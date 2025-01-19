package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class RecoverMobSkill extends MobSkill {
    public RecoverMobSkill(String id) {
        super(id);
    }


    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > 100){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity){
                livingEntity.heal(2.0F);
            }
        }else {
            dat.putInt("tick",t + 1);
        }
    }
}
