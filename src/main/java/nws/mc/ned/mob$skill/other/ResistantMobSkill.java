package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class ResistantMobSkill extends MobSkill {
    //减免受到的伤害
    public ResistantMobSkill(String id) {
        super(id);
    }

    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        event.setNewDamage(event.getNewDamage() /2);
    }
}
