package net.rudahee.data.providers;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.rudahee.EmdTest;

public class ModEnglishLanguageProvider extends LanguageProvider {

    public ModEnglishLanguageProvider(PackOutput output, String locale) {
        super(output, EmdTest.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("block.emd_test.aluminum_block", "Block of Aluminum ");
        add("block.emd_test.ettmetal_block", "Block of Ettmetal");

        add("block.emd_test.ettmetal_ore", "Ettmetal ore");
        add("block.emd_test.aluminum_ore", "Aluminum ore");

        add("item.emd_test.aluminum_ingot", "Aluminum ingot");
        add("item.emd_test.ettmetal", "Ettmetal");

        add("item.emd_test.ettmetal_nugget", "Ettmetal nugget");
        add("item.emd_test.aluminum_nugget", "Aluminum nugget");

        add("item.emd_test.aluminum_raw", "Raw of Aluminum");
    }
}