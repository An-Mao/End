package nws.mc.ned.mob$skill;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;
import nws.mc.ned.NED;
import nws.mc.ned.mob$skill.other.*;
import nws.mc.ned.mob$skill.other.ChaoMobSkill;
import nws.mc.ned.mob$skill.other.FettersMobSkill;
import nws.mc.ned.mob$skill.other.LifeAbsorbingMobSkill;
import nws.mc.ned.mob$skill.other.tentacle.TentacleMobSkill;
import nws.mc.ned.mob$skill.other.timid.TimidMobSkill;

import java.util.function.Function;

public class MobSkillRegister {

    public static final ResourceLocation KEY =  ResourceLocation.fromNamespaceAndPath(NED.MOD_ID, "mob_skill");
    public static final ResourceKey<Registry<MobSkill>> REGISTRY_KEY = ResourceKey.createRegistryKey(KEY);
    public static final Registry<MobSkill> REGISTRY = new RegistryBuilder<>(REGISTRY_KEY)
            .sync(true)
            .maxId(Integer.MAX_VALUE)
            .create();
    public static final DeferredRegister<MobSkill> MOB_SKILL = DeferredRegister.create(REGISTRY, NED.MOD_ID);


    //public static final Supplier<IForgeRegistry<MobSkill>> REGISTRY = MOB_SKILL.makeRegistry(RegistryBuilder::new);


