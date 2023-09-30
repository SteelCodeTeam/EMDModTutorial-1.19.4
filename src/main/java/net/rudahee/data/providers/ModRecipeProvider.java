package net.rudahee.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.rudahee.setup.registries.ModBlockRegister;
import net.rudahee.setup.registries.ModItemsRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    // Nuestro constructor por defecto.
    public ModRecipeProvider(DataGenerator generator) {
        super(generator.getPackOutput());
    }

    // El metodo que estamos obligados a implementar. Aqui vamos a crear nuestras recetas.
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> recipesConsumer) {

        // Vamos a crear primero las recetas de un bloque a 9 items. Para ello necesitamos una receta "sin forma",
        // ya que no nos importa el slot donde se coloque el bloque, el resultado siempre será el mismo, 9 lingotes.
        // Para hacer esto vamos a usar ShapelessRecipeBuilder#shapeless.
        // Los parametros de este metodo son: Tipo de receta, item resultante, cantidad del item resultante.
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItemsRegister.ALUMINUM_INGOT.get(), 9)
                // Tras eso, ponemos los items y bloques necesarios para la receta, en este caso solo el bloque.
                .requires(ModBlockRegister.ALUMINUM_BLOCK.get())
                // Ahora decimos cuando vamos a aprender esta receta en el libro de recetas de Minecraft,
                // en este caso, cuando tengas en el inventario un lingote de aluminio.
                .unlockedBy("has_item", has(ModItemsRegister.ALUMINUM_INGOT.get()))
                // Y para finalizar la guardamos con un nombre descriptivo. En mi caso siempre hago este tipo de nombres
                // Para evitar que se puedan generar errores (al coincidir con recetas de otros mods, o con otras recetas propias).
                .save(recipesConsumer, new ResourceLocation("aluminum_block_to_aluminum_ingot_recipe"));

        // Hacemos lo mismo que arriba, pero este caso para el ettmetal.
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItemsRegister.ETTMETAL.get(), 9)
                .requires(ModBlockRegister.ETTMETAL_BLOCK.get())
                .unlockedBy("has_item", has(ModItemsRegister.ETTMETAL.get()))
                .save(recipesConsumer, new ResourceLocation("ettmetal_block_to_ettmetal_ingot_recipe"));

        // Ahora vamos a plantear una receta donde hay un orden los items y los slots, una receta con una "forma" especifica.
        // Para eso usamos un #ShapedRecipeBuilder#shaped, para convertir 9 ingots en un bloque.
        // Los parametros de este metodo son: Tipo de receta, item resultante, cantidad del item resultante.
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlockRegister.ETTMETAL_BLOCK.get())
                // Con el metodo ShapedRecipeBuilder#define, definimos un caracter para representar el item en la receta.
                // Podemos llamar al define hasta 9 veces, una por slot de la mesa de crafteo. El caracter puede ser cualquier caracter imprimible.
                .define('#', ModItemsRegister.ETTMETAL.get())
                // El conjunto de tres llamadas a ShapedRecipeBuilder#pattern definen una cuadricula 3x3 que representa
                // la mesa de crafteo, y con ello podemos definir la receta, en nuestro caso son 3x3 ingots de ettmetal.
                .pattern("###")
                .pattern("###")
                .pattern("###")
                // Ahora decimos cuando vamos a aprender esta receta en el libro de recetas de Minecraft,
                // en este caso, cuando tengas en el inventario un lingote de ettmetal.
                .unlockedBy("has_block", has(ModItemsRegister.ETTMETAL.get()))
                // Y para finalizar la guardamos con un nombre descriptivo. En mi caso siempre hago este tipo de nombres
                // Para evitar que se puedan generar errores (al coincidir con recetas de otros mods, o con otras recetas propias).
                .save(recipesConsumer, new ResourceLocation("ettmetal_ingot_to_ettmetal_block"));

        // Esta receta funciona exactamente igual de la de arriba.
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlockRegister.ALUMINUM_BLOCK.get())
                .define('#', ModItemsRegister.ALUMINUM_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_block", has(ModItemsRegister.ALUMINUM_INGOT.get()))
                .save(recipesConsumer, new ResourceLocation("aluminum_ingot_to_aluminum_block"));

        // Las dos siguientes recetas son sin forma, exactamente igual que las dos primeras.
        // Son para transformar lingotes a pepitas.
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItemsRegister.ALUMINUM_NUGGET.get(), 9)
                .requires(ModItemsRegister.ALUMINUM_INGOT.get())
                .unlockedBy("has_item", has(ModItemsRegister.ALUMINUM_INGOT.get()))
                .save(recipesConsumer, new ResourceLocation("aluminum_ingot_to_aluminum_nugget_recipe"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItemsRegister.ALUMINUM_INGOT.get(), 9)
                .requires(ModItemsRegister.ETTMETAL.get())
                .unlockedBy("has_item", has(ModItemsRegister.ETTMETAL.get()))
                .save(recipesConsumer, new ResourceLocation("ettmetal_ingot_to_ettmetal_nugget_recipe"));

        // Las dos siguientes recetas son con forma especifica.
        // Son para transformar pepitas a lingotes.
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItemsRegister.ALUMINUM_INGOT.get())
                .define('#', ModItemsRegister.ALUMINUM_NUGGET.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(ModItemsRegister.ALUMINUM_NUGGET.get()))
                .save(recipesConsumer, new ResourceLocation("aluminum_nugget_to_aluminum_ingot"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItemsRegister.ETTMETAL.get())
                .define('#', ModItemsRegister.ETTMETAL_NUGGET.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(ModItemsRegister.ETTMETAL_NUGGET.get()))
                .save(recipesConsumer, new ResourceLocation("ettmetal_nugget_to_ettmetal_ingot"));

        // Estas recetas son para los distintos hornos y funcionan de forma muy similar.
        // Reciben los mismos parametros: Ingrediente a quemar (se obtiene con Ingredient#of y pasandole el item), tipo de receta,
        // el resultado de la receta, tiempo de cocinado, y experiencia recibida por realizar la receta.
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItemsRegister.ALUMINUM_RAW.get()), RecipeCategory.MISC, ModItemsRegister.ALUMINUM_INGOT.get(), 1f, 100)
                // Como antes, decidimos cuando desbloqueamos la receta, y le ponemos un nombre al JSON.
                .unlockedBy("has_item", has(ModItemsRegister.ALUMINUM_RAW.get()))
                .save(recipesConsumer, new ResourceLocation("cooking_aluminum_raw_to_ingot"));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItemsRegister.ALUMINUM_RAW.get()), RecipeCategory.MISC, ModItemsRegister.ALUMINUM_INGOT.get(), 0.5f, 200)
                .unlockedBy("has_item", has(ModItemsRegister.ALUMINUM_RAW.get()))
                .save(recipesConsumer, new ResourceLocation("blasting_aluminum_raw_to_ingot"));
    }
}
