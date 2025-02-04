package nws.mc.ned.mob$enchant.skill;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.mob$enchant.skill.a.*;

import java.util.function.Function;

public class MobSkillRegister {

    public static final ResourceLocation KEY =  ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID, "mob_skill");
    public static final ResourceKey<Registry<MobSkill>> REGISTRY_KEY = ResourceKey.createRegistryKey(KEY);
    public static final Registry<MobSkill> REGISTRY = new RegistryBuilder<>(REGISTRY_KEY)
            .sync(true)
            .maxId(Integer.MAX_VALUE)
            .create();
    public static final DeferredRegister<MobSkill> MOB_SKILL = DeferredRegister.create(REGISTRY, NekoEnd.MOD_ID);


    //public static final Supplier<IForgeRegistry<MobSkill>> REGISTRY = MOB_SKILL.makeRegistry(RegistryBuilder::new);


    public static final DeferredHolder<MobSkill, SolitaryMobSkill> Solitary = reg("solitary", SolitaryMobSkill::new);
    public static final DeferredHolder<MobSkill, FireBlastMobSkill> FireBlast = reg("fire_blast", FireBlastMobSkill::new);
    public static final DeferredHolder<MobSkill, ChaosMoveMobSkill> ChaosMove = reg("chaos_move", ChaosMoveMobSkill::new);
    public static final DeferredHolder<MobSkill, PushAwayMobSkill> PushAway = reg("push_away", PushAwayMobSkill::new);
    public static final DeferredHolder<MobSkill, DeathBombMobSkill> DeathBomb = reg("death_bomb", DeathBombMobSkill::new);
    public static final DeferredHolder<MobSkill, IceArmorMobSkill> IceArmor = reg("ice_armor", IceArmorMobSkill::new);
    public static final DeferredHolder<MobSkill, BloodRageMobSkill> BloodRage = reg("blood_rage", BloodRageMobSkill::new);
    public static final DeferredHolder<MobSkill, LightningStrikeMobSkill> LightningStrike = reg("lightning_strike", LightningStrikeMobSkill::new);
    public static final DeferredHolder<MobSkill, WearDownMobSkill> WearDown = reg("wear_down", WearDownMobSkill::new);
    public static final DeferredHolder<MobSkill, DamageReflectMobSkill> DamageReflect = reg("damage_reflect", DamageReflectMobSkill::new);
    public static final DeferredHolder<MobSkill, SingleImmuneMobSkill> SingleImmune = reg("single_immune", SingleImmuneMobSkill::new);
    public static final DeferredHolder<MobSkill, StatDownMobSkill> StatDown = reg("stat_down", StatDownMobSkill::new);
    public static final DeferredHolder<MobSkill, DarkPoisonMobSkill> DarkPoison = reg("dark_poison", DarkPoisonMobSkill::new);
    public static final DeferredHolder<MobSkill, DarkBoostMobSkill> DarkBoost = reg("dark_boost", DarkBoostMobSkill::new);
    public static final DeferredHolder<MobSkill, BlinkDodgeMobSkill> BlinkDodge = reg("blink_dodge", BlinkDodgeMobSkill::new);
    public static final DeferredHolder<MobSkill, InvisibilityMobSkill> Invisibility = reg("invisibility", InvisibilityMobSkill::new);
    public static final DeferredHolder<MobSkill, CloseReduceMobSkill> CloseReduce = reg("close_reduce", CloseReduceMobSkill::new);
    public static final DeferredHolder<MobSkill, MagicReduceMobSkill> MagicReduce = reg("magic_reduce", MagicReduceMobSkill::new);
    public static final DeferredHolder<MobSkill, SpeedUpMobSkill> SpeedUp = reg("speed_up", SpeedUpMobSkill::new);
    public static final DeferredHolder<MobSkill, FarReduceMobSkill> FarReduce = reg("far_reduce", FarReduceMobSkill::new);
    public static final DeferredHolder<MobSkill, ImprisonMobSkill> Imprison = reg("imprison", ImprisonMobSkill::new);
    public static final DeferredHolder<MobSkill, FireRingMobSkill> FireRing = reg("fire_ring", FireRingMobSkill::new);
    public static final DeferredHolder<MobSkill, IceRingMobSkill> IceRing = reg("ice_ring", IceRingMobSkill::new);
    public static final DeferredHolder<MobSkill, IceSlowMobSkill> IceSlow = reg("ice_slow", IceSlowMobSkill::new);
    public static final DeferredHolder<MobSkill, ComboReflectMobSkill> ComboReflect = reg("combo_reflect", ComboReflectMobSkill::new);
    public static final DeferredHolder<MobSkill, CrossBlastMobSkill> CrossBlast = reg("cross_blast", CrossBlastMobSkill::new);
    public static final DeferredHolder<MobSkill, InvincibleAreaMobSkill> InvincibleArea = reg("invincible_area", InvincibleAreaMobSkill::new);
    public static final DeferredHolder<MobSkill, PoisonSpreadMobSkill> PoisonSpread = reg("poison_spread", PoisonSpreadMobSkill::new);
    public static final DeferredHolder<MobSkill, StopHealMobSkill> StopHeal = reg("stop_heal", StopHealMobSkill::new);
    public static final DeferredHolder<MobSkill, SingleDefendMobSkill> SingleDefend = reg("single_defend", SingleDefendMobSkill::new);
    public static final DeferredHolder<MobSkill, ThresholdReduceMobSkill> ThresholdReduce = reg("threshold_reduce", ThresholdReduceMobSkill::new);
    public static final DeferredHolder<MobSkill, AllyBoostMobSkill> AllyBoost = reg("ally_boost", AllyBoostMobSkill::new);
    public static final DeferredHolder<MobSkill, SelfBoostMobSkill> SelfBoost = reg("self_boost", SelfBoostMobSkill::new);
    public static final DeferredHolder<MobSkill, FrontDefendMobSkill> FrontDefend = reg("front_defend", FrontDefendMobSkill::new);
    public static final DeferredHolder<MobSkill, LifeStealMobSkill> LifeSteal = reg("life_steal", LifeStealMobSkill::new);
    public static final DeferredHolder<MobSkill, LowFrequencyReflectMobSkill> LowFrequencyReflect = reg("low_frequency_reflect", LowFrequencyReflectMobSkill::new);
    public static final DeferredHolder<MobSkill, MirrorImageMobSkill> MirrorImage = reg("mirror_image", MirrorImageMobSkill::new);
    public static final DeferredHolder<MobSkill, InvincibleMobSkill> Invincible = reg("invincible", InvincibleMobSkill::new);
    public static final DeferredHolder<MobSkill, RepeatReduceMobSkill> RepeatReduce = reg("repeat_reduce", RepeatReduceMobSkill::new);
    public static final DeferredHolder<MobSkill, OrderKillMobSkill> OrderKill = reg("order_kill", OrderKillMobSkill::new);
    public static final DeferredHolder<MobSkill, PoisonThrowMobSkill> PoisonThrow = reg("poison_throw", PoisonThrowMobSkill::new);
    public static final DeferredHolder<MobSkill, PoisonMistMobSkill> PoisonMist = reg("poison_mist", PoisonMistMobSkill::new);
    public static final DeferredHolder<MobSkill, AttackBoostMobSkill> AttackBoost = reg("attack_boost", AttackBoostMobSkill::new);
    public static final DeferredHolder<MobSkill, DeathBoostMobSkill> DeathBoost = reg("death_boost", DeathBoostMobSkill::new);
    public static final DeferredHolder<MobSkill, DeBuffCleanseMobSkill> DeBuffCleanse = reg("debuff_cleanse", DeBuffCleanseMobSkill::new);
    public static final DeferredHolder<MobSkill, ResurrectionMobSkill> Resurrection = reg("resurrection", ResurrectionMobSkill::new);
    public static final DeferredHolder<MobSkill, SelfHealMobSkill> SelfHeal = reg("self_heal", SelfHealMobSkill::new);
    public static final DeferredHolder<MobSkill, ReviveAllyMobSkill> ReviveAlly = reg("revive_ally", ReviveAllyMobSkill::new);
    public static final DeferredHolder<MobSkill, ForcePushMobSkill> ForcePush = reg("force_push", ForcePushMobSkill::new);
    public static final DeferredHolder<MobSkill, DamageReduceMobSkill> DamageReduce = reg("damage_reduce", DamageReduceMobSkill::new);
    public static final DeferredHolder<MobSkill, HealBlockMobSkill> HealBlock = reg("heal_block", HealBlockMobSkill::new);
    public static final DeferredHolder<MobSkill, BackDefendMobSkill> BackDefend = reg("back_defend", BackDefendMobSkill::new);
    public static final DeferredHolder<MobSkill, FireDefendMobSkill> FireDefend = reg("fire_defend", FireDefendMobSkill::new);
    public static final DeferredHolder<MobSkill, SpeedAdjustMobSkill> SpeedAdjust = reg("speed_adjust", SpeedAdjustMobSkill::new);
    public static final DeferredHolder<MobSkill, VoidDefendMobSkill> VoidDefend = reg("void_defend", VoidDefendMobSkill::new);
    public static final DeferredHolder<MobSkill, DirectDefendMobSkill> DirectDefend = reg("direct_defend", DirectDefendMobSkill::new);
    public static final DeferredHolder<MobSkill, DebuffDefendMobSkill> DebuffDefend = reg("debuff_defend", DebuffDefendMobSkill::new);
    public static final DeferredHolder<MobSkill, StealDurabilityMobSkill> StealDurability = reg("steal_durability", StealDurabilityMobSkill::new);
    public static final DeferredHolder<MobSkill, ShareDamageMobSkill> ShareDamage = reg("share_damage", ShareDamageMobSkill::new);
    public static final DeferredHolder<MobSkill, SummonMonsterMobSkill> SummonMonster = reg("summon_monster", SummonMonsterMobSkill::new);
    public static final DeferredHolder<MobSkill, DamageAuraMobSkill> DamageAura = reg("damage_aura", DamageAuraMobSkill::new);
    public static final DeferredHolder<MobSkill, AllyPowerMobSkill> AllyPower = reg("ally_power", AllyPowerMobSkill::new);
    public static final DeferredHolder<MobSkill, TeleportMobSkill> Teleport = reg("teleport", TeleportMobSkill::new);
    public static final DeferredHolder<MobSkill, PullMobSkill> Pull = reg("pull", PullMobSkill::new);
    public static final DeferredHolder<MobSkill, ThunderDefendMobSkill> ThunderDefend = reg("thunder_defend", ThunderDefendMobSkill::new);
    public static final DeferredHolder<MobSkill, WeaponLockMobSkill> WeaponLock = reg("weapon_lock", WeaponLockMobSkill::new);
    public static final DeferredHolder<MobSkill, FireTrailMobSkill> FireTrail = reg("fire_trail", FireTrailMobSkill::new);


    public static <T extends MobSkill> DeferredHolder<MobSkill,T> reg(String name , Function<String , T> mobSkillFunction){
        return MOB_SKILL.register(name, () -> mobSkillFunction.apply(name));
    }

    public static void register(IEventBus eventBus){
        MOB_SKILL.register(eventBus);
    }
}
