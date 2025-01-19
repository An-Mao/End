package nws.mc.ned.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import nws.mc.ned.NED;
import nws.mc.ned.register.item.ItemReg;

public class DataGen_ItemModelProvider extends ItemModelProvider {
    public DataGen_ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NED.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ItemReg.SWORD_OF_SOUND);
    }
    private <T extends Item> ItemModelBuilder simpleItem(DeferredHolder<Item,T> item){
        return withExistingParent(
                item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0",ResourceLocation.fromNamespaceAndPath(NED.MOD_ID,"item/"+item.getId().getPath())
                );
    }
}
