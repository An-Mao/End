package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class IceRingMobSkill extends MobSkill {
    private int tick = 200;
    private int distance = 15;
    public IceRingMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        tick = dat.getInt("tick");
        distance = dat.getInt("distance");
    }

    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putInt("tick",tick);
        dat.putInt("distance",distance);
        return dat;
    }
    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int tick = dat.getInt("tick");
        if (tick > this.tick) {
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                Player player = livingEntity.level().getNearestPlayer(livingEntity, distance);
                if (player != null) {
                    Level level = player.level();
                    BlockPos playerPos = player.blockPosition();
                    for (int xOffset = -1; xOffset <= 1; xOffset++) {
                        for (int yOffset = 0; yOffset <= 2; yOffset++) {
                            for (int zOffset = -1; zOffset <= 1; zOffset++) {
                                BlockPos snowPos = playerPos.offset(xOffset, yOffset, zOffset);
                                if (level.getBlockState(snowPos).isAir()) {
                                    level.setBlockAndUpdate(snowPos, Blocks.POWDER_SNOW.defaultBlockState());
                                }
                            }
                        }
                    }
                }
            }
        }else {
            dat.putInt("tick",tick+1);
        }
    }
}
