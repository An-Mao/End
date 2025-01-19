package nws.mc.ned.event;

import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import nws.mc.ned.NED;
import nws.mc.ned.config.Configs;
import nws.mc.ned.mob$skill.MobSkillArgument;
import nws.mc.ned.mob$skill.MobSkillArgumentTypeInfo;
import nws.mc.ned.mob$skill.MobSkillRegister;
import nws.mc.ned.mob$skill.MobSkills;


@EventBusSubscriber(modid = NED.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
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
