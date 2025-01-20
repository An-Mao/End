package nws.mc.ned.mob$skill.other.timid;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import nws.mc.ned.NED;
import nws.mc.ned.register.data.DataRegister;
import nws.mc.ned.register.data.ItemExtraData;

@EventBusSubscriber(modid = NED.MOD_ID)
public class TimidMobSkillEvent {
    @SubscribeEvent
    public static void onPlayerUseItem(LivingEntityUseItemEvent event){
        if (event.getEntity() instanceof ServerPlayer serverPlayer){
            ItemStack itemStack = event.getItem();
            if (itemStack != ItemStack.EMPTY){
                ItemExtraData md = itemStack.get(DataRegister.Item_Extra_Data.get());
                if (md != null) {
                    long a = md.getTagCopy().getLong("disarm");
                    if (a > 0 && serverPlayer.level().getGameTime() - a < 100){
                        event.setDuration(0);//.setCanceled(true);
                    }
                }
            }
        }
    }
}
