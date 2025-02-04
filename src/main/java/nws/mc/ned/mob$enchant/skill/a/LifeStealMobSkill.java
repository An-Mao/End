package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class LifeStealMobSkill extends MobSkill {
    private float healthBase = 0.5F;
    public LifeStealMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        healthBase = dat.getFloat("healthBase");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putFloat("healthBase", healthBase);
        return dat;
    }
    @Override
    public void livingIncomingDamage(LivingIncomingDamageEvent event, CompoundTag dat) {

        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            livingEntity.heal(event.getAmount() * healthBase);
        }


    }
}
