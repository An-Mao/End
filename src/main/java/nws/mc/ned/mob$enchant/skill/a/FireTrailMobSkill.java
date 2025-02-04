package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class FireTrailMobSkill extends MobSkill {
    public FireTrailMobSkill(String id) {
        super(id);
    }


    @Override
    public void entityInvulnerabilityCheck(EntityInvulnerabilityCheckEvent event, CompoundTag dat) {
        if (!event.getSource().typeHolder().is(DamageTypes.IN_FIRE.location())){
            event.setInvulnerable(true);
        }
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            BlockPos pos = event.getEntity().getOnPos();
            if (level.getBlockState(pos).isAir()) {
                level.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
            }
        }
    }
}
