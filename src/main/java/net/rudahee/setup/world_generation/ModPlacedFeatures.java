package net.rudahee.setup.world_generation;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.rudahee.EmdTest;

import java.util.List;

public class ModPlacedFeatures {

    // Estas keys deben coincidir con los Jsons que debemos crear nosotros a mano.
    public static final ResourceKey<PlacedFeature> ALUMINUM_PLACED_STONE_KEY = createKey("aluminum_placed_stone");
    public static final ResourceKey<PlacedFeature> ETTMETAL_PLACED_DEEPSLATE_KEY = createKey("ettmetal_placed_deepslate");



    public static void bootstrap(BootstapContext<PlacedFeature> context) {

        // Hacemos el holder con el que obtendremos las caracteristicas ya configuradas. Nosotros las creamos en la clase ModConfiguredFeatures.
        // Si estas no se crean correctamente, entonces recibiras una excepcion en la consola.
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // Aqui registramos ambas vetas de las metas de metal
        register(context, ALUMINUM_PLACED_STONE_KEY, configuredFeatures.getOrThrow(ModConfigFeatures.ALUMINUM_ORE_STONE_KEY),
                // Con el ModPlacement#commonOrePlacemente, definimos la cantidad de vetas que deben aparecer en un unico chunk,
                // asi como las alturas validas, en este caso yo tomo el la coordenada 0 desde lo mas profundo de Minecraft
                // (con el metodo aboveBottom, si puediera 10, estaría tomando la coordenada -55, al poner 0, tomo la coordenada -65,
                // el parametro suma esa cantidad de bloques a la coordenada en la que se empieza a generar la bedrock)
                // Y para finalizar, le pongo la coordenada absoluta 120, por tanto las menas se generaran de la coordenada -65 a 120.
                ModPlacement.commonOrePlacement(25, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(120))));

        register(context, ETTMETAL_PLACED_DEEPSLATE_KEY, configuredFeatures.getOrThrow(ModConfigFeatures.ETTMETAL_ORE_DEEPSLATE_KEY),
                ModPlacement.commonOrePlacement(12, // veins per chunk
                        // En este caso, aunque a partir de la coordenada 0 no hay deepslate, mantengo el 120,
                        // ya que no influye, no va a reemplazar nada de stone, ya que solo puede reemplazar la propia deepslate.
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(120))));
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(EmdTest.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}