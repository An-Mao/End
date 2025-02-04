package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.cores.helper.entity.EntityHelper;
import nws.mc.ned.mob$enchant.skill.MobSkill;
import nws.mc.ned.register.attribute.NEDAttributes;

import java.util.List;

public class AllyPowerMobSkill extends MobSkill {
    private double attackDamageBase = 0.3;
    private double hurtDown = 10;
    public AllyPowerMobSkill(String id) {
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
            AttributeHelper.setAttribute(event.getEntity(), Attributes.ATTACK_DAMAGE,new AttributeModifier(getAttributeKey("attack_damage.multi_total"),a, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            a = s * hurtDown;
            AttributeHelper.setAttribute(event.getEntity(), NEDAttributes.HURT_DOWN,new AttributeModifier(getAttributeKey("hurt_down.add"),a, AttributeModifier.Operation.ADD_VALUE));
        }
    }
}
