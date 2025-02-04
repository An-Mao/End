package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

import java.util.Random;

public class BlinkDodgeMobSkill extends MobSkill {
    private int tick = 140;
    private int teleportXRadius = 20;
    private int teleportZRadius = 20;
    private int teleportFix = 10;
    public BlinkDodgeMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        teleportXRadius = dat.getInt("teleportXRadius");
        teleportZRadius = dat.getInt("teleportZRadius");
        teleportFix = dat.getInt("teleportFix");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putInt("teleportXRadius",teleportXRadius);
        dat.putInt("teleportZRadius",teleportZRadius);
        dat.putInt("teleportFix",teleportFix);
        return dat;
    }
    @Override
    public void livingIncomingDamage(LivingIncomingDamageEvent event, CompoundTag dat) {
        if (dat.getBoolean("withstand")) {
            dat.putBoolean("withstand",false);
            Random random = new Random();
            double offsetX = random.nextDouble() * teleportXRadius - teleportFix;
            double offsetY = 0;
            double offsetZ = random.nextDouble() * teleportZRadius - teleportFix;

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
        if (t > this.tick){
            dat.putInt("tick",0);
            dat.putBoolean("withstand",true);
        }else {
            dat.putInt("tick",t+1);
        }
    }
}
