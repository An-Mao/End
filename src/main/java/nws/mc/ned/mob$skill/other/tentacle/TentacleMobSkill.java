package nws.mc.ned.mob$skill.other.tentacle;

import net.minecraft.nbt.CompoundTag;
import nws.mc.ned.mob$skill.MobSkill;

public class TentacleMobSkill extends MobSkill {
    private double moveSpeed = 0.2D;
    public TentacleMobSkill(String id) {
        super(id);
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

    public double getMoveSpeed() {
        return moveSpeed;
    }
}
