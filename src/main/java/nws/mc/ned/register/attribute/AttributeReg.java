package nws.mc.ned.register.attribute;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nws.mc.ned.NekoEnd;

public class AttributeReg {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, NekoEnd.MOD_ID);
    public static final DeferredHolder<Attribute, Attribute> HURT_UP = ATTRIBUTES.register("hurt_up",()->Hurt.UP);
    public static final DeferredHolder<Attribute, Attribute> HURT_DOWN = ATTRIBUTES.register("hurt_down",()->Hurt.DOWN);
    public static void register(IEventBus eventBus){
        ATTRIBUTES.register(eventBus);
    }
}
