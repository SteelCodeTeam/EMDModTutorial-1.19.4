package net.rudahee.setup.registries;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.setup.Registration;

import java.util.function.Supplier;

public class ModItemsRegister {

    // Creo unas propiedades comunes para todos los ítems, en este caso que se stackeen de 64 en 64.
    private static Item.Properties COMMON_ITEM_PROPERTIES = new Item.Properties().stacksTo(64);

    // Creo los ítems en unos objetos RegistryObject<Item> del que los obtendremos cuando lo necesitemos.
    // Usando nuestro método, usando lambdas vamos a crear nuevos ítems con propiedades comunes.
    public static RegistryObject<Item> ALUMINUM_INGOT;
    public static RegistryObject<Item> ALUMINUM_NUGGET;
    public static RegistryObject<Item> ALUMINUM_RAW;

    // En los ítems de ettmetal extiendo las propiedades comunes para agregarle una rareza (Esto cambia como el color del texto, como pasa con el huevo del dragón)
    public static RegistryObject<Item> ETTMETAL_NUGGET;
    public static RegistryObject<Item> ETTMETAL;


    public static void register() {
        ALUMINUM_INGOT = registerItem("aluminum_ingot", () -> new Item(COMMON_ITEM_PROPERTIES));
        ALUMINUM_NUGGET = registerItem("aluminum_nugget", () -> new Item(COMMON_ITEM_PROPERTIES));
        ALUMINUM_RAW = registerItem("aluminum_raw", () -> new Item(COMMON_ITEM_PROPERTIES));
        ETTMETAL_NUGGET = registerItem("ettmetal_nugget", () -> new Item(COMMON_ITEM_PROPERTIES.rarity(Rarity.RARE)));
        ETTMETAL = registerItem("ettmetal", () -> new Item(COMMON_ITEM_PROPERTIES.rarity(Rarity.RARE)));

    }

    public static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> itemSupplier) {
        // Aquí llamamos a la instancia del ITEMS_DEFERED_REGISTER para registrar nuestros ítems con los valores que pasamos por parámetro:
        // El nombre del ítem y un supplier que nos devuelva el item.
        return Registration.ITEMS_DEFERRED_REGISTER.register(name, itemSupplier);
    }
}
