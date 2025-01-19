package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class RainbowPositionMobSkill extends MobSkill {
    public RainbowPositionMobSkill(String id) {
        super(id);
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > 10){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                livingEntity.getActiveEffects().removeIf(mobEffectInstance -> mobEffectInstance.getEffect().value().isBeneficial());
            }
        }else {
            dat.putInt("tick",t + 1);
        }
    }
}
