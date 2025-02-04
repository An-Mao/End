package nws.mc.ned.register.data;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import nws.dev.core.array._Array3D;
import nws.mc.ned.NekoEnd;

public class DataRegister {

    public static final DeferredRegister<DataComponentType<?>> DATAS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, NekoEnd.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>,DataComponentType<ItemExtraData>> Item_Extra_Data = DATAS.register("item_extra_data",()-> DataComponentType.<ItemExtraData>builder().persistent(ItemExtraData.CODEC).networkSynchronized(ItemExtraData.STREAM_CODEC).build());


    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPE_DEFERRED_REGISTER = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, NekoEnd.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<MobSkillData>> MOB_SKILLS =
            ATTACHMENT_TYPE_DEFERRED_REGISTER.register(
                    "mob_skills",
                    () -> AttachmentType.builder(() -> new MobSkillData(new _Array3D<>())).serialize(MobSkillData.CODECC).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<EasyEntityData>> EASY_ENTITY_DATA =
            ATTACHMENT_TYPE_DEFERRED_REGISTER.register(
                    "easy_entity_data",
                    () -> AttachmentType.builder(() -> new EasyEntityData()).serialize(EasyEntityData.CODEC).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<InvasionData>> INVASIONS =
            ATTACHMENT_TYPE_DEFERRED_REGISTER.register(
        "invasions",
        () -> AttachmentType.builder(()->new InvasionData()).serialize(InvasionData.CODEC).build()
    ) ;





    public static void register(IEventBus eventBus){
        DATAS.register(eventBus);
        ATTACHMENT_TYPE_DEFERRED_REGISTER.register(eventBus);
    }
}
