package nws.mc.ned.event;

import net.minecraft.commands.Commands;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import nws.mc.ned.NED;
import nws.mc.ned.config.Configs;
import nws.mc.ned.lib.EntityHelper;
import nws.mc.ned.mob$skill.MobSkill;
import nws.mc.ned.mob$skill.MobSkillArgument;
import nws.mc.ned.mob$skill.MobSkillHelper;
import nws.mc.ned.mob$skill.MobSkills;
import nws.mc.ned.register.DataRegister;
import nws.mc.ned.register.MobSkillData;
import nws.mc.ned.register.net.NetRegister;
import nws.mc.net.core.NetCore;
import nws.mc.net.core.NetPack;

import java.util.List;

@EventBusSubscriber(modid = NED.MOD_ID,bus = EventBusSubscriber.Bus.GAME)
public class GameEvent {
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
        if (!event.getLevel().isClientSide() && Configs.mobSkill().enable()) {
            MobSkillData mobSkillData = event.getEntity().getData(DataRegister.MOB_SKILLS);
            if (MobSkillHelper.canAddSkill(event.getEntity())){
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
    @SubscribeEvent
    public static void regCommand(RegisterCommandsEvent event) {
        event.getDispatcher()
                .register(Commands.literal(NED.MOD_ID)
                        .then(Commands.literal("invision")
                                .then(Commands.literal("start")
                                        .requires(commandSource -> commandSource.hasPermission(2))
                                        .executes(context -> {
                                            context.getSource().getLevel().getData(DataRegister.INVASIONS).preInvasion(context.getSource().getLevel());
                                            return 1;
                                        }
                                        )
                                )
                        )
                        .then(Commands.literal("addSkill")
                                .then(Commands.argument("ned:mob_skill", MobSkillArgument.mobSkill(event.getBuildContext()))
                                                .requires(commandSource -> commandSource.hasPermission(2))
                                                .executes(context -> {
                                                    if (context.getSource().getEntity() instanceof ServerPlayer serverPlayer){
                                                        MobSkill skill = MobSkillArgument.getMobSkill(context,"ned:mob_skill").getSkill().value();
                                                        List<LivingEntity> entities = serverPlayer.level().getEntities(EntityTypeTest.forClass(LivingEntity.class), serverPlayer.getBoundingBox().inflate(5), Entity::isAlive);
                                                        entities.forEach(livingEntity -> {
                                                            if (livingEntity instanceof Player) return;
                                                            MobSkillData mobSkillData = livingEntity.getData(DataRegister.MOB_SKILLS);
                                                            mobSkillData.giveNewSkill(skill);
                                                            CompoundTag data = mobSkillData.getCompoundTag();
                                                            data.putInt("entity", livingEntity.getId());
                                                            NetCore.sendToPlayer(NetPack.createClientPack(data, NetRegister.MOB_SKILL.get()),serverPlayer);

                                                        });
                                                        context.getSource().sendSuccess(() -> Component.literal("success"), false);
                                                        return 1;
                                                    }
                                                    return 0;
                                                })
                                )
                        )

                );
    }
}
