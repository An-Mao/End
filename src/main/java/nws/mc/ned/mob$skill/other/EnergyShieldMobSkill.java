package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.dev.core.math._Math;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$skill.MobSkill;

public class EnergyShieldMobSkill extends MobSkill {
    public EnergyShieldMobSkill(String id) {
        super(id);
    }

    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        if (!event.getSource().typeHolder().is(DamageTypes.MAGIC.location())){
            if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer){
                int r = _Math.RD.getIntRandomNumber(1,3);
                if (r == 1) {
                    AttributeHelper.setTempAttribute(serverPlayer, Attributes.ATTACK_DAMAGE.value(),ATTRIBUTE_SKILL_ATTACK_DAMAGE,-0.7D, AttributeModifier.Operation.ADD_VALUE,200);
                }else if (r == 2){
                    AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_SPEED.value(),ATTRIBUTE_SKILL_ATTACK_SPEED,-0.3D,AttributeModifier.Operation.ADD_VALUE,200);
                }else {
                    serverPlayer.hurt(event.getEntity().damageSources().magic(),event.getNewDamage() * 0.5F);
                }
            }
            event.setNewDamage(event.getNewDamage() * 0.2F);
        }
    }
}
