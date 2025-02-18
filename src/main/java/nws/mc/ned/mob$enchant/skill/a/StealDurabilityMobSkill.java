package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import nws.mc.ned.mob$enchant.skill.MobSkill;

public class StealDurabilityMobSkill extends MobSkill {
    private double damageScale = 0.1;
    public StealDurabilityMobSkill(String id) {
        super(id);
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        damageScale = dat.getDouble("damageScale");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putDouble("damageScale",damageScale);
        return dat;
    }

    @Override
    public void livingDamagePost(LivingDamageEvent.Post event, CompoundTag dat) {
        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer){
            ItemStack itemStack = serverPlayer.getMainHandItem();
            if (itemStack != ItemStack.EMPTY && itemStack.isDamageableItem()){
                itemStack.setDamageValue((int) (itemStack.getDamageValue()+itemStack.getMaxDamage()*damageScale));
            }
        }
    }
}

