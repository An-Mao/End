package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.cores.helper.attribute.AttributeHelper;
import nws.mc.ned.mob$skill.MobSkill;

public class SenseOfCompetitionMobSkill extends MobSkill {
    private int tick = 300;
    private double moveSpeed = 0.2;
    public SenseOfCompetitionMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        moveSpeed = dat.getDouble("moveSpeed");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putDouble("moveSpeed",moveSpeed);
        return dat;
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > tick){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                //LivingEntity livingEntity = event.getEntity();
                Player player = livingEntity.level().getNearestPlayer(livingEntity, 10);
                if (player != null) {
                    AttributeHelper.setTempAttribute(livingEntity, Attributes.MOVEMENT_SPEED.value(), ATTRIBUTE_SKILL_MOVE_SPEED, player.getAttribute(Attributes.MOVEMENT_SPEED).getValue() * moveSpeed, AttributeModifier.Operation.ADD_VALUE, tick);
                }
            }
        }else {
            dat.putInt("tick", t + 1);
        }
    }
}
