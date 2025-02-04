package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class LowFrequencyReflectMobSkill extends MobSkill {
    private int tick = 40;
    private float damageScale = 0.1F;
    private int maxScale = 10;
    public LowFrequencyReflectMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        damageScale = dat.getFloat("damageScale");
        maxScale = dat.getInt("maxScale");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putFloat("damageScale",damageScale);
        dat.putInt("maxScale",maxScale);
        return dat;
    }
    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        int d = dat.getInt("downDamage");
        event.setNewDamage(event.getNewDamage() * (d * damageScale));
        if (d < maxScale) {
            dat.putInt("downDamage",d+1);
        }
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int tick = dat.getInt("tick");
        if (tick >this.tick){
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
