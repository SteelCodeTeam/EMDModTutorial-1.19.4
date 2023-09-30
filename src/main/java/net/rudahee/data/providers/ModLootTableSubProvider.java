package net.rudahee.data.providers;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import net.rudahee.EmdTest;
import net.rudahee.setup.registries.ModBlockRegister;
import net.rudahee.setup.registries.ModItemsRegister;

import java.util.function.BiConsumer;

public class ModLootTableSubProvider implements LootTableSubProvider {


    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> writer) {


        // Agregamos los bloques sencillos.
        addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(ModBlockRegister.ALUMINUM_BLOCK.get()).getPath(), ModBlockRegister.ALUMINUM_BLOCK.get());
        addSimpleBlock(writer, ForgeRegistries.BLOCKS.getKey(ModBlockRegister.ETTMETAL_BLOCK.get()).getPath(), ModBlockRegister.ETTMETAL_BLOCK.get());

        // Agregamos las menas de metal.
        addComplexBlock(writer, ForgeRegistries.BLOCKS.getKey(ModBlockRegister.ALUMINUM_ORE.get()).getPath(),
                ModBlockRegister.ALUMINUM_ORE.get(), ModItemsRegister.ALUMINUM_RAW.get(), 1, 3, 2);
        addComplexBlock(writer, ForgeRegistries.BLOCKS.getKey(ModBlockRegister.ETTMETAL_ORE.get()).getPath(),
                ModBlockRegister.ETTMETAL_ORE.get(), ModItemsRegister.ETTMETAL.get(), 1, 1, 3);
    }


    /*
     * Esto lo vamos a usar para implementar bloques muy sencillos, como un bloque de aluminio, que da igual como lo piques, siempre te dará 1 unidad.
     */
    protected static void addSimpleBlock(BiConsumer<ResourceLocation, LootTable.Builder> writer, String name, Block block) {
        // En los parametros a LootPool#name le pasamos el nombre de la loot table, en LootPool#setRolls le decimos la cantidad, en este caso una constante de una unidad.
        LootPool.Builder builder = LootPool.lootPool().name(name).setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block));

        // Despues solo indicamos donde se va a escribir la loot table, en este caso dentro de la carpeta loot_tables/blocks
        writer.accept(new ResourceLocation(EmdTest.MOD_ID, "blocks/" + name), LootTable.lootTable().withPool(builder));
    }

    protected static void addComplexBlock(BiConsumer<ResourceLocation, LootTable.Builder> writer, String name, Block block, Item lootItem, float min, float max, int bonus) {
        LootPool.Builder builder = LootPool
                .lootPool()
                // En el nombre, como arriba, será el nombre de la propia lootTable
                .name(name)
                // Por defecto debe dropear de X a Y items dependiendo del valor pasado por parametro.
                .setRolls(UniformGenerator.between(min, max))
                // Alternativamente creamos otro loot table si el jugador tiene el encantamiento de toque de seda.
                .add(AlternativesEntry.alternatives(LootItem
                        .lootTableItem(block)
                        .when(MatchTool.toolMatches(ItemPredicate.Builder
                                .item()
                                // Si tiene toque de seda, devolvemos una sola unidad.
                                .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH,
                                        MinMaxBounds.Ints.atLeast(1))))), LootItem
                        .lootTableItem(lootItem)
                        // Aqui volvemos a comentar entre que valores se encuentra el drop
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                        // Y en cuantas unidades mas se puede incrementar con fortuna.
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, bonus))
                        // Y que el item se dropee si hay una explosion.
                        .apply(ApplyExplosionDecay.explosionDecay())));


        writer.accept(new ResourceLocation(EmdTest.MOD_ID, "blocks/" + name), LootTable.lootTable().withPool(builder));
    }
}
