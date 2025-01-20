package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.dev.core.math._Math;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$skill.MobSkill;

public class ColdShieldMobSkill extends MobSkill {
    private int tick = 200;
    private double damage = 0.7;
    private double speed = 0.3;
    private float damageBase = 0.5F;
    private float damageScale = 0.2F;
    //大幅度减少冰以外的伤害
    //攻击力降低，攻速降低，对玩家造成伤害
    public ColdShieldMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        damage = dat.getDouble("damage");
        speed = dat.getDouble("speed");
        damageBase = dat.getFloat("damageBase");
        damageScale = dat.getFloat("damageScale");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putDouble("damage",damage);
        dat.putDouble("speed",speed);
        dat.putFloat("damageBase",damageBase);
        dat.putFloat("damageScale",damageScale);
        return dat;
    }

    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        if (!event.getSource().typeHolder().is(DamageTypes.FREEZE.location())){
            if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer){
                /*
                int r = _Math.RD.getIntRandomNumber(1,3);
                if (r == 1) {
                    AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_DAMAGE.value(),ATTRIBUTE_SKILL_ATTACK_DAMAGE,-0.7D,AttributeModifier.Operation.ADD_VALUE,200);
                }else if (r == 2){
                    AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_SPEED.value(),ATTRIBUTE_SKILL_ATTACK_SPEED,-0.3D,AttributeModifier.Operation.ADD_VALUE,200);
                }else {
                    serverPlayer.hurt(event.getEntity().damageSources().freeze(),event.getOriginalDamage() * 0.5F);
                }

                 */
                AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_DAMAGE.value(),ATTRIBUTE_SKILL_ATTACK_DAMAGE,-damage,AttributeModifier.Operation.ADD_VALUE,tick);
                AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_SPEED.value(),ATTRIBUTE_SKILL_ATTACK_SPEED,-speed,AttributeModifier.Operation.ADD_VALUE,tick);
                serverPlayer.hurt(event.getEntity().damageSources().freeze(),event.getOriginalDamage() * damageBase);
            }
            event.setNewDamage(event.getNewDamage() * damageScale);
        }
    }
}
