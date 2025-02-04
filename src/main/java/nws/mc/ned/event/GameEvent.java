package nws.mc.ned.event;

import net.minecraft.core.RegistryAccess;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.config.Configs;
import nws.mc.ned.config.damage_scale.DamageScaleConfig;
import nws.mc.ned.config.fighting_strength.FightingStrengthConfig;
import nws.mc.ned.config.invasion.InvasionConfig;
import nws.mc.ned.config.invasion.InvasionMobList;
import nws.mc.ned.event.command.CommandReg;
import nws.mc.ned.invasion.InvasionCDT;
import nws.mc.ned.invasion.InvasionTimer;
import nws.mc.ned.mob$enchant.fighting_strength.FightingStrengthHelper;
import nws.mc.ned.mob$enchant.skill.MobSkillHelper;
import nws.mc.ned.mob$enchant.skill.MobSkills;
import nws.mc.ned.register.data.DataRegister;
import nws.mc.ned.register.data.MobSkillData;

@EventBusSubscriber(modid = NekoEnd.MOD_ID,bus = EventBusSubscriber.Bus.GAME)
public class GameEvent {


    @SubscribeEvent
    public static void onStart(ServerStartedEvent event){
        InvasionTimer.setRun(true);
    }
    @SubscribeEvent
    public static void onStop(ServerStoppingEvent event){
        InvasionTimer.setRun(false);
    }

    @SubscribeEvent
    public static void onReload(AddReloadListenerEvent event) {
        RegistryAccess registryAccess = event.getRegistryAccess();
        MobSkills.load(registryAccess);
        Configs.loadDataPack(registryAccess);
    }
    @SubscribeEvent
    public static void onLoad(LevelEvent.Load event) {
        //if (event.getLevel().isClientSide()) return;
        /*
        RegistryAccess registryAccess = event.getLevel().registryAccess();
        MobSkills.load(registryAccess);

        Configs.loadDataPack(registryAccess);
         */
    }

