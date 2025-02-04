package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class LightningStrikeMobSkill extends MobSkill {
    public LightningStrikeMobSkill(String id) {
        super(id);
    }

    @Override
    public void livingDamagePost(LivingDamageEvent.Post event, CompoundTag dat) {
        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            //受到攻击召唤闪电
            LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(serverPlayer.level());
            if (lightningBolt != null) {
                lightningBolt.moveTo(serverPlayer.getX(),serverPlayer.getY(),serverPlayer.getZ());
                serverPlayer.level().addFreshEntity(lightningBolt);
            }
        }
    }
}
