package nws.mc.ned.register.data;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ItemExtraData {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final ItemExtraData EMPTY = new ItemExtraData(new CompoundTag());
    public static final Codec<ItemExtraData> CODEC = Codec.withAlternative(CompoundTag.CODEC, TagParser.AS_CODEC).xmap(ItemExtraData::new, p_327962_ -> p_327962_.tag);

    public static final StreamCodec<ByteBuf, ItemExtraData> STREAM_CODEC = ByteBufCodecs.COMPOUND_TAG.map(ItemExtraData::new, p_329964_ -> p_329964_.tag);



    private final CompoundTag tag;
    public ItemExtraData(CompoundTag nbt) {
        this.tag = nbt;
    }
    public static ItemExtraData of(CompoundTag pTag) {
        return new ItemExtraData(pTag.copy());
    }
    public boolean matchedBy(CompoundTag pTag) {
        return NbtUtils.compareNbt(pTag, this.tag, true);
    }



    public static Predicate<ItemStack> itemMatcher(DataComponentType<ItemExtraData> pComponentType, CompoundTag pTag) {
        return p_334391_ -> {
            ItemExtraData itemExtraData = p_334391_.getOrDefault(pComponentType, EMPTY);
            return itemExtraData.matchedBy(pTag);
        };
    }
    public ItemExtraData copy() {
        return new ItemExtraData(this.tag.copy());
    }
    public CompoundTag getTagCopy(){
        return this.tag.copy();
    }
    public CompoundTag getUnsafe() {
        return this.tag;
    }
    public CompoundTag get(String key) {
        return this.tag.getCompound(key);
    }
    public void put(String key, CompoundTag tag) {
        this.tag.put(key, tag);
    }
    public ItemExtraData update(Consumer<CompoundTag> pUpdater) {
        CompoundTag compoundtag = this.tag.copy();
        pUpdater.accept(compoundtag);
        return new ItemExtraData(compoundtag);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ItemExtraData itemExtraData)
            return NbtUtils.compareNbt(this.tag, itemExtraData.tag, true);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tag);
    }
}
