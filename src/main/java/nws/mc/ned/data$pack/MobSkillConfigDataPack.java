package nws.mc.ned.data$pack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public record MobSkillConfigDataPack(
        boolean enable,
        int probability,
        boolean onlyMonster,
        int mobMaxSkill,
        boolean onlyWhiteList,
        List<String> whiteList,
        List<String> blackList
        ) {
    public static final MobSkillConfigDataPack DEFAULT =  MobSkillConfigDataPack.create();
    public static final @Nullable Codec<MobSkillConfigDataPack> NO_SYNC_CODEC = null;
    public static final Codec<MobSkillConfigDataPack> CODEC = RecordCodecBuilder.create(
            mobSkillDataPackInstance -> mobSkillDataPackInstance.group(
                    Codec.BOOL.fieldOf("enable").forGetter(MobSkillConfigDataPack::enable),
                    Codec.INT.fieldOf("probability").forGetter(MobSkillConfigDataPack::probability),
                    Codec.BOOL.fieldOf("onlyMonster").forGetter(MobSkillConfigDataPack::onlyMonster),
                    Codec.INT.fieldOf("mobMaxSkill").forGetter(MobSkillConfigDataPack::mobMaxSkill),
                    Codec.BOOL.fieldOf("onlyWhiteList").forGetter(MobSkillConfigDataPack::onlyWhiteList),
                    Codec.list(Codec.STRING).fieldOf("whiteList").forGetter(MobSkillConfigDataPack::whiteList),
                    Codec.list(Codec.STRING).fieldOf("blackList").forGetter(MobSkillConfigDataPack::blackList)
            ).apply(mobSkillDataPackInstance, MobSkillConfigDataPack::new)
    );

    public static MobSkillConfigDataPack create(){
        return new MobSkillConfigDataPack(true,50,true,7,false,new ArrayList<>(),new ArrayList<>());
    }
}
