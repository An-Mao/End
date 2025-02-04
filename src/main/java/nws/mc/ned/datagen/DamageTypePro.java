package nws.mc.ned.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.register.damage$type.DamageTypes;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DamageTypePro extends DatapackBuiltinEntriesProvider {

    public DamageTypePro(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output,registries,
                new RegistrySetBuilder()
                        .add(Registries.DAMAGE_TYPE, bootstrap -> bootstrap.register(DamageTypes.GONG, new DamageType(DamageTypes.GONG.location().toLanguageKey(),0)))
                        .add(Registries.DAMAGE_TYPE, bootstrap -> bootstrap.register(DamageTypes.SHANG, new DamageType(DamageTypes.SHANG.location().toLanguageKey(),0)))
                        .add(Registries.DAMAGE_TYPE, bootstrap -> bootstrap.register(DamageTypes.JUE, new DamageType(DamageTypes.JUE.location().toLanguageKey(),0)))
                        .add(Registries.DAMAGE_TYPE, bootstrap -> bootstrap.register(DamageTypes.ZHI, new DamageType(DamageTypes.ZHI.location().toLanguageKey(),0)))
                        .add(Registries.DAMAGE_TYPE, bootstrap -> bootstrap.register(DamageTypes.YU, new DamageType(DamageTypes.YU.location().toLanguageKey(),0)))
                , Set.of(NekoEnd.MOD_ID));
    }
}
