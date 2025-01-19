package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$skill.MobSkill;

public class RepulseMobSkill extends MobSkill {
    //增加击退距离
    public RepulseMobSkill(String id) {
        super(id);
    }


    @Override
    public void mobSpawn(FinalizeSpawnEvent event, CompoundTag dat) {
        if (!dat.getBoolean("notFirstSpawn")) {
            dat.putBoolean("notFirstSpawn",true);
            AttributeHelper.setAttribute(event.getEntity(), Attributes.KNOCKBACK_RESISTANCE.value(), ATTRIBUTE_SKILL_KNOCK_BACK, 2D, AttributeModifier.Operation.ADD_VALUE);
        }
    }
}
