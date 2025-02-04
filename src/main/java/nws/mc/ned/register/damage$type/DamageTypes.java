package nws.mc.ned.register.damage$type;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import nws.mc.ned.NekoEnd;

public class DamageTypes {
    public static final String[] ALL = {
            "gong", "shang", "jue", "zhi", "yu"
    };
    public static final ResourceKey<DamageType> GONG =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID, "gong"));
    public static final ResourceKey<DamageType> SHANG =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID, "shang"));

    public static final ResourceKey<DamageType> JUE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID, "jue"));

    public static final ResourceKey<DamageType> ZHI =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID, "zhi"));

    public static final ResourceKey<DamageType> YU =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(NekoEnd.MOD_ID, "yu"));

    public static DamageSource getDamage(Entity causer, ResourceKey<DamageType> damageType) {
        return new DamageSource(
                causer.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(damageType),
                causer);
    }

    public static void reg(BootstrapContext<DamageType> bootstrap) {
        bootstrap.register(DamageTypes.GONG, new DamageType(DamageTypes.GONG.location().toLanguageKey(),0));
        bootstrap.register(DamageTypes.SHANG, new DamageType(DamageTypes.SHANG.location().toLanguageKey(),0));
        bootstrap.register(DamageTypes.JUE, new DamageType(DamageTypes.JUE.location().toLanguageKey(),0));
        bootstrap.register(DamageTypes.ZHI, new DamageType(DamageTypes.ZHI.location().toLanguageKey(),0));
        bootstrap.register(DamageTypes.YU, new DamageType(DamageTypes.YU.location().toLanguageKey(), 0));
    }
}
