package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class DarkPositionMobSkill extends MobSkill {
    //减少受到的伤害，处于异常时可造成正常伤害
    public DarkPositionMobSkill(String id) {
        super(id);
    }

    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        if (!hasDeBuff(event.getEntity())){
            event.setNewDamage(event.getNewDamage() * 0.2F);
        }
    }

    public static boolean hasDeBuff(LivingEntity entity) {
        return entity.getActiveEffects().stream().anyMatch(effect -> !effect.getEffect().value().isBeneficial());
    }
}
