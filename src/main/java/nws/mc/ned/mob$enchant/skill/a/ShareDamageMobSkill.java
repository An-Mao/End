package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.cores.helper.entity.EntityHelper;
import nws.mc.ned.mob$enchant.skill.MobSkill;
import nws.mc.ned.register.data.DataRegister;

import java.util.ArrayList;
import java.util.List;

public class ShareDamageMobSkill extends MobSkill {
    private int radius = 10;
    public ShareDamageMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        radius = dat.getInt("radius");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("radius", radius);
        return dat;
    }

    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        if (event.getSource().getEntity() instanceof ServerPlayer){
            List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(event.getEntity(), radius);
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
