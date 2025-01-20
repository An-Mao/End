package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.cores.helper.entity.EntityHelper;
import nws.mc.ned.register.attribute.NEDAttributes;
import nws.mc.ned.mob$skill.MobSkill;

import java.util.List;

public class AloneMobSkill extends MobSkill {
    private int tick = 200;
    private int effectTick = 180;
    private double entityBase = 1;
    private double damage = 5;
    private double speed = 2;
    private double hurtDown = 50;
    //怪物越少，攻击力，移速，减伤提升越多
    public AloneMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        effectTick = dat.getInt("effectTick");
        entityBase = dat.getInt("entityBase");
        damage = dat.getDouble("damage");
        speed = dat.getDouble("speed");
        hurtDown = dat.getDouble("hurtDown");
        tick = dat.getInt("tick");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("effectTick",effectTick);
        dat.putInt("entityBase", (int) entityBase);
        dat.putInt("damage", (int) damage);
        dat.putInt("speed", (int) speed);
        dat.putInt("hurtDown", (int) hurtDown);
        dat.putInt("tick",tick);
        return dat;
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int tick = dat.getInt("tick");
        if (tick > this.tick){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity entity) {
                List<? extends LivingEntity> entities = EntityHelper.getLivingEntities(entity);
                double s = entityBase / entities.size();
                double a = 1 + s * damage;
                AttributeHelper.setTempAttribute(entity, Attributes.ATTACK_DAMAGE.value(), ATTRIBUTE_SKILL_ATTACK_DAMAGE, a, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, effectTick);
                a = 1 + s * speed;
                AttributeHelper.setTempAttribute(entity, Attributes.MOVEMENT_SPEED.value(), ATTRIBUTE_SKILL_MOVE_SPEED, a, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, effectTick);
                a = s * hurtDown;
                AttributeHelper.setTempAttribute(entity, NEDAttributes.hurtDown, ATTRIBUTE_SKILL_HURT_DOWN, a, AttributeModifier.Operation.ADD_VALUE, effectTick);
            }
        }else {
            dat.putInt("tick",tick + 1);
        }
    }


}
