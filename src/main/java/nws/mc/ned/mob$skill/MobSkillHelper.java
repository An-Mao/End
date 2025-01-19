package nws.mc.ned.mob$skill;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import nws.dev.core.array._Array3D;
import nws.mc.ned.config.Configs;
import nws.mc.ned.config.general.GeneralConfig;
import nws.mc.ned.data$pack.MobSkillDataPacks;
import nws.mc.ned.register.DataRegister;
import nws.mc.ned.register.MobSkillData;
import nws.mc.ned.register.net.NetRegister;
import nws.mc.net.core.NetCore;
import nws.mc.net.core.NetPack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class MobSkillHelper {
    private static final Random random = new Random();

    public static void requestData(LivingEntity entity) {
        if (!entity.hasData(DataRegister.MOB_SKILLS)) {
            entity.setData(DataRegister.MOB_SKILLS, new MobSkillData(new _Array3D<>()));
            CompoundTag data = new CompoundTag();
            data.putInt("entity", entity.getId());
            NetCore.sendToServer(NetPack.createClientPack(data, NetRegister.MOB_SKILL.get()));
        }
    }
    public static boolean canAddSkill(Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            String registryId = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();
            if (Configs.mobSkill().blackList().contains(registryId)) return false;
            if (Configs.mobSkill().whiteList().contains(registryId)) return true;
            if (Configs.mobSkill().onlyWhiteList()) return false;
            if (Configs.mobSkill().onlyMonster()) return livingEntity.getType().getCategory() == MobCategory.MONSTER || livingEntity instanceof Monster;
            else return random.nextInt(0,100) < Configs.mobSkill().probability();
        }
        return false;
    }


    public static boolean isEnable(Level level, String key) {
        return level.registryAccess().holderOrThrow(MobSkillDataPacks.getId(key)).value().enable();
    }

    public static Collection<Suggestion> getSkills(SuggestionsBuilder builder) {
        return getSkills(builder,"");
    }
    public static Collection<Suggestion> getSkills(SuggestionsBuilder builder,String in) {
        Collection<Suggestion> suggestions = new ArrayList<>();
        MobSkillRegister.REGISTRY.asHolderIdMap().forEach(mobSkillHolder -> {
            String k = mobSkillHolder.getKey().location().toString();
            if (!in.isEmpty() && !k.contains(in)) return;
            builder.suggest(k,mobSkillHolder.value().getName());
        } );
        return suggestions;
    }

    public static String getDescription(String key) {
        return Component.translatable(key).getString();
    }
}
