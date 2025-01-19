package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class InfectionMobSkill extends MobSkill {
    public InfectionMobSkill(String id) {
        super(id);
    }


    @Override
    public void livingDamagePost(LivingDamageEvent.Post event, CompoundTag dat) {
        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.POISON,200,1));
        }
    }
}
