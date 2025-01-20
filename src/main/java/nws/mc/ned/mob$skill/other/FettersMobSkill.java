package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.register.effect.EffectRegister;
import nws.mc.ned.mob$skill.MobSkill;

public class FettersMobSkill extends MobSkill {
    private int tick = 200;
    private int distance = 15;
    private int effectTime = 100;
    public FettersMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        distance = dat.getInt("distance");
        effectTime = dat.getInt("effectTime");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putInt("distance",distance);
        dat.putInt("effectTime",effectTime);
        return dat;
    }
    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {

        int tick = dat.getInt("tick");
        if (tick > this.tick){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                Player player = livingEntity.level().getNearestPlayer(livingEntity, distance);
                if (player != null) {
                    player.addEffect(new MobEffectInstance(EffectRegister.FETTERS.getDelegate(), effectTime, 1));
                }
            }
        }else {
            dat.putInt("tick",tick+1);
        }
    }
}
