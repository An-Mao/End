package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class NewbornMobSkill extends MobSkill {
    private int time = 1000;
    public NewbornMobSkill(String id) {
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
        if (!dat.getBoolean("isNotFirstJoin")) {
            dat.putBoolean("isNotFirstJoin",true);
            dat.putInt("invincibility", time);
        }
    }
    @Override
    public void entityInvulnerabilityCheck(EntityInvulnerabilityCheckEvent event, CompoundTag dat) {
        if (dat.getInt("invincibility") >0){
            event.setInvulnerable(true);
        }
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int i = dat.getInt("invincibility");
        if (i > 0){
            dat.putInt("invincibility",i-1);
        }
    }
}
