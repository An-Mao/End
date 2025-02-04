package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class DarkBoostMobSkill extends MobSkill {
    private float damageScale = 0.2F;
    //减少受到的伤害，处于异常时可造成正常伤害
    public DarkBoostMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        damageScale = dat.getFloat("damageScale");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putFloat("damageScale",damageScale);
        return dat;
    }
    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        if (!hasDeBuff(event.getEntity())){
            event.setNewDamage(event.getNewDamage() * damageScale);
        }
    }

    public static boolean hasDeBuff(LivingEntity entity) {
        return entity.getActiveEffects().stream().anyMatch(effect -> !effect.getEffect().value().isBeneficial());
    }
}
