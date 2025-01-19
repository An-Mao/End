package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class LifeAbsorbingMobSkill extends MobSkill {
    public LifeAbsorbingMobSkill(String id) {
        super(id);
    }


    @Override
    public void livingIncomingDamage(LivingIncomingDamageEvent event, CompoundTag dat) {

        if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
            livingEntity.heal(event.getAmount() * 0.5F);
        }


    }
}
