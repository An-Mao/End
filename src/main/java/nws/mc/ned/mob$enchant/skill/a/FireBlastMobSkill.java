package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class FireBlastMobSkill extends MobSkill {
    private int tick = 100;
    private double checkDistance = 32;
    private double summonY = 0.6;
    private int power = 1;
    private double speed = 0.06;
    public FireBlastMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        checkDistance = dat.getDouble("checkDistance");
        summonY = dat.getDouble("summonY");
        power = dat.getInt("power");
        speed = dat.getDouble("speed");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putDouble("checkDistance",checkDistance);
        dat.putDouble("summonY",summonY);
        dat.putInt("power",power);
        dat.putDouble("speed",speed);
        return dat;
    }
    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > this.tick){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                Player player = livingEntity.level().getNearestPlayer(livingEntity, checkDistance);
                if (player != null) {
                    double livingEntityX = livingEntity.getX();
                    double livingEntityY = livingEntity.getY() + livingEntity.getBbHeight() + summonY;
                    double livingEntityZ = livingEntity.getZ();

                    Vec3 playerPos = player.position();

                    double motionX = playerPos.x - livingEntityX;
                    double motionY = playerPos.y+player.getBbHeight()/2 - livingEntityY;
                    double motionZ = playerPos.z - livingEntityZ;

                    LargeFireball largeFireball = new LargeFireball(livingEntity.level(), livingEntity, new Vec3(motionX, motionY, motionZ), power);

                    largeFireball.setPos(livingEntityX, livingEntityY, livingEntityZ);

                    //largeFireball.setDeltaMovement(motionX, motionY, motionZ);

                    largeFireball.accelerationPower = speed;

                    player.level().addFreshEntity(largeFireball);
                }
            }
        }else {
            dat.putInt("tick",t+1);
        }
    }
}
