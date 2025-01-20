package nws.mc.ned.data$pack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;

public record MobSkillDataPack(boolean enable,int weight,CompoundTag data) {
    public static final CompoundTag EMPTY = new CompoundTag();
    public static final @Nullable Codec<MobSkillDataPack> NO_SYNC_CODEC = null;
    public static final Codec<MobSkillDataPack> CODEC = RecordCodecBuilder.create(
            mobSkillDataPackInstance -> mobSkillDataPackInstance.group(
                    Codec.BOOL.fieldOf("enable").forGetter(MobSkillDataPack::enable),
                    Codec.INT.fieldOf("weight").forGetter(MobSkillDataPack::weight),
                    CompoundTag.CODEC.fieldOf("data").forGetter(MobSkillDataPack::data)
                    ).apply(mobSkillDataPackInstance, MobSkillDataPack::new)
    );
    public MobSkillDataPack() { this(true,1,EMPTY); }
    public MobSkillDataPack(int weight) { this(true,weight,EMPTY); }
    public MobSkillDataPack(boolean enable) {
        this(enable,1,EMPTY);
    }
    public MobSkillDataPack(CompoundTag data) {
        this(true,1,data);
    }
}
