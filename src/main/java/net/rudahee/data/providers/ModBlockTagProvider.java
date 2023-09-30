package net.rudahee.data.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rudahee.EmdTest;
import net.rudahee.setup.registries.ModBlockRegister;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EmdTest.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        makePickaxeMineable(ModBlockRegister.ALUMINUM_BLOCK.get(), ModBlockRegister.ETTMETAL_BLOCK.get(),
                ModBlockRegister.ALUMINUM_ORE.get(), ModBlockRegister.ETTMETAL_ORE.get());
    }

    private void makePickaxeMineable(Block... items) {
        tag(BlockTags.create(new ResourceLocation("minecraft", "mineable/pickaxe"))).replace(false).add(items);
        tag(BlockTags.create(new ResourceLocation("minecraft", "needs_iron_tool"))).replace(false).add(items);
    }
}
