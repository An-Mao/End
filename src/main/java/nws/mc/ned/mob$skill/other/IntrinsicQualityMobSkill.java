package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class IntrinsicQualityMobSkill extends MobSkill {
    public IntrinsicQualityMobSkill(String id) {
        super(id);
    }


    @Override
    public void livingIncomingDamage(LivingIncomingDamageEvent event, CompoundTag dat) {
        if (dat.getBoolean("noDamage")){
            dat.putBoolean("noDamage",false);
            event.setCanceled(true);
        }
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > 60){
            dat.putInt("tick",0);
            dat.putBoolean("noDamage",true);
        }else {
            dat.putInt("tick",t + 1);
        }
    }
}
