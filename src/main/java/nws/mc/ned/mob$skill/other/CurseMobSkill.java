package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.register.attribute.NEDAttributes;
import nws.mc.ned.mob$skill.MobSkill;

public class CurseMobSkill extends MobSkill {
    //降低移速，攻击力，减伤
    public CurseMobSkill(String id) {
        super(id);
    }

    @Override
    public void livingDeath(LivingDeathEvent event, CompoundTag dat) {
        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            AttributeHelper.setTempAttribute(serverPlayer,Attributes.MOVEMENT_SPEED.value(),ATTRIBUTE_SKILL_MOVE_SPEED,-0.1D,AttributeModifier.Operation.ADD_VALUE,200);
            AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_DAMAGE.value(),ATTRIBUTE_SKILL_ATTACK_DAMAGE,-0.5D,AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL,200);
            AttributeHelper.setTempAttribute(serverPlayer, NEDAttributes.hurtUp,ATTRIBUTE_SKILL_HURT_UP,5D,AttributeModifier.Operation.ADD_VALUE,200);
        }
    }
}
