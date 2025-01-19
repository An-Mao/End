package nws.mc.ned.mob$skill.other;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$skill.MobSkill;

public class StealMobSkill extends MobSkill {
    public StealMobSkill(String id) {
        super(id);
    }


    @Override
    public void livingDamagePost(LivingDamageEvent.Post event, CompoundTag dat) {
        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            ItemStack itemStack = serverPlayer.getMainHandItem();
            if (itemStack != ItemStack.EMPTY && itemStack.isDamageableItem()){
                itemStack.setDamageValue((int) (itemStack.getDamageValue()+itemStack.getMaxDamage()*0.1));
            }
        }
    }
}

