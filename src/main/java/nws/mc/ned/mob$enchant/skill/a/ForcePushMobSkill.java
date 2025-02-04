package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class ForcePushMobSkill extends MobSkill {
    private double knockBack = 2D;
    //增加击退距离
    public ForcePushMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        knockBack = dat.getDouble("knockBack");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putDouble("knockBack",knockBack);
        return dat;
    }

    @Override
    public void mobSpawn(FinalizeSpawnEvent event, CompoundTag dat) {
        if (!dat.getBoolean("notFirstSpawn")) {
            dat.putBoolean("notFirstSpawn",true);
            AttributeHelper.setAttribute(event.getEntity(), Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(getAttributeKey("knock_back.add"), knockBack, AttributeModifier.Operation.ADD_VALUE));
        }
    }
}
