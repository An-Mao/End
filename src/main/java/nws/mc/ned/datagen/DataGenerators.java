package nws.mc.ned.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.data$pack.DataPackReg;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = NekoEnd.MOD_ID,bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();

        dataGenerator.addProvider(event.includeClient(),new DataGen_ItemModelProvider(packOutput,existingFileHelper));
        /*
        dataGenerator.addProvider(event.includeClient(),
                (DataProvider.Factory<DatapackBuiltinEntriesProvider>) pOutput -> new DatapackBuiltinEntriesProvider(
                        packOutput,
                        event.getLookupProvider(),
                        new RegistrySetBuilder().add(DataPack.MOB_SKILL_KEY, bootstrap->{
                            bootstrap.register(MobSkillDataPacks.ALONE,MobSkillDataPacks.ALONE_DATA);
                        }),
                        Set.of(NED.MOD_ID)
                ));


         */
        dataGenerator.addProvider(
                event.includeServer(),
                (DataProvider.Factory<DatapackBuiltinEntriesProvider>) pOutput -> new DatapackBuiltinEntriesProvider(packOutput,event.getLookupProvider(),
                        DataPackReg.getBuilder(),
                        Set.of(NekoEnd.MOD_ID))
        ).getRegistryProvider();

    }
}
