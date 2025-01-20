package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$skill.MobSkill;

public class PowerfulMobSkill extends MobSkill {
    private double attackDamage = 3D;

    //攻击力大幅提升
    public PowerfulMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        attackDamage = dat.getDouble("attackDamage");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putDouble("attackDamage", attackDamage);
        return dat;
    }

    @Override
    public void mobSpawn(FinalizeSpawnEvent event, CompoundTag dat) {
        event.getEntity();
        AttributeHelper.setAttribute(event.getEntity(), Attributes.ATTACK_DAMAGE.value(), ATTRIBUTE_SKILL_ATTACK_DAMAGE, attackDamage, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }
}
