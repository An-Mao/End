package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class DamageReduceMobSkill extends MobSkill {
    private float damageScale = 0.2F;
    //减免受到的伤害
    public DamageReduceMobSkill(String id) {
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
        event.setNewDamage(event.getNewDamage() * damageScale);
    }
}
