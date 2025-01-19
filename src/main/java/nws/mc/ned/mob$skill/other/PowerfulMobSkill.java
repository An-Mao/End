package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$skill.MobSkill;

public class PowerfulMobSkill extends MobSkill {
    //攻击力大幅提升
    public PowerfulMobSkill(String id) {
        super(id);
    }


    @Override
    public void mobSpawn(FinalizeSpawnEvent event, CompoundTag dat) {
        event.getEntity();
        AttributeHelper.setAttribute(event.getEntity(), Attributes.ATTACK_DAMAGE.value(), ATTRIBUTE_SKILL_ATTACK_DAMAGE, 3D, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }
}
