package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class DamageReflectMobSkill extends MobSkill {
    private float damageBase = 0.3F;
    public DamageReflectMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        damageBase = dat.getFloat("damageBase");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putFloat("damageBase",damageBase);
        return dat;
    }

    @Override
    public void livingDamagePost(LivingDamageEvent.Post event, CompoundTag dat) {
        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            serverPlayer.hurt(event.getEntity().damageSources().genericKill(),event.getNewDamage() * damageBase);
        }
    }
}
