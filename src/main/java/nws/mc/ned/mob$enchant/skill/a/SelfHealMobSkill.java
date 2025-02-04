package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class SelfHealMobSkill extends MobSkill {
    private int tick = 100;
    private float health = 2F;
    public SelfHealMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        health = dat.getFloat("health");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putFloat("health",health);
        return dat;
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > tick){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity){
                livingEntity.heal(health);
            }
        }else {
            dat.putInt("tick",t + 1);
        }
    }
}
