package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class HighFrequencyBoundaryMobSkill extends MobSkill {
    public HighFrequencyBoundaryMobSkill(String id) {
        super(id);
    }

    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        int d = dat.getInt("downDamage");
        event.setNewDamage(event.getNewDamage() * (1 - d * 0.1F));
        if (d < 10) {
            dat.putInt("downDamage",d+1);
        }
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int tick = dat.getInt("tick");
        if (tick >40){
            dat.putInt("tick",0);

            int d = dat.getInt("downDamage");
            if (d > 0){
                dat.putInt("downDamage",d-1);
            }
        }else {
            dat.putInt("tick",tick+1);
        }
    }
}
