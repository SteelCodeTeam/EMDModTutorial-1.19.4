package net.rudahee.setup.registries;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.setup.Registration;

import java.util.function.Supplier;

public class ModBlockRegister {

    // Como antes, creamos un Item.Properties común para los ítems.
    private static final Item.Properties COMMON_BLOCK_ITEM_PROPERTIES = new Item.Properties().stacksTo(64);

    // Aquí estamos creando dos propiedades distintas para los bloques, uno para las menas de donde vamos a minar nuestros ítems
    // y otro para los bloques que podremos [I]craftear[/I] al unir 9 lingotes en la mesa de [I]crafteo[/I]. La diferencia entre ellos es que uno define en qué material nos basamos y el otro, en el sonido al picarlos.
    // Debemos definir también la resistencia contra explosiones, si se pueden picar con cualquier herramienta y el tiempo que tardan en picarse.

    private static final BlockBehaviour.Properties COMMON_BLOCK_ORE_PROPERTIES
            = BlockBehaviour.Properties.of(Material.STONE).explosionResistance(15).destroyTime(2).requiresCorrectToolForDrops().sound(SoundType.STONE);
    private static final BlockBehaviour.Properties COMMON_BLOCK_METAL_PROPERTIES
            = BlockBehaviour.Properties.of(Material.HEAVY_METAL).explosionResistance(15).destroyTime(2).requiresCorrectToolForDrops().sound(SoundType.METAL);

    // Hacemos nuestros registros como hacíamos con los ítems, a través de los métodos auxiliares que encontramos abajo.
    public static RegistryObject<Block> ALUMINUM_ORE;
    public static RegistryObject<Block> ETTMETAL_ORE;

    public static RegistryObject<Block> ALUMINUM_BLOCK;
    public static RegistryObject<Block> ETTMETAL_BLOCK;

    public static void register() {
        ALUMINUM_ORE = registerBlock("aluminum_ore", () -> new Block(COMMON_BLOCK_ORE_PROPERTIES), COMMON_BLOCK_ITEM_PROPERTIES);
        ETTMETAL_ORE = registerBlock("ettmetal_ore", () -> new Block(COMMON_BLOCK_ORE_PROPERTIES), COMMON_BLOCK_ITEM_PROPERTIES);
        ALUMINUM_BLOCK = registerBlock("aluminum_block", () -> new Block(COMMON_BLOCK_METAL_PROPERTIES), COMMON_BLOCK_ITEM_PROPERTIES);
        ETTMETAL_BLOCK = registerBlock("ettmetal_block", () -> new Block(COMMON_BLOCK_METAL_PROPERTIES), COMMON_BLOCK_ITEM_PROPERTIES);

    }


    public static <T extends Block> RegistryObject<T> registerBlockNoItem(String name, Supplier<T> blockSupplier) {

        // En esta línea inyectamos el código al bus a través del DEFERRED_REGISTER de bloques que hemos creado previamente.
        return Registration.BLOCKS_DEFERRED_REGISTER.register(name, blockSupplier);
    }

    // Con este método auxiliar, recibimos 3 parámetros, el nombre del bloque y del ítem, el supplier del bloque y las propiedades del ítem
    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier, Item.Properties properties) {

        // Llamamos al registro del bloque en sí.
        RegistryObject<T> blockRegistered = registerBlockNoItem(name, blockSupplier);

        // Registramos el ítem tal y como vimos en el punto 2.1
        Registration.ITEMS_DEFERRED_REGISTER.register(name, () -> (new BlockItem(blockRegistered.get(), properties)));

        return blockRegistered;

    }

}
