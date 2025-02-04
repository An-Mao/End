package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class DarkPoisonMobSkill extends MobSkill {
    private int tick = 200;
    private double attackSpeed = 0.3D;
    private double attackDamage = 0.7D;
    private float damageBase = 0.5F;
    private float damageScale = 0.2F;
    public DarkPoisonMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        attackSpeed = dat.getDouble("attackSpeed");
        attackDamage = dat.getDouble("attackDamage");
        damageBase = dat.getFloat("damageBase");
        damageScale = dat.getFloat("damageScale");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putDouble("attackSpeed", attackSpeed);
        dat.putDouble("attackDamage", attackDamage);
        dat.putFloat("damageBase",damageBase);
        dat.putFloat("damageScale",damageScale);
        return dat;
    }
    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        if (!event.getSource().typeHolder().is(DamageTypes.THORNS.location())){
            if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer) {
                AttributeHelper.setTempAttribute(serverPlayer, Attributes.ATTACK_DAMAGE,new AttributeModifier(getAttributeKey("attack_damage.add"), -attackDamage, AttributeModifier.Operation.ADD_VALUE), tick);

                AttributeHelper.setTempAttribute(serverPlayer, Attributes.ATTACK_SPEED,new AttributeModifier(getAttributeKey("attack_speed.add"), -attackSpeed, AttributeModifier.Operation.ADD_VALUE), tick);

                serverPlayer.hurt(event.getEntity().damageSources().thorns(event.getEntity()), event.getNewDamage() * damageBase);

            }
            event.setNewDamage(event.getNewDamage() * damageScale);
        }
    }
}
