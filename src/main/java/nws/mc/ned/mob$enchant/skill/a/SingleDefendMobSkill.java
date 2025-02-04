package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class SingleDefendMobSkill extends MobSkill {
    private int tick = 60;
    public SingleDefendMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        return dat;
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
        if (t > tick){
            dat.putInt("tick",0);
            dat.putBoolean("noDamage",true);
        }else {
            dat.putInt("tick",t + 1);
        }
    }
}
