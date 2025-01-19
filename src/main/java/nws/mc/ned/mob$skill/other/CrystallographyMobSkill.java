package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class CrystallographyMobSkill extends MobSkill {
    public CrystallographyMobSkill(String id) {
        super(id);
    }

    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        if (event.getNewDamage() != 1.0F){
            event.setNewDamage(1.0F);
        }
    }
}
