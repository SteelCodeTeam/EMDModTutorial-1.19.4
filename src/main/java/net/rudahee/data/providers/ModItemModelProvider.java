package net.rudahee.data.providers;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.EmdTest;

public class ModItemModelProvider extends ItemModelProvider {

    // Constante para decirle el tipo de item que vamos a generar, en este caso es el item "por defecto".
    private final ModelFile MODEL_FILE = getExistingFile(mcLoc("item/generated"));

    // Constructor por defecto.
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), EmdTest.MOD_ID, existingFileHelper);
    }

    // Metodo que debemos implementar para que nos genere los distintos JSONs.
    @Override
    protected void registerModels() {

        // El primer parametro es el tipo de item, el segundo es donde se generará el JSON
        // y el tercero es donde hemos colocado la textura.
        // LLamadas a nuestro metodo que debe devolver un objeto de tipo ItemModelBuilder.
        builder(MODEL_FILE,"item/aluminum_nugget", "item/aluminum_nugget");
        builder(MODEL_FILE,"item/aluminum_ingot", "item/aluminum_ingot");
        builder(MODEL_FILE,"item/aluminum_raw", "item/aluminum_raw");


        builder(MODEL_FILE,"item/ettmetal_nugget", "item/ettmetal_nugget");
        builder(MODEL_FILE,"item/ettmetal", "item/ettmetal_ingot");
    }

    // Metodo auxiliar para obtener el builder, y construir el json con los datos dados por parametros.
    // el metodo super#getBuilder nos da el un builder que estamos usando para decirle el tipo de item
    // que vamos a generar y donde se va a encontrar la textura.
    private ItemModelBuilder builder(ModelFile itemGenerated, String outPath, String texturePath) {
        return getBuilder(outPath).parent(itemGenerated).texture("layer0", texturePath);
    }
}
