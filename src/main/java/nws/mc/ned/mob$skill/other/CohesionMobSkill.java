package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class CohesionMobSkill extends MobSkill {
    public CohesionMobSkill(String id) {
        super(id);
    }

    @Override
    public void livingDeath(LivingDeathEvent event, CompoundTag dat) {
        LivingEntity livingEntity = event.getEntity();
        if (!dat.getBoolean("Cohesion")) {
            dat.putBoolean("Cohesion",true);
            PrimedTnt tntEntity = new PrimedTnt(livingEntity.level(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), livingEntity);
            tntEntity.setFuse(60);
            livingEntity.level().addFreshEntity(tntEntity);
        }
    }
}
