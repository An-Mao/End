package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class AttackBoostMobSkill extends MobSkill {
    private double attackDamage = 3D;

    //攻击力大幅提升
    public AttackBoostMobSkill(String id) {
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
        AttributeHelper.setAttribute(event.getEntity(), Attributes.ATTACK_DAMAGE,new AttributeModifier(getAttributeKey("attack_damage.add"), attackDamage, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }
}