    public static final DeferredHolder<MobSkill,AloneMobSkill> Alone = reg("alone", AloneMobSkill::new);
    public static final DeferredHolder<MobSkill,BombMobSkill> Bomb = reg("bomb", BombMobSkill::new);
    public static final DeferredHolder<MobSkill,ChaoMobSkill> Chao = reg("chao", ChaoMobSkill::new);
    public static final DeferredHolder<MobSkill,ChaseAwayMobSkill> ChaseAway = reg("chase_away", ChaseAwayMobSkill::new);
    public static final DeferredHolder<MobSkill,CohesionMobSkill> Cohesion = reg("cohesion", CohesionMobSkill::new);
    public static final DeferredHolder<MobSkill,ColdShieldMobSkill> ColdShield = reg("cold_shield", ColdShieldMobSkill::new);
    public static final DeferredHolder<MobSkill,ComprehendMobSkill> Comprehend = reg("comprehend", ComprehendMobSkill::new);
    public static final DeferredHolder<MobSkill,ConductivityMobSkill> Conductivity = reg("conductivity", ConductivityMobSkill::new);
    public static final DeferredHolder<MobSkill,CorrosionMobSkill> Corrosion = reg("corrosion", CorrosionMobSkill::new);
    public static final DeferredHolder<MobSkill,CounterstrikeMobSkill> Counterstrike = reg("counterstrike", CounterstrikeMobSkill::new);
    public static final DeferredHolder<MobSkill,CrystallographyMobSkill> Crystallography = reg("crystallography", CrystallographyMobSkill::new);
    public static final DeferredHolder<MobSkill,CurseMobSkill> Curse = reg("curse", CurseMobSkill::new);
    public static final DeferredHolder<MobSkill,DarkPoisonShieldMobSkill> DarkPoisonShield = reg("dark_poison_shield", DarkPoisonShieldMobSkill::new);
    public static final DeferredHolder<MobSkill,DarkPositionMobSkill> DarkPosition = reg("dark_position", DarkPositionMobSkill::new);
    public static final DeferredHolder<MobSkill,DexterityMobSkill> Dexterity = reg("dexterity", DexterityMobSkill::new);
    public static final DeferredHolder<MobSkill,DisappearMobSkill> Disappear = reg("disappear", DisappearMobSkill::new);
    public static final DeferredHolder<MobSkill,DislikeMobSkill> Dislike = reg("dislike", DislikeMobSkill::new);
    public static final DeferredHolder<MobSkill,EnergyShieldMobSkill> EnergyShield = reg("energy_shield", EnergyShieldMobSkill::new);
    public static final DeferredHolder<MobSkill,FastMobSkill> Fast = reg("fast", FastMobSkill::new);
    public static final DeferredHolder<MobSkill,FavoriteMobSkill> Favorite = reg("favorite", FavoriteMobSkill::new);
    public static final DeferredHolder<MobSkill,FettersMobSkill> Fetters = reg("fetters", FettersMobSkill::new);
    public static final DeferredHolder<MobSkill,FireBarrierMobSkill> FireBarrier = reg("fire_barrier", FireBarrierMobSkill::new);
    public static final DeferredHolder<MobSkill,FrostbiteMobSkill> Frostbite = reg("frostbite", FrostbiteMobSkill::new);
    public static final DeferredHolder<MobSkill,FrostTrailMobSkill> FrostTrail = reg("frost_trail", FrostTrailMobSkill::new);
    public static final DeferredHolder<MobSkill,HighFrequencyBoundaryMobSkill> HighFrequencyBoundary = reg("high_frequency_boundary", HighFrequencyBoundaryMobSkill::new);
    public static final DeferredHolder<MobSkill,IgniteMobSkill> Ignite = reg("ignite", IgniteMobSkill::new);
    public static final DeferredHolder<MobSkill,ImperishableMobSkill> Imperishable = reg("imperishable", ImperishableMobSkill::new);
    public static final DeferredHolder<MobSkill,InfectionMobSkill> Infection = reg("infection", InfectionMobSkill::new);
    public static final DeferredHolder<MobSkill,InterruptionOfGrowthMobSkill> InterruptionOfGrowth = reg("interruption_of_growth", InterruptionOfGrowthMobSkill::new);
    public static final DeferredHolder<MobSkill,IntrinsicQualityMobSkill> IntrinsicQuality = reg("intrinsic_quality", IntrinsicQualityMobSkill::new);
    public static final DeferredHolder<MobSkill,IroncladMobSkill> Ironclad = reg("ironclad", IroncladMobSkill::new);
    public static final DeferredHolder<MobSkill,LeadersMobSkill> Leaders = reg("leaders", LeadersMobSkill::new);
    public static final DeferredHolder<MobSkill,LearnMobSkill> LearnMobSkill = reg("learn", LearnMobSkill::new);
    public static final DeferredHolder<MobSkill,LeftGuardianMobSkill> LeftGuardian = reg("left_guardian", LeftGuardianMobSkill::new);
    public static final DeferredHolder<MobSkill,LifeAbsorbingMobSkill> LifeAbsorbing = reg("life_absorbing", LifeAbsorbingMobSkill::new);
    public static final DeferredHolder<MobSkill,LowFrequencyBoundaryMobSkill> LowFrequencyBoundary = reg("low_frequency_boundary", LowFrequencyBoundaryMobSkill::new);
    public static final DeferredHolder<MobSkill,MirroringMobSkill> Mirroring = reg("mirroring", MirroringMobSkill::new);
    public static final DeferredHolder<MobSkill,NewbornMobSkill> NewbornMobSkill = reg("newborn", NewbornMobSkill::new);
    public static final DeferredHolder<MobSkill,NightmareMemoryMobSkill> NightmareMemory = reg("nightmare_memory", NightmareMemoryMobSkill::new);
    public static final DeferredHolder<MobSkill,OrderMobSkill> Order = reg("order", OrderMobSkill::new);
    public static final DeferredHolder<MobSkill,PoisonedEggMobSkill> PoisonedEgg = reg("poisoned_egg", PoisonedEggMobSkill::new);
    public static final DeferredHolder<MobSkill,PoisonMistMobSkill> PoisonMist = reg("poison_mist", PoisonMistMobSkill::new);
    public static final DeferredHolder<MobSkill,PowerfulMobSkill> Powerful = reg("powerful", PowerfulMobSkill::new);
    public static final DeferredHolder<MobSkill,ProliferateMobSkill> Proliferate = reg("proliferate", ProliferateMobSkill::new);
    public static final DeferredHolder<MobSkill,RainbowPositionMobSkill> RainbowPosition = reg("rainbow_position", RainbowPositionMobSkill::new);
    public static final DeferredHolder<MobSkill,RebirthMobSkill> Rebirth = reg("rebirth", RebirthMobSkill::new);
    public static final DeferredHolder<MobSkill,RecoverMobSkill> Recover = reg("recover", RecoverMobSkill::new);
    public static final DeferredHolder<MobSkill,ReincarnationMobSkill> Reincarnation = reg("reincarnation", ReincarnationMobSkill::new);
    public static final DeferredHolder<MobSkill,RepulseMobSkill> Repulse = reg("repulse", RepulseMobSkill::new);
    public static final DeferredHolder<MobSkill,ResistantMobSkill> Resistant = reg("resistant", ResistantMobSkill::new);
    public static final DeferredHolder<MobSkill,ReverseEntropyMobSkill> ReverseEntropy = reg("reverse_entropy", ReverseEntropyMobSkill::new);
    public static final DeferredHolder<MobSkill,RightBlessingMobSkill> RightBlessing = reg("right_blessing", RightBlessingMobSkill::new);
    public static final DeferredHolder<MobSkill,SearingShieldMobSkill> SearingShield = reg("searing_shield", SearingShieldMobSkill::new);
    public static final DeferredHolder<MobSkill,SenseOfCompetitionMobSkill> SenseOfCompetition = reg("sense_of_competition", SenseOfCompetitionMobSkill::new);
    public static final DeferredHolder<MobSkill,ShieldOfChaosMobSkill> ShieldOfChaos = reg("shield_of_chaos", ShieldOfChaosMobSkill::new);
    public static final DeferredHolder<MobSkill,ShieldOfPurityMobSkill> ShieldOfPurity = reg("shield_of_purity", ShieldOfPurityMobSkill::new);
    public static final DeferredHolder<MobSkill,SteadfastnessMobSkill> Steadfastness = reg("steadfastness", SteadfastnessMobSkill::new);
    public static final DeferredHolder<MobSkill,StealMobSkill> Steal = reg("steal", StealMobSkill::new);
    public static final DeferredHolder<MobSkill,SubtotalMobSkill> Subtotal = reg("subtotal", SubtotalMobSkill::new);
    public static final DeferredHolder<MobSkill,SummonMobSkill> Summon = reg("summon", SummonMobSkill::new);
    public static final DeferredHolder<MobSkill,SwirlMobSkill> Swirl = reg("swirl", SwirlMobSkill::new);
    public static final DeferredHolder<MobSkill,SymbiosisMobSkill> Symbiosis = reg("symbiosis", SymbiosisMobSkill::new);
    public static final DeferredHolder<MobSkill,TeleportationMobSkill> Teleportation = reg("teleportation", TeleportationMobSkill::new);
    public static final DeferredHolder<MobSkill,TentacleMobSkill> Tentacle = reg("tentacle", TentacleMobSkill::new);
    public static final DeferredHolder<MobSkill,ThunderthornShieldMobSkill> ThunderthornShield = reg("thunderthorn_shield", ThunderthornShieldMobSkill::new);
    public static final DeferredHolder<MobSkill,TimidMobSkill> Timid = reg("timid", TimidMobSkill::new);
    public static final DeferredHolder<MobSkill,TrailOfFlamesMobSkill> TrailOfFlames = reg("trail_of_flames", TrailOfFlamesMobSkill::new);


    public static <T extends MobSkill> DeferredHolder<MobSkill,T> reg(String name , Function<String , T> mobSkillFunction){
        return MOB_SKILL.register(name, () -> mobSkillFunction.apply(name));
    }

    public static void register(IEventBus eventBus){
        MOB_SKILL.register(eventBus);
    }
}
