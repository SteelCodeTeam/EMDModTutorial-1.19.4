package net.rudahee.data.providers;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.EmdTest;
import net.rudahee.setup.registries.ModBlockRegister;

public class ModBlockStateProvider extends BlockStateProvider {

    // Nuestro constructor, muy parecido al de los items.
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EmdTest.MOD_ID, existingFileHelper);
    }

    // Metodo que nos generará los JSON.
    @Override
    protected void registerStatesAndModels() {

        // En este caso tenemos dos metodos en la clase padre, SimpleBlock y SimpleBlockItem.
        // SimpleBlock genera el json para el bloque automaticamente solo con pasarle el bloque.
        // SimpleBlockItem genera el json para el item relacionado al bloque y en este caso debemos pasarle
        // la localizacion de la textura a traves de un objeto ModelFile, en este caso lo haré unchecked para
        // ahorrarnos algunas configuraciones extra, si no lo encuentra, simplemente no se mostrara en el juego
        // sin mostrar ningun tipo de error.
        simpleBlock(ModBlockRegister.ALUMINUM_BLOCK.get());
        simpleBlockItem(ModBlockRegister.ALUMINUM_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/aluminum_block")));

        simpleBlock(ModBlockRegister.ALUMINUM_ORE.get());
        simpleBlockItem(ModBlockRegister.ALUMINUM_ORE.get(), new ModelFile.UncheckedModelFile(modLoc("block/aluminum_ore")));

        simpleBlock(ModBlockRegister.ETTMETAL_BLOCK.get());
        simpleBlockItem(ModBlockRegister.ETTMETAL_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/ettmetal_block")));

        simpleBlock(ModBlockRegister.ETTMETAL_ORE.get());
        simpleBlockItem(ModBlockRegister.ETTMETAL_ORE.get(), new ModelFile.UncheckedModelFile(modLoc("block/ettmetal_ore")));
    }
}
