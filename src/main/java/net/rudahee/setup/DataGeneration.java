package net.rudahee.setup;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.EmdTest;
import net.rudahee.data.providers.*;

import java.util.Collections;
import java.util.List;

// Esta etiqueta es así debido a que es un evento, lo explicaré en el punto 5.
@Mod.EventBusSubscriber(modid = EmdTest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {

    // Esta etiqueta debe estar ahí debido a que es un evento, lo explicaré en el punto 5.
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        // Obtenemos todo lo necesario del evento.
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Le pasamos los generadores al evento con el método DataGenerator#addProvider creando una nueva instancia del generador.
        gen.addProvider(event.includeServer(), new ModBlockStateProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModItemModelProvider(gen, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(gen));
        gen.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModLootTableSubProvider::new, LootContextParamSets.BLOCK))));

        // Agregamos un provider llamando a la misma clase para todas las variantes de español:
        // España, Argentina, Mexico, Uruguay, Venezuela y Chile.
        gen.addProvider(event.includeServer(), new ModSpanishLanguageProvider(packOutput, "es_es"));
        gen.addProvider(event.includeServer(), new ModSpanishLanguageProvider(packOutput, "es_ar"));
        gen.addProvider(event.includeServer(), new ModSpanishLanguageProvider(packOutput, "es_mx"));
        gen.addProvider(event.includeServer(), new ModSpanishLanguageProvider(packOutput, "es_uy"));
        gen.addProvider(event.includeServer(), new ModSpanishLanguageProvider(packOutput, "es_ve"));
        gen.addProvider(event.includeServer(), new ModSpanishLanguageProvider(packOutput, "es_cl"));

        // Agregamos un provider llamando a la misma clase para todas las variantes de ingles:
        // Estados unidos, Australia, Canadá y Reino unido.
        gen.addProvider(event.includeServer(), new ModEnglishLanguageProvider(packOutput, "en_us"));
        gen.addProvider(event.includeServer(), new ModEnglishLanguageProvider(packOutput, "en_au"));
        gen.addProvider(event.includeServer(), new ModEnglishLanguageProvider(packOutput, "en_ca"));
        gen.addProvider(event.includeServer(), new ModEnglishLanguageProvider(packOutput, "en_gb"));
    }
}
