package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class PoisonSpreadMobSkill extends MobSkill {
    private int effectTime = 200;
    private int effectLevel = 1;
    public PoisonSpreadMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        effectLevel = dat.getInt("effectLevel");
        effectTime = dat.getInt("effectTime");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("effectLevel",effectLevel);
        dat.putInt("effectTime",effectTime);
        return dat;
    }
    @Override
    public void livingDamagePost(LivingDamageEvent.Post event, CompoundTag dat) {
        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.POISON,effectTime,effectLevel));
        }
    }
}
