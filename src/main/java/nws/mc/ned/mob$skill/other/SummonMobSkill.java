package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class SummonMobSkill extends MobSkill {
    public SummonMobSkill(String id) {
        super(id);
    }


    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > 1000){
            dat.putInt("tick",0);
        }else {
            dat.putInt("tick",t + 1);
        }
    }
}
