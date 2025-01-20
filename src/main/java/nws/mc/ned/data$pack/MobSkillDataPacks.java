package nws.mc.ned.data$pack;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import nws.mc.ned.NED;
import nws.mc.ned.mob$skill.MobSkill;

import java.util.HashMap;

public class MobSkillDataPacks {
    private static final HashMap<String, ResourceKey<MobSkillDataPack>> IDS = new HashMap<>();
    private static final HashMap<String, MobSkillDataPack> DATA = initData();

    private static HashMap<String, MobSkillDataPack> initData() {
        HashMap<String, MobSkillDataPack> data = new HashMap<>();
        /*
        data.put("chase_away", new MobSkillDataPack(false));
        data.put("corrosion", new MobSkillDataPack(false));
        data.put("frost_trail", new MobSkillDataPack(false));
        data.put("imperishable", new MobSkillDataPack(false));
        data.put("interruption_of_growth", new MobSkillDataPack(false));
        data.put("leaders", new MobSkillDataPack(false));
        data.put("left_guardian", new MobSkillDataPack(false));
        data.put("mirroring", new MobSkillDataPack(false));
        data.put("order", new MobSkillDataPack(false));
        data.put("reincarnation", new MobSkillDataPack(false));
        data.put("reverse_entropy", new MobSkillDataPack(false));
        data.put("right_blessing", new MobSkillDataPack(false));
        data.put("shield_of_chaos", new MobSkillDataPack(false));
        data.put("shield_of_purity", new MobSkillDataPack(false));
        data.put("steadfastness", new MobSkillDataPack(false));
        data.put("summon", new MobSkillDataPack(false));
        data.put("swirl", new MobSkillDataPack(false));
        data.put("teleportation", new MobSkillDataPack(false));
        data.put("tentacle", new MobSkillDataPack(false));

         */

        return data;
    }
    public static MobSkillDataPack getData(MobSkill mobSkill) {
        return DATA.getOrDefault(mobSkill.getId(), new MobSkillDataPack(mobSkill.getDefaultConfig()));
    }
    public static ResourceKey<MobSkillDataPack> getId(String id) {
        if (!IDS.containsKey(id)) IDS.put(id, ResourceKey.create(DataPackReg.MOB_SKILL_KEY, ResourceLocation.fromNamespaceAndPath(NED.MOD_ID, id)));
        return IDS.get(id);
    }


}
