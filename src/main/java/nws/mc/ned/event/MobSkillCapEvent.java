package nws.mc.ned.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.NED;
import nws.mc.ned.config.Configs;
import nws.mc.ned.mob$skill.MobSkillHelper;
import nws.mc.ned.register.data.DataRegister;
import nws.mc.ned.register.data.MobSkillData;

@EventBusSubscriber(modid = NED.MOD_ID)
public class MobSkillCapEvent {
    @SubscribeEvent
    public static void EntityInvulnerabilityCheckEvent(EntityInvulnerabilityCheckEvent event) {
        if (Configs.mobSkill().enable() && notClient(event.getEntity()) && notPlayer(event.getEntity())) {
            event.getEntity().getData(DataRegister.MOB_SKILLS.get()).entityInvulnerabilityCheck(event);
        }
    }
    @SubscribeEvent
    public static void onJoin(EntityJoinLevelEvent event) {
        if (Configs.mobSkill().enable() && event.getEntity() instanceof LivingEntity livingEntity && notPlayer(livingEntity)) {
            if (livingEntity.level().isClientSide()) {
                MobSkillHelper.requestData(livingEntity);
            } else {
                if (!MobSkillHelper.canAddSkill(livingEntity)) return;
                MobSkillData mobSkillData = livingEntity.getData(DataRegister.MOB_SKILLS);
                if (mobSkillData.isEmpty()) mobSkillData.randomSkills();
                livingEntity.getData(DataRegister.MOB_SKILLS).entityJoinLevel(event);

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
        if (Configs.mobSkill().enable() && notClient(event.getEntity())) {
            LivingEntity livingEntity = event.getEntity();
            if (notPlayer(livingEntity)) {
                livingEntity.getData(DataRegister.MOB_SKILLS.get()).livingDamagePre(event);
            }
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
    public static void onTick(EntityTickEvent.Pre event) {
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
}
