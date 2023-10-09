package net.rudahee.setup.world_generation;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.rudahee.EmdTest;
import net.rudahee.setup.registries.ModBlockRegister;

import java.util.List;

public class ModConfigFeatures {

    // Definimos las reglas para que sustituyan tipos de bloques concretos. En este caso vamos a crear una mena para
    // stone, y una meena para deepslate
    public static final RuleTest DEEPSLATE_REPLACE_RULE = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    public static final RuleTest STONE_REPLACE_RULE = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

    // Para el aluminio y ettmetal, vamos a crear las configuraciones, que incluyen, que bloque se va a reemplazar,
    // y nuestro bloque de mena del metal especifico.
    public static final List<OreConfiguration.TargetBlockState> ALUMINUM_ORE_CONFIG_STONE =  List.of(OreConfiguration.target(STONE_REPLACE_RULE, ModBlockRegister.ALUMINUM_ORE.get().defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINUM_ORE_STONE_KEY = registerKey("aluminum_ore_stone_key");

    public static final List<OreConfiguration.TargetBlockState> ETTMETAL_ORE_CONFIG_DEEPSLATE =  List.of(OreConfiguration.target(DEEPSLATE_REPLACE_RULE, ModBlockRegister.ETTMETAL_ORE.get().defaultBlockState()));
    public static final ResourceKey<ConfiguredFeature<?, ?>> ETTMETAL_ORE_DEEPSLATE_KEY = registerKey("ettmetal_ore_deepslate_key");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        // Los parametros del OreConfiguration#new son los siguientes: la configuracion, el tamaño de la veta, y la probabilidad de que no aparezcan con una cara expuesta al aire.
        register(context, ALUMINUM_ORE_STONE_KEY, Feature.ORE, new OreConfiguration(ALUMINUM_ORE_CONFIG_STONE, 7, 0.5f));
        register(context, ETTMETAL_ORE_DEEPSLATE_KEY, Feature.ORE, new OreConfiguration(ETTMETAL_ORE_CONFIG_DEEPSLATE, 4, 0.7f));
    }

    // Metodo auxiliar para crear una key en el json para que Minecraft sepa identificar que mod esta tocando la generacion de mundo.
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(EmdTest.MOD_ID, name));
    }

    // Aqui registramos las propias configuraciones de las menas. para ello, le vamos a pasar el context del metodo boostrap,
    // la key que hemos generado, el tipo de objeto que se generará en el mundo y la configuracion de ese tipo de objeto (en este caso una mena)
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