    /*
    @SubscribeEvent
    public static void onStart(ServerStartingEvent event) {
        MobSkills.load(event.getServer().registryAccess());
    }

     */
    @SubscribeEvent
    public static void onSpawn(FinalizeSpawnEvent event) {
        if (!event.getLevel().isClientSide()) {
            Mob mob = event.getEntity();
            if (isInvasionMob(mob)) {
                mob.addEffect(InvasionCDT.SLOW_FALLING);
                mob.addEffect(InvasionCDT.WATER_BREATHING);
                mob.addEffect(InvasionCDT.FIRE_RESISTANCE);
            }
            FightingStrengthConfig.addFightingStrength(mob);
            if (Configs.mobSkill().enable()) {
                MobSkillData mobSkillData = mob.getData(DataRegister.MOB_SKILLS);
                if (MobSkillHelper.canAddSkill(mob)) {
                    if (mobSkillData.isFirstSpawn()) {
                        mobSkillData.setFirstSpawn(false);
                        if (mobSkillData.isEmpty()) {
                            mobSkillData.randomSkills();
                            event.getEntity().setData(DataRegister.MOB_SKILLS, mobSkillData);
                        }
                    }
                    mobSkillData.spawnEvent(event);
                }
            }
        }
    }
    @SubscribeEvent
    public static void regCommand(RegisterCommandsEvent event) {
        CommandReg.Reg(event.getDispatcher(),event.getBuildContext());
    }
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Pre event) {
        Level level = event.getLevel();
        if (!level.isClientSide() && level.dimension().location().toString().equals("minecraft:overworld")) {
            //level.getData(DataRegister.INVASIONS).tick(level);
            InvasionTimer.I.setDayTime(level.getDayTime());
            MinecraftServer server = level.getServer();
            InvasionTimer.I.sendMsg(server);
            InvasionTimer.I.spawn(server);
        }

    }
    @SubscribeEvent
    public static void EntityInvulnerabilityCheckEvent(EntityInvulnerabilityCheckEvent event) {
        if (event.getEntity() instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide()){
            if (Configs.mobSkill().enable() && notPlayer(event.getEntity())) {
                event.getEntity().getData(DataRegister.MOB_SKILLS.get()).entityInvulnerabilityCheck(event);
            }
            if (InvasionConfig.isEnable() && isInvasionMob(livingEntity) && InvasionConfig.isImmunityNonPlayerDamage()) {
                if (!(event.getSource().getEntity() instanceof Player)) {
                    event.setInvulnerable(true);
                }
            }
        }
    }
    @SubscribeEvent
    public static void onJoin(EntityJoinLevelEvent event) {
        if ( event.getEntity() instanceof LivingEntity livingEntity &&  notPlayer(livingEntity)){
            if (livingEntity.level().isClientSide()) {
                if (FightingStrengthConfig.I.getDatas().enable()) FightingStrengthHelper.requestData(livingEntity);
                if (Configs.mobSkill().enable())MobSkillHelper.requestData(livingEntity);
            }else {
                if (Configs.mobSkill().enable()){
                    if (!MobSkillHelper.canAddSkill(livingEntity)) return;
                    MobSkillData mobSkillData = livingEntity.getData(DataRegister.MOB_SKILLS);
                    if (mobSkillData.isEmpty()) mobSkillData.randomSkills();
                    livingEntity.getData(DataRegister.MOB_SKILLS).entityJoinLevel(event);
                }
            }
        }
    }
    @SubscribeEvent
    public static void onAttack(LivingIncomingDamageEvent event) {
        if (Configs.mobSkill().enable() && notClient(event.getEntity())) {
            LivingEntity livingEntity = event.getEntity();
            if (notPlayer(livingEntity)) {
                livingEntity.getData(DataRegister.MOB_SKILLS.get()).livingIncomingDamage(event);
            }
        }
    }

    @SubscribeEvent
    public static void onHurt(LivingDamageEvent.Pre event) {
        if (event.getEntity().level().isClientSide())return;
        LivingEntity entity = event.getEntity();
        if (notPlayer(entity)){
            if (DamageScaleConfig.INSTANCE.getDatas().enable()){
                float oldDamage = event.getNewDamage();
                float maxDamage = Math.max(DamageScaleConfig.INSTANCE.getDatas().minDamage(),entity.getMaxHealth() * DamageScaleConfig.INSTANCE.getDatas().scaleWithHealth());
                event.setNewDamage(Math.min(oldDamage, maxDamage));
            }
            if (Configs.mobSkill().enable()) entity.getData(DataRegister.MOB_SKILLS.get()).livingDamagePre(event);
        }

    }

    @SubscribeEvent
    public static void onDamage(LivingDamageEvent.Post event) {
        if (Configs.mobSkill().enable() && notClient(event.getEntity())) {
            LivingEntity livingEntity = event.getEntity();
            if (notPlayer(livingEntity)) {
                livingEntity.getData(DataRegister.MOB_SKILLS.get()).livingDamagePost(event);
            }
        }
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if (Configs.mobSkill().enable() && notClient(event.getEntity())) {
            LivingEntity livingEntity = event.getEntity();
            if (notPlayer(livingEntity)) {
                livingEntity.getData(DataRegister.MOB_SKILLS.get()).livingDeath(event);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Pre event) {
        if (Configs.mobSkill().enable() && event.getEntity() instanceof LivingEntity livingEntity) {
            if (notClient(event.getEntity())) {
                if (notPlayer(event.getEntity()) && livingEntity.hasData(DataRegister.MOB_SKILLS)) {
                    livingEntity.getData(DataRegister.MOB_SKILLS).entityTickPre(event);
            /*
            MobSkillData mobSkillData = livingEntity.getData(DataRegister.MOB_SKILLS);
            mobSkillData.entityTickPre(event);
            if (mobSkillData.isNeedSync()) {
                mobSkillData.setNeedSync(false);
                CompoundTag data = mobSkillData.getCompoundTag();
                data.putInt("entity", event.getEntity().getId());
                NetCore.sendToEntity(NetPack.createClientPack(data, NetRegister.MOB_SKILL.get()), event.getEntity());
            }

             */
                }
            } else {
                MobSkillHelper.requestData(livingEntity);
            }
        }
    }


    public static boolean notClient(Entity entity) {
        return !entity.level().isClientSide;
    }

    public static boolean notPlayer(Entity entity) {
        return !(entity instanceof Player);
    }

    public static boolean isInvasionMob(LivingEntity livingEntity){
        return livingEntity.getPersistentData().getBoolean(InvasionMobList.SAVE_INVASION_KEY);
    }
}
