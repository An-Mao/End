package nws.mc.ned.event.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.entity.EntityTypeTest;
import nws.mc.cores.helper.entity.EntityHelper;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.config.client.ClientConfig;
import nws.mc.ned.invasion.InvasionTimer;
import nws.mc.ned.mob$enchant.skill.MobSkill;
import nws.mc.ned.register.data.DataRegister;
import nws.mc.ned.register.data.MobSkillData;
import nws.mc.ned.register.net.NetRegister;
import nws.mc.net.core.NetCore;
import nws.mc.net.core.NetPack;

import java.util.List;

public class CommandReg {
    public static void Reg(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext){
        dispatcher.register(Commands.literal(NekoEnd.MOD_ID)
                        .then(Commands.literal("invision")
                                .then(Commands.literal("start")
                                        .requires(commandSource -> commandSource.hasPermission(2))
                                        .executes(CommandReg::invisionStart)
                                )
                        )
                        .then(Commands.literal("add_mob_skill")
                                .then(Commands.argument("ned:mob_skill", MobSkillArgument.mobSkill(buildContext))
                                        .requires(commandSource -> commandSource.hasPermission(2))
                                        .executes(CommandReg::addMobSkill)
                                )
                        )
                        .then(Commands.literal("client")
                                .then(Commands.literal("render")
                                        .then(Commands.literal("mob_skill")
                                                .then(Commands.literal("type")
                                                        .then(Commands.argument("type", IntegerArgumentType.integer(0,2))
                                                                .executes(CommandReg::changeRenderType)
                                                        )
                                                )
                                                .then(Commands.literal("only_view")
                                                        .then(Commands.argument("type", BoolArgumentType.bool())
                                                                .executes(CommandReg::changeRenderView)
                                                        )
                                                )
                                        )

                                )

                        )
                        .then(Commands.literal("debug")
                                .then(Commands.literal("info")
                                        .then(Commands.literal("near")
                                                .executes(CommandReg::debugInfoNear)
                                        )
                                )
                        )
                );
    }

    private static int debugInfoNear(CommandContext<CommandSourceStack> c) {
        if (c.getSource().getEntity() instanceof ServerPlayer player){
            EntityHelper.getRadiusEntities(player.level(), player.getBoundingBox().inflate(10), 10).forEach(entity -> {
                if (entity instanceof LivingEntity livingEntity){
                    c.getSource().sendSystemMessage(Component.literal(livingEntity.getAttributes().save().toString()));
                }
            });

        }
        return 0;
    }

    private static int changeRenderView(CommandContext<CommandSourceStack> context) {
        boolean type = context.getArgument("type", Boolean.class);

        ClientConfig.INSTANCE.getDatas().MobSkillOnlyRenderWithView = type;
        context.getSource().sendSuccess(() -> Component.literal("success change render view:"+type), false);
        return 1;
    }

    private static int changeRenderType(CommandContext<CommandSourceStack> context) {
        int type = context.getArgument("type", Integer.class);
        ClientConfig.INSTANCE.getDatas().MobSkillRenderType = type;
        context.getSource().sendSuccess(() -> Component.literal("success change render type:"+type), false);
        return 1;
    }

    private static int invisionStart(CommandContext<CommandSourceStack> context) {
        InvasionTimer.I.preInvasion(context.getSource().getLevel());
        //context.getSource().getLevel().getData(DataRegister.INVASIONS).preInvasion(context.getSource().getLevel());
        return 1;
    }
    private static int addMobSkill(CommandContext<CommandSourceStack> context) {
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
    }
}
