package net.rudahee.setup;


/*
 *  CLASE: Registration.java
 */

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.rudahee.EmdTest;
import net.rudahee.modules.events.ModCreativeTabEvents;
import net.rudahee.setup.registries.ModBlockRegister;
import net.rudahee.setup.registries.ModItemsRegister;

public class Registration {
    /*
     *  Lo definimos como público y estático debido a que necesitamos acceder a esta constante cada vez que necesitemos agregar un ítem.
     *  En el método ForgeRegistries#create le pasamos que queremos registrar ítems y que nuestro MOD_ID para que sepa qué mod los va a estar registrando.
     */
    public static final DeferredRegister<Item> ITEMS_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, EmdTest.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, EmdTest.MOD_ID);

    /*
     * Con este método que recibe un parámetro IEventBus le decimos en que bus debe registrar los ítems que vamos a crear a continuación.
     */
    public static void register(IEventBus modEventBus) {
        ITEMS_DEFERRED_REGISTER.register(modEventBus);
        BLOCKS_DEFERRED_REGISTER.register(modEventBus);

        ModItemsRegister.register();
        ModBlockRegister.register();
    }
}