package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class RebirthMobSkill extends MobSkill {
    public RebirthMobSkill(String id) {
        super(id);
    }


    @Override
    public void livingDeath(LivingDeathEvent event, CompoundTag dat) {
        if (!dat.getBoolean("isRebirth")){
            dat.putBoolean("isRebirth",true);
            event.getEntity().setHealth(event.getEntity().getMaxHealth());
            event.setCanceled(true);
        }
    }
}
