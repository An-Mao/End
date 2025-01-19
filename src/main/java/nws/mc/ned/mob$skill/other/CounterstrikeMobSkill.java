package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class CounterstrikeMobSkill extends MobSkill {
    public CounterstrikeMobSkill(String id) {
        super(id);
    }

    @Override
    public void livingDamagePost(LivingDamageEvent.Post event, CompoundTag dat) {
        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            serverPlayer.hurt(event.getEntity().damageSources().fellOutOfWorld(),event.getNewDamage() * 0.3F);
        }
    }
}
