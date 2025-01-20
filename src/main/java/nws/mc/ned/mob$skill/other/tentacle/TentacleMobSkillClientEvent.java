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
import nws.mc.cores.helper.entity.EntityHelper;
import nws.mc.ned.NED;
import nws.mc.ned.config.Configs;
import nws.mc.ned.mob$skill.MobSkillHelper;
import nws.mc.ned.mob$skill.MobSkillRegister;
import nws.mc.ned.register.data.DataRegister;
import nws.mc.ned.register.data.MobSkillData;

import java.util.List;
@EventBusSubscriber(modid = NED.MOD_ID,value = Dist.CLIENT)
public class TentacleMobSkillClientEvent {
    @SubscribeEvent
    public static void onClientTick(LevelTickEvent.Pre event){
        if (event.getLevel().isClientSide() && Configs.mobSkill().enable() && MobSkillHelper.isEnable(event.getLevel(),"tentacle")) {
            TentacleMobSkill tentacleMobSkill = (TentacleMobSkill) event.getLevel().registryAccess().holderOrThrow(MobSkillRegister.Tentacle.getKey()).value();
            
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                List<? extends LivingEntity> entities = EntityHelper.getLevel(player).getEntities(EntityTypeTest.forClass(LivingEntity.class), player.getBoundingBox().inflate(20), Entity::isAlive);
                final boolean[] b = {false};

                for (LivingEntity livingEntity : entities) {
                    MobSkillData skillData = livingEntity.getData(DataRegister.MOB_SKILLS.get());
                    if (skillData.hasSkill(MobSkillRegister.Tentacle.get())) {
                        //System.out.println("---------------tent---------------");
                        double directionX = livingEntity.getX() - player.getX();
                        double directionY = livingEntity.getY() - player.getY();
                        double directionZ = livingEntity.getZ() - player.getZ();
                        double distance = Math.sqrt(directionX * directionX + directionY * directionY + directionZ * directionZ);
                        double motionX = directionX / distance * tentacleMobSkill.getMoveSpeed();
                        double motionY = directionY / distance * tentacleMobSkill.getMoveSpeed();
                        double motionZ = directionZ / distance * tentacleMobSkill.getMoveSpeed();
                        player.lerpMotion(motionX, motionY, motionZ);
                        break;

                    }
                }
            }
        }
    }
}
