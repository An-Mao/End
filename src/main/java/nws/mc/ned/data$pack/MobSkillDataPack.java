package nws.mc.ned.data$pack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.Nullable;

public record MobSkillDataPack(boolean enable,int weight) {
    public static final @Nullable Codec<MobSkillDataPack> NO_SYNC_CODEC = null;
    public static final Codec<MobSkillDataPack> CODEC = RecordCodecBuilder.create(
            mobSkillDataPackInstance -> mobSkillDataPackInstance.group(
                    Codec.BOOL.fieldOf("enable").forGetter(MobSkillDataPack::enable),
                    Codec.INT.fieldOf("weight").forGetter(MobSkillDataPack::weight)
                    ).apply(mobSkillDataPackInstance, MobSkillDataPack::new)
    );
    public MobSkillDataPack() { this(true,1); }
    public MobSkillDataPack(int weight) { this(true,weight); }

    public MobSkillDataPack(boolean enable) {
        this(enable,1);
    }
}
