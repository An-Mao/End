package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.register.attribute.NEDAttributes;
import nws.mc.ned.lib.EntityHelper;
import nws.mc.ned.mob$skill.MobSkill;

import java.util.List;

public class SymbiosisMobSkill extends MobSkill {
    public SymbiosisMobSkill(String id) {
        super(id);
    }


    @Override
    public void mobSpawn(FinalizeSpawnEvent event, CompoundTag dat) {
        if (!dat.getBoolean("NotFirst")){
            dat.putBoolean("NotFirst",true);
            List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(event.getEntity());
            double s = entities.size();
            double a = 1 + s * 0.3;
            AttributeHelper.setAttribute(event.getEntity(), Attributes.ATTACK_DAMAGE.value(),ATTRIBUTE_SKILL_ATTACK_DAMAGE,a, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            a = s * 10;
            AttributeHelper.setAttribute(event.getEntity(), NEDAttributes.hurtDown,ATTRIBUTE_SKILL_HURT_DOWN,a, AttributeModifier.Operation.ADD_VALUE);
        }
    }
}
