package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class IroncladMobSkill extends MobSkill {
    private float maxDamage = 11.0F;
    public IroncladMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        maxDamage = dat.getFloat("maxDamage");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putFloat("maxDamage", maxDamage);
        return dat;
    }
    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        float damage = Math.min(event.getNewDamage(),maxDamage);
        event.setNewDamage(damage);
    }
}
