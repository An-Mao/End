package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class RepeatReduceMobSkill extends MobSkill {
    private float damageScale = 0.05F;
    private int maxScale = 20;
    public RepeatReduceMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        damageScale = dat.getFloat("damageScale");
        maxScale = dat.getInt("maxScale");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putFloat("damageScale", damageScale);
        dat.putInt("maxScale", maxScale);
        return dat;
    }
    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        int scale = dat.getInt(event.getSource().type().toString());
        event.setNewDamage(event.getNewDamage() * (1 - scale * damageScale));
        if (scale < maxScale) {
            dat.putInt(event.getSource().type().toString(), scale + 1);
        }
    }
}
