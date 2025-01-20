package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.cores.helper.entity.EntityHelper;
import nws.mc.ned.register.attribute.NEDAttributes;
import nws.mc.ned.mob$skill.MobSkill;

import java.util.List;

public class SymbiosisMobSkill extends MobSkill {
    private double attackDamageBase = 0.3;
    private double hurtDown = 10;
    public SymbiosisMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        attackDamageBase = dat.getDouble("attackDamageBase");
        hurtDown = dat.getDouble("hurtDown");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putDouble("attackDamageBase",attackDamageBase);
        dat.putDouble("hurtDown",hurtDown);
        return dat;
    }

    @Override
    public void mobSpawn(FinalizeSpawnEvent event, CompoundTag dat) {
        if (!dat.getBoolean("NotFirst")){
            dat.putBoolean("NotFirst",true);
            List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(event.getEntity());
            double s = entities.size();
            double a = 1 + s * attackDamageBase;
            AttributeHelper.setAttribute(event.getEntity(), Attributes.ATTACK_DAMAGE.value(),ATTRIBUTE_SKILL_ATTACK_DAMAGE,a, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            a = s * hurtDown;
            AttributeHelper.setAttribute(event.getEntity(), NEDAttributes.hurtDown,ATTRIBUTE_SKILL_HURT_DOWN,a, AttributeModifier.Operation.ADD_VALUE);
        }
    }
}
