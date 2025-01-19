package nws.mc.ned.mob$skill.other.timid;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;
import nws.mc.ned.register.DataRegister;
import nws.mc.ned.register.ItemExtraData;

public class TimidMobSkill extends MobSkill {
    public TimidMobSkill(String id) {
        super(id);
    }

    @Override
    public void entityTickPre(EntityTickEvent.Pre event, CompoundTag dat) {
        int t = dat.getInt("tick");
        if (t > 200){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                Player player = livingEntity.level().getNearestPlayer(livingEntity, 10);
                if (player != null) {
                    ItemStack stack = player.getMainHandItem();
                    if (stack != ItemStack.EMPTY) {
                        ItemExtraData md = stack.getOrDefault(DataRegister.Item_Extra_Data.get(), new ItemExtraData(new CompoundTag()));
                        CompoundTag tag = md.getTagCopy();
                        tag.putLong("disarm", player.level().getGameTime());
                        stack.set(DataRegister.Item_Extra_Data.get(), new ItemExtraData(tag));
                    }
                }
            }
        }else {
            dat.putInt("tick",t + 1);
        }
    }
}
