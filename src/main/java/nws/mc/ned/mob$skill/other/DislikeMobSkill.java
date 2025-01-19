package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class DislikeMobSkill extends MobSkill {
    public DislikeMobSkill(String id) {
        super(id);
    }


    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        if (event.getSource().getEntity() != null && event.getEntity().distanceTo(event.getSource().getEntity()) < 5) {
            event.setNewDamage(event.getNewDamage() * 0.2F);
        }
    }
}
