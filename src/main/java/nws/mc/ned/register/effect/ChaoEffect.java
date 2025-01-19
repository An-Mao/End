package nws.mc.ned.register.effect;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import nws.dev.core.math._Math;
import org.jetbrains.annotations.NotNull;

public class ChaoEffect extends MobEffect {
    protected ChaoEffect() {
        super(MobEffectCategory.HARMFUL, 0xffff00);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof LocalPlayer localPlayer){
            double speed = 2.5;
            double theta = _Math.RD.getRandomDouble() * 2 * Math.PI;
            double phi = _Math.RD.getRandomDouble() * Math.PI;
            double xi = speed * Math.sin(phi) * Math.cos(theta);
            double zi = speed * Math.sin(phi) * Math.sin(theta);
            double distance = Math.sqrt(xi * xi + zi * zi);
            if (distance > 0.1) {
                localPlayer.lerpMotion(xi, 0, zi);
            } else {
                localPlayer.lerpMotion(0, 0, 0);
            }
        }
        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
