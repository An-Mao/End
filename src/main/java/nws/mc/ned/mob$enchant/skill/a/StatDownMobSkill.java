package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$enchant.skill.MobSkill;
import nws.mc.ned.register.attribute.NEDAttributes;

public class StatDownMobSkill extends MobSkill {
    private int tick = 200;
    private double speed = 0.1D;
    private double damage = 0.5D;
    private double hurtUp = 5D;
    //降低移速，攻击力，减伤
    public StatDownMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        speed = dat.getDouble("speed");
        damage = dat.getDouble("damage");
        hurtUp = dat.getDouble("hurtUp");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putDouble("speed",speed);
        dat.putDouble("damage",damage);
        dat.putDouble("hurtUp",hurtUp);
        return dat;
    }
    @Override
    public void livingDeath(LivingDeathEvent event, CompoundTag dat) {
        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            AttributeHelper.setTempAttribute(serverPlayer,Attributes.MOVEMENT_SPEED,new AttributeModifier(getAttributeKey("move_speed.add"),-speed,AttributeModifier.Operation.ADD_VALUE),tick);
            AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_DAMAGE,new AttributeModifier(getAttributeKey("attack_damage.add_multi_total"),-damage,AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),tick);
            AttributeHelper.setTempAttribute(serverPlayer, NEDAttributes.HURT_UP,new AttributeModifier(getAttributeKey("hurt_up.add"),hurtUp,AttributeModifier.Operation.ADD_VALUE),tick);
        }
    }
}
