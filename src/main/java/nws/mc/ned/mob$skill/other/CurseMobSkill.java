package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.register.attribute.NEDAttributes;
import nws.mc.ned.mob$skill.MobSkill;

public class CurseMobSkill extends MobSkill {
    private int tick = 200;
    private double speed = 0.1D;
    private double damage = 0.5D;
    private double hurtUp = 5D;
    //降低移速，攻击力，减伤
    public CurseMobSkill(String id) {
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
            AttributeHelper.setTempAttribute(serverPlayer,Attributes.MOVEMENT_SPEED.value(),ATTRIBUTE_SKILL_MOVE_SPEED,-speed,AttributeModifier.Operation.ADD_VALUE,tick);
            AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_DAMAGE.value(),ATTRIBUTE_SKILL_ATTACK_DAMAGE,-damage,AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL,tick);
            AttributeHelper.setTempAttribute(serverPlayer, NEDAttributes.hurtUp,ATTRIBUTE_SKILL_HURT_UP,hurtUp,AttributeModifier.Operation.ADD_VALUE,tick);
        }
    }
}
