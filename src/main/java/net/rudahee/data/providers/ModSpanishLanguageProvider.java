package net.rudahee.data.providers;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.EmdTest;

public class ModSpanishLanguageProvider extends LanguageProvider {

    public ModSpanishLanguageProvider(PackOutput output, String locale) {
        super(output, EmdTest.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {

        // El primer parámetro suele tener el formato de "tipo_de_objeto"."mod_id"."nombre_de_registro". Si no ponemos una traducción,
        // se verá el nombre separado por puntos en el juego y podemos copiarlos y pegarlos, el segundo parámetro es el nombre que le queremos poner.
        add("block.emd_test.aluminum_block", "Bloque de aluminio");
        add("block.emd_test.ettmetal_block", "Bloque de ettmetal");

        add("block.emd_test.ettmetal_ore", "Mena de ettmetal");
        add("block.emd_test.aluminum_ore", "Mena de aluminio");

        add("item.emd_test.aluminum_ingot", "Lingote de aluminio");
        add("item.emd_test.ettmetal", "Ettmetal");

        add("item.emd_test.ettmetal_nugget", "Pepita de ettmetal");
        add("item.emd_test.aluminum_nugget", "Pepita de aluminio");

        add("item.emd_test.aluminum_raw", "Aluminio en crudo");
    }
}