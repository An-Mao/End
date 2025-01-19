package nws.mc.ned.mob$skill.other.tentacle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import nws.mc.ned.NED;
import nws.mc.ned.config.Configs;
import nws.mc.ned.lib.EntityHelper;
import nws.mc.ned.mob$skill.MobSkillHelper;
import nws.mc.ned.mob$skill.MobSkillRegister;
import nws.mc.ned.register.DataRegister;
import nws.mc.ned.register.MobSkillData;

import java.util.List;
@EventBusSubscriber(modid = NED.MOD_ID,value = Dist.CLIENT)
public class TentacleMobSkillClientEvent {
    private static final double speed = 0.2;
    @SubscribeEvent
    public static void onClientTick(LevelTickEvent.Pre event){
        if (event.getLevel().isClientSide() && Configs.mobSkill().enable() && MobSkillHelper.isEnable(event.getLevel(),"tentacle")) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                List<? extends LivingEntity> entities = EntityHelper.getEntityLevel(player).getEntities(EntityTypeTest.forClass(LivingEntity.class), player.getBoundingBox().inflate(20), Entity::isAlive);
                final boolean[] b = {false};

                for (LivingEntity livingEntity : entities) {
                    MobSkillData skillData = livingEntity.getData(DataRegister.MOB_SKILLS.get());
                    if (skillData.hasSkill(MobSkillRegister.Tentacle.get())) {
                        //System.out.println("---------------tent---------------");
                        double directionX = livingEntity.getX() - player.getX();
                        double directionY = livingEntity.getY() - player.getY();
                        double directionZ = livingEntity.getZ() - player.getZ();
                        double distance = Math.sqrt(directionX * directionX + directionY * directionY + directionZ * directionZ);
                        double motionX = directionX / distance * speed;
                        double motionY = directionY / distance * speed;
                        double motionZ = directionZ / distance * speed;
                        player.lerpMotion(motionX, motionY, motionZ);
                        break;

                    }
                }
            }
        }
    }
}
