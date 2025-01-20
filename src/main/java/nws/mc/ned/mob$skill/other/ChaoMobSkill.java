package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.register.effect.EffectRegister;
import nws.mc.ned.mob$skill.MobSkill;

public class ChaoMobSkill extends MobSkill {
    private int tick = 200;
    private int effectTime = 100;
    private double checkDistance = 15;
    public ChaoMobSkill(String id) {
        super(id);
    }
    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        checkDistance = dat.getDouble("checkDistance");
        effectTime = dat.getInt("effectTime");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putDouble("checkDistance",checkDistance);
        dat.putInt("effectTime",effectTime);
        return dat;
    }
    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {

        int t = dat.getInt("tick");
        if (t > this.tick){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                Player player = livingEntity.level().getNearestPlayer(livingEntity, checkDistance);
                if (player != null) {
                    player.addEffect(new MobEffectInstance(EffectRegister.CHAO.getDelegate(), effectTime, 1));
                }
            }
        }else {
            dat.putInt("tick",t + 1);
        }

    }
}
