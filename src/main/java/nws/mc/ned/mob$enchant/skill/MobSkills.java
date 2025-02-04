package nws.mc.ned.mob$enchant.skill;

import com.mojang.logging.LogUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import nws.dev.core.math._WeightRandom;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.data$pack.MobSkillDataPack;
import nws.mc.ned.data$pack.MobSkillDataPacks;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MobSkills extends MobSkillRegister{
    private static final Logger LOGGER = LogUtils.getLogger();
    private static _WeightRandom<MobSkill> TotalWeight = null;
    public static MobSkill getMobSkill(String id){
        return getMobSkill(ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID, id));
    }
    public static MobSkill getMobSkill(ResourceLocation id){
        return REGISTRY.get(id);
    }
    public static MobSkill getRandomSkill(){
        if (TotalWeight == null) return null ;
        return TotalWeight.getRandom();
    }
    public static void setTotalWeight(_WeightRandom<MobSkill> totalWeight) {
        TotalWeight = totalWeight;
    }
    public static void load(RegistryAccess registryAccess){
        //if (TotalWeight != null) return;
        List<String> strings = new ArrayList<>();
        HashMap<MobSkill,Integer> weight = new HashMap<>();
        REGISTRY.forEach(mobSkill -> {
            String s = mobSkill.getId();
            MobSkillDataPack dataPack = registryAccess.holderOrThrow(MobSkillDataPacks.getId(s)).value();
            if (dataPack.enable()){
                mobSkill.loadDataPack(dataPack.data());
                if (!strings.contains(s)) {
                    strings.add(s);
                    if (dataPack.weight() < 0) throw new IllegalArgumentException("error: weight < 0 in " + s);
                    weight.put(getMobSkill(s),dataPack.weight());
                }
            }
        });
        if (!weight.isEmpty()) setTotalWeight(new _WeightRandom<>(weight));
        LOGGER.debug("Loaded {} mob skills", strings.size());
    }
}
