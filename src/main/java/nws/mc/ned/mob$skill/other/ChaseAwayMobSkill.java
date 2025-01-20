package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class ChaseAwayMobSkill extends MobSkill {
    //将角色推开
    private int tick = 20;
    private double checkDistance = 10;
    private double pushStrength = 1;
    public ChaseAwayMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        checkDistance = dat.getDouble("checkDistance");
        pushStrength = dat.getDouble("pushStrength");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putDouble("checkDistance",checkDistance);
        dat.putDouble("pushStrength",pushStrength);
        return dat;
    }
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
                }
            }
        }else {
            dat.putInt("tick",t+1);
        }
    }
}
