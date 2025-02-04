package nws.mc.ned.register.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nws.mc.ned.NekoEnd;

public class ItemReg {

    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = DeferredRegister.create(Registries.ITEM, NekoEnd.MOD_ID);
    public static final DeferredHolder<Item, SwordOfSound> SWORD_OF_SOUND = ITEM_DEFERRED_REGISTER.register("sword_of_sound", SwordOfSound::new);
    public static void register(IEventBus eventBus){
        ITEM_DEFERRED_REGISTER.register(eventBus);
    }
}
