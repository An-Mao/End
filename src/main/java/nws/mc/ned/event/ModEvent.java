package nws.mc.ned.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.mob$enchant.skill.MobSkillRegister;


@EventBusSubscriber(modid = NekoEnd.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEvent {
    //private static final Oracles os = Oracles.getInstance();
    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event) {
        event.register(MobSkillRegister.REGISTRY);
    }
    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        /*
        event.enqueueWork(() -> {
            ArgumentTypeInfos.registerByClass(MobSkillArgument.class, new MobSkillArgumentTypeInfo());
        });

         */
    }
}
