package net.rudahee.data.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.rudahee.EmdTest;
import net.rudahee.setup.world_generation.ModConfigFeatures;
import net.rudahee.setup.world_generation.ModPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

// Nuestro provider de costumbre que nos va a generar los jsons.
public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {

    // Definimos un builder, al que le pasamos por parametros en los metodos RegistrySetBuilder#add tanto el configFeature,
    // como el placedFeature, asi como los metodos bootstrap que hemos creado previamente
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfigFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);

    // Generamos el constructor por defecto heredado de la clase DatapackBuiltInEntriesProvider.
    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(EmdTest.MOD_ID));
    }
}
