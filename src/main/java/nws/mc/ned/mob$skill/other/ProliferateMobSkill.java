package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.cores.helper.entity.EntityHelper;
import nws.mc.ned.register.attribute.NEDAttributes;
import nws.mc.ned.mob$skill.MobSkill;

import java.util.List;

public class ProliferateMobSkill extends MobSkill {
    private double attackDamage = 1.0D;
    private double moveSpeed = 0.1D;
    private double hurtDown = 5D;
    //死亡后为附近同类生物提供攻击，移速，减伤
    public ProliferateMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        attackDamage = dat.getDouble("attackDamage");
        moveSpeed = dat.getDouble("moveSpeed");
        hurtDown = dat.getDouble("hurtDown");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putDouble("attackDamage",attackDamage);
        dat.putDouble("moveSpeed",moveSpeed);
        dat.putDouble("hurtDown",hurtDown);
        return dat;
    }

    @Override
    public void livingDeath(LivingDeathEvent event, CompoundTag dat) {
        List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(event.getEntity());
        for (LivingEntity livingEntity :entities){
            AttributeHelper.setAttribute(livingEntity, Attributes.ATTACK_DAMAGE.value(),ATTRIBUTE_SKILL_ATTACK_DAMAGE,attackDamage, AttributeModifier.Operation.ADD_VALUE);
            AttributeHelper.setAttribute(livingEntity, Attributes.MOVEMENT_SPEED.value(),ATTRIBUTE_SKILL_MOVE_SPEED,moveSpeed, AttributeModifier.Operation.ADD_VALUE);
            AttributeHelper.setAttribute(livingEntity, NEDAttributes.hurtDown,ATTRIBUTE_SKILL_HURT_DOWN,hurtDown, AttributeModifier.Operation.ADD_VALUE);
        }
    }
}
