package nws.mc.ned.register.data;

import com.mojang.serialization.Codec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import org.jetbrains.annotations.NotNull;

public class EasyEntityData {
    public static final Codec<EasyEntityData> CODEC = Codec.withAlternative(CompoundTag.CODEC, TagParser.AS_CODEC).xmap(EasyEntityData::new, EasyEntityData::getCompoundTag);
    private final CompoundTag data;
    private CompoundTag synData;
    public EasyEntityData () {
        this.data = new CompoundTag();
        this.synData = new CompoundTag();
    }
    public EasyEntityData (CompoundTag nbt) {
        data = nbt.getCompound("data");
        synData = nbt.getCompound("synData");
    }
    public CompoundTag getCompoundTag() {
        CompoundTag nbt = new CompoundTag();
        nbt.put("data", this.data);
        nbt.put("synData", this.synData);
        return nbt;
    }
    public CompoundTag getData() {
        return data;
    }
    public CompoundTag getSynData() {
        return synData;
    }
    public void handlePacket(@NotNull CompoundTag dat) {
        synData = dat;
    }
}
