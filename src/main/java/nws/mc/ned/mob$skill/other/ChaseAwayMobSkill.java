package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class ChaseAwayMobSkill extends MobSkill {
    //将角色推开
    public ChaseAwayMobSkill(String id) {
        super(id);
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {

        int t = dat.getInt("tick");
        if (t > 20) {
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                dat.putInt("tick", 0);
                double pushStrength = 1.0;
                Player player = livingEntity.level().getNearestPlayer(livingEntity, 10.0);
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
