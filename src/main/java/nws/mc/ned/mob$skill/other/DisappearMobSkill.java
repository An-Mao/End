package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class DisappearMobSkill extends MobSkill {
    //一段时间后消失
    public DisappearMobSkill(String id) {
        super(id);
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
        if (event.getEntity().level().getGameTime() - dat.getLong("JoinTime") > 1200){
            event.getEntity().discard();
        }
    }
}
