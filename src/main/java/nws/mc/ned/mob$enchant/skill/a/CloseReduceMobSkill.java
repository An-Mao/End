package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class CloseReduceMobSkill extends MobSkill {
    private int distance = 5;
    private float damageScale = 0.2F;
    public CloseReduceMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        distance = dat.getInt("distance");
        damageScale = dat.getFloat("damageScale");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("distance", distance);
        dat.putFloat("damageScale", damageScale);
        return dat;
    }
    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        if (event.getSource().getEntity() != null && event.getEntity().distanceTo(event.getSource().getEntity()) < distance) {
            event.setNewDamage(event.getNewDamage() * damageScale);
        }
    }
}
