package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.register.attribute.NEDAttributes;
import nws.mc.ned.lib.EntityHelper;
import nws.mc.ned.mob$skill.MobSkill;

import java.util.List;

public class ProliferateMobSkill extends MobSkill {
    //死亡后为附近同类生物提供攻击，移速，减伤
    public ProliferateMobSkill(String id) {
        super(id);
    }

    @Override
    public void livingDeath(LivingDeathEvent event, CompoundTag dat) {
        List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(event.getEntity());
        for (LivingEntity livingEntity :entities){
            AttributeHelper.setAttribute(livingEntity, Attributes.ATTACK_DAMAGE.value(),ATTRIBUTE_SKILL_ATTACK_DAMAGE,1.0D, AttributeModifier.Operation.ADD_VALUE);
            AttributeHelper.setAttribute(livingEntity, Attributes.MOVEMENT_SPEED.value(),ATTRIBUTE_SKILL_MOVE_SPEED,0.1D, AttributeModifier.Operation.ADD_VALUE);
            AttributeHelper.setAttribute(livingEntity, NEDAttributes.hurtDown,ATTRIBUTE_SKILL_HURT_DOWN,5D, AttributeModifier.Operation.ADD_VALUE);
        }
    }
}
