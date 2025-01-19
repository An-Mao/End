package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.register.attribute.NEDAttributes;
import nws.mc.ned.lib.EntityHelper;
import nws.mc.ned.mob$skill.MobSkill;

import java.util.List;

public class AloneMobSkill extends MobSkill {
    //怪物越少，攻击力，移速，减伤提升越多
    public AloneMobSkill(String id) {
        super(id);
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int tick = dat.getInt("tick");
        if (tick > 200){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity entity) {
                List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(entity);
                double s = 1d / entities.size();
                double a = 1 + s * 5;
                AttributeHelper.setTempAttribute(entity, Attributes.ATTACK_DAMAGE.value(), ATTRIBUTE_SKILL_ATTACK_DAMAGE, a, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, 180);
                a = 1 + s * 2;
                AttributeHelper.setTempAttribute(entity, Attributes.MOVEMENT_SPEED.value(), ATTRIBUTE_SKILL_MOVE_SPEED, a, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, 180);
                a = s * 50;
                AttributeHelper.setTempAttribute(entity, NEDAttributes.hurtDown, ATTRIBUTE_SKILL_HURT_DOWN, a, AttributeModifier.Operation.ADD_VALUE, 180);
            }
        }else {
            dat.putInt("tick",tick + 1);
        }
    }
}
