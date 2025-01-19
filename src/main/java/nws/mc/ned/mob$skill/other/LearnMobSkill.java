package nws.mc.ned.mob$skill.other;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$skill.MobSkill;

public class LearnMobSkill extends MobSkill {
    public LearnMobSkill(String id) {
        super(id);
    }


    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > 600){
            dat.putInt("tick",0);
            int lvl = dat.getInt("lvl");
            if (lvl < 10){
                dat.putInt("lvl",lvl +1);
                if (event.getEntity() instanceof LivingEntity livingEntity){
                    AttributeHelper.setAttribute(livingEntity, Attributes.ATTACK_DAMAGE.value(),ATTRIBUTE_SKILL_ATTACK_DAMAGE,1.0D, AttributeModifier.Operation.ADD_VALUE);
                    AttributeHelper.setAttribute(livingEntity, Attributes.MOVEMENT_SPEED.value(),ATTRIBUTE_SKILL_MOVE_SPEED,0.1D, AttributeModifier.Operation.ADD_VALUE);
                }
            }
        }else {
            dat.putInt("tick",t + 1);
        }
    }
}
