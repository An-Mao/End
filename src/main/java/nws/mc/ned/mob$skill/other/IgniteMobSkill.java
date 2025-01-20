package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class IgniteMobSkill extends MobSkill {
    private int tick = 100;
    private int tick2 = 20;
    private int max = 4;
    public IgniteMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        tick2 = dat.getInt("tick2");
        max = dat.getInt("max");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putInt("tick2",tick2);
        dat.putInt("max",max);
        return dat;
    }
    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        if (event.getEntity() instanceof LivingEntity livingEntity) {
            int tick = dat.getInt("tick");
            if (tick > this.tick) {
                int t2 = dat.getInt("tick2");
                if (t2 > this.tick2) {
                    dat.putInt("tick2", 0);
                    int a = dat.getInt("a");
                    summonExplosion(livingEntity, a);
                    if (a >= max) {
                        dat.putInt("tick", 0);
                    } else {
                        dat.putInt("a", a + 1);
                    }
                } else {
                    dat.putInt("tick2", t2 + 1);
                }
            } else {
                dat.putInt("tick", tick + 1);
            }
        }
    }

    @Override
    public void livingDeath(LivingDeathEvent event, CompoundTag dat) {
        LivingEntity livingEntity = event.getEntity();
        double x = livingEntity.getX();
        double y = livingEntity.getY();
        double z = livingEntity.getZ();
        for (int a = 0;a < max;a++){
            summonExplosion(livingEntity,x,y,z,a);
        }
    }

    private void summonExplosion(LivingEntity livingEntity , int a){
        double x = livingEntity.getX();
        double y = livingEntity.getY();
        double z = livingEntity.getZ();
        summonExplosion(livingEntity,x,y,z,a);
    }
    private void summonExplosion(LivingEntity livingEntity, double x,double y,double z, int a){
        Explosion explosion = getExplosion(livingEntity,x + a, y, z + a);
        Explosion explosion1 = getExplosion(livingEntity,x + a, y, z - a);
        Explosion explosion2 = getExplosion(livingEntity,x - a, y, z + a);
        Explosion explosion3 = getExplosion(livingEntity,x - a, y, z - a);
        explosion.explode();
        explosion1.explode();
        explosion2.explode();
        explosion3.explode();
    }
    public Explosion getExplosion(LivingEntity livingEntity,double x,double y, double z) {
        return new Explosion(livingEntity.level(), livingEntity, x , y, z , 1.0f, true, Explosion.BlockInteraction.KEEP);
    }
}
