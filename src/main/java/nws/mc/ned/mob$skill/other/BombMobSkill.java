package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class BombMobSkill extends MobSkill {
    public BombMobSkill(String id) {
        super(id);
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > 100) {
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                Player player = livingEntity.level().getNearestPlayer(livingEntity, 32.0);
                if (player != null) {
                    double livingEntityX = livingEntity.getX();
                    double livingEntityY = livingEntity.getY() + livingEntity.getBbHeight() + 0.6;
                    double livingEntityZ = livingEntity.getZ();

                    Vec3 playerPos = player.position();

                    double motionX = playerPos.x - livingEntityX;
                    double motionY = playerPos.y+player.getBbHeight()/2 - livingEntityY;
                    double motionZ = playerPos.z - livingEntityZ;

                    LargeFireball largeFireball = new LargeFireball(livingEntity.level(), livingEntity, new Vec3(motionX, motionY, motionZ), 1);

                    largeFireball.setPos(livingEntityX, livingEntityY, livingEntityZ);

                    //largeFireball.setDeltaMovement(motionX, motionY, motionZ);

                    largeFireball.accelerationPower = 0.06;

                    player.level().addFreshEntity(largeFireball);
                }
            }
        }else {
            dat.putInt("tick",t+1);
        }
    }
}
