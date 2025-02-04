package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.cores.helper.entity.EntityHelper;
import nws.mc.ned.mob$enchant.skill.MobSkill;
import nws.mc.ned.register.attribute.NEDAttributes;

import java.util.List;

public class DeathBoostMobSkill extends MobSkill {
    private double attackDamage = 1.0D;
    private double moveSpeed = 0.1D;
    private double hurtDown = 5D;
    //死亡后为附近同类生物提供攻击，移速，减伤
    public DeathBoostMobSkill(String id) {
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
            AttributeHelper.setAttribute(livingEntity, Attributes.ATTACK_DAMAGE,new AttributeModifier(getAttributeKey("attack_damage.add"),attackDamage, AttributeModifier.Operation.ADD_VALUE));
            AttributeHelper.setAttribute(livingEntity, Attributes.MOVEMENT_SPEED,new AttributeModifier(getAttributeKey("move_speed.add"),moveSpeed, AttributeModifier.Operation.ADD_VALUE));
            AttributeHelper.setAttribute(livingEntity, NEDAttributes.HURT_DOWN,new AttributeModifier(getAttributeKey("hurt_down.add"),hurtDown, AttributeModifier.Operation.ADD_VALUE));
        }
    }
}
