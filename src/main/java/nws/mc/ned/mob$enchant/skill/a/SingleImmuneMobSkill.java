package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class SingleImmuneMobSkill extends MobSkill {
    private float maxDamage = 1.0F;
    public SingleImmuneMobSkill(String id) {
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
        if (event.getNewDamage() != maxDamage){
            event.setNewDamage(maxDamage);
        }
    }
}
