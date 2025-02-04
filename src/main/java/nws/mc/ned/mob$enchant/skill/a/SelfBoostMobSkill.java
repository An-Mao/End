package nws.mc.ned.mob$enchant.skill.a;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class SelfBoostMobSkill extends MobSkill {
    private int tick = 600;
    private double attackDamage = 1.0D;
    private double moveSpeed = 0.1D;
    public SelfBoostMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        attackDamage = dat.getDouble("attackDamage");
        moveSpeed = dat.getDouble("moveSpeed");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putDouble("attackDamage",attackDamage);
        dat.putDouble("moveSpeed",moveSpeed);
        return dat;
    }
    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > tick){
            dat.putInt("tick",0);
            int lvl = dat.getInt("lvl");
            if (lvl < 10){
                dat.putInt("lvl",lvl +1);
                if (event.getEntity() instanceof LivingEntity livingEntity){
                    AttributeHelper.setAttribute(livingEntity, Attributes.ATTACK_DAMAGE,new AttributeModifier(getAttributeKey("attack_damage.add"),attackDamage, AttributeModifier.Operation.ADD_VALUE));
                    AttributeHelper.setAttribute(livingEntity, Attributes.MOVEMENT_SPEED,new AttributeModifier(getAttributeKey("move_speed.add"),moveSpeed, AttributeModifier.Operation.ADD_VALUE));
                }
            }
        }else {
            dat.putInt("tick",t + 1);
        }
    }
}
