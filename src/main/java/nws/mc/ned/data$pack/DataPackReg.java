package nws.mc.ned.data$pack;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import nws.mc.ned.NED;
import nws.mc.ned.mob$skill.MobSkills;
import nws.mc.ned.register.damage$type.DamageTypes;


@EventBusSubscriber(modid = NED.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataPackReg {
    public static final ResourceKey<Registry<MobSkillDataPack>> MOB_SKILL_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(NED.MOD_ID, "skill/mob.skill"));
    public static final ResourceKey<Registry<MobSkillConfigDataPack>> MOB_SKILL_Config = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(NED.MOD_ID, "skill/config"));


    public static final ResourceKey<MobSkillConfigDataPack> MOB_SKILL_CONFIG_DATA_PACK_RESOURCE_KEY = ResourceKey.create(DataPackReg.MOB_SKILL_Config, ResourceLocation.fromNamespaceAndPath(NED.MOD_ID, "mob.skill.config"));
    @SubscribeEvent
    public static void register(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(
                MOB_SKILL_KEY,
                MobSkillDataPack.CODEC,
                MobSkillDataPack.CODEC
        );
        event.dataPackRegistry(
                MOB_SKILL_Config,
                MobSkillConfigDataPack.CODEC,
                MobSkillConfigDataPack.CODEC
        );
    }
    public static RegistrySetBuilder getBuilder() {
        return new RegistrySetBuilder()
                .add(Registries.DAMAGE_TYPE, DamageTypes::reg)
                .add(DataPackReg.MOB_SKILL_Config, DataPackReg::regMobSkillConfig)
                .add(DataPackReg.MOB_SKILL_KEY, DataPackReg::regMobSkill);
    }
    private static void regMobSkill(BootstrapContext<MobSkillDataPack> bootstrap) {
        MobSkills.REGISTRY.forEach(mobSkill -> bootstrap.register(MobSkillDataPacks.getId(mobSkill.getId()),MobSkillDataPacks.getData(mobSkill.getId())));
    }
    private static void regMobSkillConfig(BootstrapContext<MobSkillConfigDataPack> bootstrap) {
        bootstrap.register(MOB_SKILL_CONFIG_DATA_PACK_RESOURCE_KEY,MobSkillConfigDataPack.DEFAULT);
    }
}
