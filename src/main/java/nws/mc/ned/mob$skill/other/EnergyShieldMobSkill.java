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

public class EnergyShieldMobSkill extends MobSkill {
    private int tick = 200;
    private double attackDamage = 0.7D;
    private double attackSpeed = 0.3D;
    private float damageBase = 0.5F;
    private float damageScale = 0.2F;
    public EnergyShieldMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        attackDamage = dat.getDouble("attackDamage");
        attackSpeed = dat.getDouble("attackSpeed");
        damageBase = dat.getFloat("damageBase");
        damageScale = dat.getFloat("damageScale");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putDouble("attackDamage",attackDamage);
        dat.putDouble("attackSpeed",attackSpeed);
        dat.putFloat("damageBase",damageBase);
        dat.putFloat("damageScale",damageScale);
        return dat;
    }
    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        if (!event.getSource().typeHolder().is(DamageTypes.MAGIC.location())){
            if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer){
                    AttributeHelper.setTempAttribute(serverPlayer, Attributes.ATTACK_DAMAGE.value(),ATTRIBUTE_SKILL_ATTACK_DAMAGE,-attackDamage, AttributeModifier.Operation.ADD_VALUE,tick);

                    AttributeHelper.setTempAttribute(serverPlayer,Attributes.ATTACK_SPEED.value(),ATTRIBUTE_SKILL_ATTACK_SPEED,-attackSpeed,AttributeModifier.Operation.ADD_VALUE,tick);

                    serverPlayer.hurt(event.getEntity().damageSources().magic(),event.getNewDamage() * damageBase);

            }
            event.setNewDamage(event.getNewDamage() * damageScale);
        }
    }
}
