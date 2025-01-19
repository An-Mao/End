package nws.mc.ned.register.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nws.mc.ned.NED;

public class EffectRegister {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, NED.MOD_ID);
    public static final DeferredHolder<MobEffect,FettersEffect> FETTERS = EFFECTS.register("fetters", FettersEffect::new);
    public static final DeferredHolder<MobEffect,ChaoEffect> CHAO = EFFECTS.register("chao", ChaoEffect::new);
    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }
}
