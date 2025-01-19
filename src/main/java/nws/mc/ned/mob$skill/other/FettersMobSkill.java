package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.register.effect.EffectRegister;
import nws.mc.ned.mob$skill.MobSkill;

public class FettersMobSkill extends MobSkill {
    public FettersMobSkill(String id) {
        super(id);
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {

        int tick = dat.getInt("tick");
        if (tick > 200){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                Player player = livingEntity.level().getNearestPlayer(livingEntity, 15);
                if (player != null) {
                    player.addEffect(new MobEffectInstance(EffectRegister.FETTERS.getDelegate(), 100, 1));
                }
            }
        }else {
            dat.putInt("tick",tick+1);
        }
    }
}
