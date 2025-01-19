package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;

import java.util.Random;

public class DexterityMobSkill extends MobSkill {
    public DexterityMobSkill(String id) {
        super(id);
    }

    @Override
    public void livingIncomingDamage(LivingIncomingDamageEvent event, CompoundTag dat) {
        if (dat.getBoolean("withstand")) {
            dat.putBoolean("withstand",false);
            Random random = new Random();
            double offsetX = random.nextDouble() * 20 - 10;
            double offsetY = 0;
            double offsetZ = random.nextDouble() * 20 - 10;

            double teleportX = event.getEntity().getX() + offsetX;
            double teleportY = event.getEntity().getY() + offsetY;
            double teleportZ = event.getEntity().getZ() + offsetZ;
            event.getEntity().teleportTo(teleportX, teleportY, teleportZ);
            event.setCanceled(true);
        }
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > 140){
            dat.putInt("tick",0);
            dat.putBoolean("withstand",true);
        }else {
            dat.putInt("tick",t+1);
        }
    }
}
