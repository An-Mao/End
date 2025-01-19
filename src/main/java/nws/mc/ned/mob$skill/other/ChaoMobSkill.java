package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.register.effect.EffectRegister;
import nws.mc.ned.mob$skill.MobSkill;

public class ChaoMobSkill extends MobSkill {
    public ChaoMobSkill(String id) {
        super(id);
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {

        int t = dat.getInt("tick");
        if (t > 200){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                Player player = livingEntity.level().getNearestPlayer(livingEntity, 15);
                if (player != null) {
                    player.addEffect(new MobEffectInstance(EffectRegister.CHAO.getDelegate(), 100, 1));
                }
            }
        }else {
            dat.putInt("tick",t + 1);
        }

    }
}
