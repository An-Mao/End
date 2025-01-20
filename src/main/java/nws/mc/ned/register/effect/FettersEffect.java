package nws.mc.ned.register.effect;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class FettersEffect extends MobEffect {
    protected FettersEffect() {
        super(MobEffectCategory.HARMFUL, 0xff0000);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            CompoundTag nbt = serverPlayer.getPersistentData();
            double x = nbt.getDouble("FettersX");
            double y = nbt.getDouble("FettersY");
            double z = nbt.getDouble("FettersZ");

            serverPlayer.teleportTo(x, y, z);
        }
        return super.applyEffectTick(pLivingEntity,pAmplifier);
    }

    @Override
    public void onEffectStarted(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer serverPlayer) {
            CompoundTag nbt = serverPlayer.getPersistentData();
            nbt.putDouble("FettersX", serverPlayer.getX());
            nbt.putDouble("FettersY", serverPlayer.getY());
            nbt.putDouble("FettersZ", serverPlayer.getZ());
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
