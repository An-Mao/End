package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class ComprehendMobSkill extends MobSkill {
    //生命低于某个值时，攻击力和移速大幅提升
    public ComprehendMobSkill(String id) {
        super(id);
    }


    @Override
    public void livingDamagePost(LivingDamageEvent.Post event, CompoundTag dat) {
        if (event.getEntity().getHealth() - event.getNewDamage() < event.getEntity().getMaxHealth() * 0.5){
            AttributeInstance att = event.getEntity().getAttribute(Attributes.ATTACK_DAMAGE);
            if (att != null) {
                att.addOrReplacePermanentModifier(new AttributeModifier(ResourceLocation.parse("ned:mob_skill.attack.damage") , 2D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }
            att = event.getEntity().getAttribute(Attributes.MOVEMENT_SPEED);
            if (att != null) {
                att.addOrReplacePermanentModifier(new AttributeModifier(ResourceLocation.parse("ned:mob_skill.move.speed"), 2D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }
        }
    }
}
