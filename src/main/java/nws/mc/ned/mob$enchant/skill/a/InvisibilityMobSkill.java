package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class InvisibilityMobSkill extends MobSkill {
    private int time = 1200;
    //一段时间后消失
    public InvisibilityMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        time = dat.getInt("time");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("time",time);
        return dat;
    }
    @Override
    public void entityJoinLevel(EntityJoinLevelEvent event, CompoundTag dat) {
        if (!dat.getBoolean("NoFirstJoin")) {
            dat.putBoolean("NoFirstJoin",true);
            dat.putLong("JoinTime", event.getEntity().level().getGameTime());
        }
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        if (event.getEntity().level().getGameTime() - dat.getLong("JoinTime") > time){
            event.getEntity().discard();
        }
    }
}
