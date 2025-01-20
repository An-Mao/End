package nws.mc.ned.mob$skill.other.timid;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.mc.ned.mob$skill.MobSkill;
import nws.mc.ned.register.data.DataRegister;
import nws.mc.ned.register.data.ItemExtraData;

public class TimidMobSkill extends MobSkill {
    private int tick = 200;
    private int distance = 10;
    public TimidMobSkill(String id) {
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
        int t = dat.getInt("tick");
        if (t > this.tick){
            dat.putInt("tick",0);
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                Player player = livingEntity.level().getNearestPlayer(livingEntity, distance);
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
