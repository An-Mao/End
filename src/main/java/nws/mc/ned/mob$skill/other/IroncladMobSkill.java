package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class IroncladMobSkill extends MobSkill {
    public IroncladMobSkill(String id) {
        super(id);
    }


    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        float damage = Math.min(event.getNewDamage(),11);
        event.setNewDamage(damage);
    }
}
