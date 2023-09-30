package net.rudahee.modules.events;

import net.minecraftforge.event.CreativeModeTabEvent;
import net.rudahee.setup.registries.ModBlockRegister;
import net.rudahee.setup.registries.ModCreativeTabRegister;
import net.rudahee.setup.registries.ModItemsRegister;

public class ModCreativeTabEvents {

    // Como se explica arriba, recibimos un evento del tipo CreativeModeTabEvent.BuildContents

    public static void addContentsToModTab(CreativeModeTabEvent.BuildContents event) {


        // Si la pestaña es nuestra EMD_TEST_TAB, aquí podríais usar las pestañas de Minecraft

        // comparando con las constantes de la clase CreativeModeTabs.

        if (event.getTab().equals(ModCreativeTabRegister.EMD_TEST_TAB)) {


            // Con el método accept le decimos que acepte el ítem para esta pestaña y que por tanto

            // lo muestre. El orden en el que se definan aquí será el orden en las pestañas.

            event.accept(ModItemsRegister.ALUMINUM_INGOT);

            event.accept(ModItemsRegister.ALUMINUM_NUGGET);

            event.accept(ModItemsRegister.ALUMINUM_RAW);

            event.accept(ModItemsRegister.ETTMETAL);

            event.accept(ModItemsRegister.ETTMETAL_NUGGET);

            event.accept(ModBlockRegister.ALUMINUM_ORE);

            event.accept(ModBlockRegister.ALUMINUM_BLOCK);

            event.accept(ModBlockRegister.ETTMETAL_ORE);

            event.accept(ModBlockRegister.ETTMETAL_BLOCK);

        }
    }
}
