package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class SummonMonsterMobSkill extends MobSkill {
    private int tick = 1000;
    public SummonMonsterMobSkill(String id) {
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
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > tick){
            dat.putInt("tick",0);
        }else {
            dat.putInt("tick",t + 1);
        }
    }
}
