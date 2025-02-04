package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class ResurrectionMobSkill extends MobSkill {
    public ResurrectionMobSkill(String id) {
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
