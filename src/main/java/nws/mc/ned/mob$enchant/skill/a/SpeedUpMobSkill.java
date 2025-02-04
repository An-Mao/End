package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class SpeedUpMobSkill extends MobSkill {
    private double moveSpeed = 0.5D;
    //移速大幅上升
    public SpeedUpMobSkill(String id) {
        super(id);
        //reg();
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        moveSpeed = dat.getDouble("moveSpeed");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putDouble("moveSpeed",moveSpeed);
        return dat;
    }
    @Override
    public void mobSpawn(FinalizeSpawnEvent event, CompoundTag dat) {
        if (!dat.getBoolean("NotFirst")){
            dat.putBoolean("NotFirst",true);
            AttributeHelper.setAttribute(event.getEntity(),Attributes.MOVEMENT_SPEED,new AttributeModifier(getAttributeKey("move_speed.add"),moveSpeed, AttributeModifier.Operation.ADD_VALUE));
        }
    }
}
