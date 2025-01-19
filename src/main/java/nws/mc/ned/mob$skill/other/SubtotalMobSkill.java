package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.lib.EntityHelper;
import nws.mc.ned.mob$skill.MobSkill;
import nws.mc.ned.register.DataRegister;

import java.util.ArrayList;
import java.util.List;

public class SubtotalMobSkill extends MobSkill {
    public SubtotalMobSkill(String id) {
        super(id);
    }

    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        if (event.getSource().getEntity() instanceof ServerPlayer){
            List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(event.getEntity(), 10);
            List<LivingEntity> se = new ArrayList<>();
            for (LivingEntity entity : entities){
                if (entity.getData(DataRegister.MOB_SKILLS).hasSkill(this)){
                    se.add(entity);
                }
            }
            if (!se.isEmpty()) {
                float amount = event.getNewDamage() * (1F / se.size());
                for (LivingEntity livingEntity : se) {
                    livingEntity.hurt(event.getEntity().damageSources().magic(), amount);
                }
                event.setNewDamage(amount);
            }
        }
    }
}
