package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class NightmareMemoryMobSkill extends MobSkill {
    public NightmareMemoryMobSkill(String id) {
        super(id);
    }


    @Override
    public void livingDamagePre(LivingDamageEvent.Pre event, CompoundTag dat) {
        int scale = dat.getInt(event.getSource().type().toString());
        event.setNewDamage(event.getNewDamage() * (1 - scale * 0.05F));
        if (scale < 20) {
            dat.putInt(event.getSource().type().toString(), scale + 1);
        }
    }
}
