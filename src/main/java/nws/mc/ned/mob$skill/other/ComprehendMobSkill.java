package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class ComprehendMobSkill extends MobSkill {
    private float health = 0.5F;
    private double damage = 2;
    private double speed = 2;
    //生命低于某个值时，攻击力和移速大幅提升
    public ComprehendMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        health = dat.getFloat("health");
        damage = dat.getDouble("damage");
        speed = dat.getDouble("speed");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putFloat("health",health);
        dat.putDouble("damage",damage);
        dat.putDouble("speed",speed);
        return dat;
    }

    @Override
    public void livingDamagePost(LivingDamageEvent.Post event, CompoundTag dat) {
        if (event.getEntity().getHealth() - event.getNewDamage() < event.getEntity().getMaxHealth() * health){
            AttributeInstance att = event.getEntity().getAttribute(Attributes.ATTACK_DAMAGE);
            if (att != null) {
                att.addOrReplacePermanentModifier(new AttributeModifier(ResourceLocation.parse("ned:mob_skill.attack.damage") , damage, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }
            att = event.getEntity().getAttribute(Attributes.MOVEMENT_SPEED);
            if (att != null) {
                att.addOrReplacePermanentModifier(new AttributeModifier(ResourceLocation.parse("ned:mob_skill.move.speed"), speed, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            }
        }
    }
}
