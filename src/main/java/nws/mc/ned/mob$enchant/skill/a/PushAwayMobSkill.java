package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.config.Configs;
import nws.mc.ned.mob$enchant.skill.MobSkill;
import nws.mc.ned.mob$enchant.skill.MobSkillHelper;
import nws.mc.ned.mob$enchant.skill.MobSkillRegister;
import nws.mc.ned.register.data.DataRegister;
import nws.mc.ned.register.data.MobSkillData;

import java.util.List;

public class PushAwayMobSkill extends MobSkill {
    //将角色推开
    //private int tick = 20;
    private double checkDistance = 10;
    private double pushStrength = 0.1;
    public PushAwayMobSkill(String id) {
        super(id);
    }

    @Override
    public boolean canAdd(List<MobSkill> skills) {
        boolean[] can = {true};
        skills.forEach(mobSkill -> {
            if (mobSkill.getId().equals("tentacle")) can[0] = false;
        });
        return can[0];
    }
    @Override
    public void loadConfig(CompoundTag dat) {
        //tick = dat.getInt("tick");
        checkDistance = dat.getDouble("checkDistance");
        pushStrength = dat.getDouble("pushStrength");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        //dat.putInt("tick",tick);
        dat.putDouble("checkDistance",checkDistance);
        dat.putDouble("pushStrength",pushStrength);
        return dat;
    }
    /*
    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {

        int t = dat.getInt("tick");
        if (t > this.tick) {
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                dat.putInt("tick", 0);
                //double pushStrength = 1.0;
                Player player = livingEntity.level().getNearestPlayer(livingEntity, checkDistance);
                if (player != null && !livingEntity.noPhysics && !player.noPhysics) {
                    double dX = player.getX() - livingEntity.getX();
                    double dZ = player.getZ() - livingEntity.getZ();
                    double pushX = dX * pushStrength;
                    double pushZ = dZ * pushStrength;
                    //player.setDeltaMovement(pushX, 0, pushZ);
                    //player.moveTo(new Vec3(pushX,player.getY(),pushZ));
                }
            }
        }else {
            dat.putInt("tick",t+1);
        }
    }


    public int getTick() {
        return tick;
    }
     */

    public double getPushStrength() {
        return pushStrength;
    }

    public double getCheckDistance() {
        return checkDistance;
    }

    @EventBusSubscriber(modid = NekoEnd.MOD_ID,value = Dist.CLIENT)
    public static class ChaseAwayEvent{
        private static int t = 0;
        @SubscribeEvent
        public static void onClientTick(PlayerTickEvent.Pre event){
            if (Configs.mobSkill().enable() && event.getEntity() instanceof LocalPlayer localPlayer && localPlayer.level() instanceof ClientLevel clientLevel && MobSkillHelper.isEnable(clientLevel,"push_away")) {
                PushAwayMobSkill pushAwayMobSkill = (PushAwayMobSkill) clientLevel.registryAccess().holderOrThrow(MobSkillRegister.PushAway.getKey()).value();
                List<? extends LivingEntity> entities = clientLevel.getEntities(EntityTypeTest.forClass(LivingEntity.class), localPlayer.getBoundingBox().inflate(pushAwayMobSkill.getCheckDistance()), Entity::isAlive);
                for (LivingEntity livingEntity : entities) {
                    MobSkillData skillData = livingEntity.getData(DataRegister.MOB_SKILLS.get());
                    if (skillData.hasSkill(MobSkillRegister.PushAway.get())) {
                        double directionX = localPlayer.getX() - livingEntity.getX();
                        double directionY = localPlayer.getY() - livingEntity.getY();
                        double directionZ = localPlayer.getZ() - livingEntity.getZ();
                        double distance = Math.sqrt(directionX * directionX + directionY * directionY + directionZ * directionZ);
                        if (distance > 0) {
                            distance *= pushAwayMobSkill.getPushStrength();
                            double motionX = directionX / distance;
                            double motionY = directionY / distance;
                            double motionZ = directionZ / distance;
                            localPlayer.lerpMotion(motionX, motionY, motionZ);
                            break;
                        }

                    }
                }
            }
        }
    }
}
