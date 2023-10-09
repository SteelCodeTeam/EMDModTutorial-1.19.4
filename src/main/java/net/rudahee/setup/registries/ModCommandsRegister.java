package net.rudahee.setup.registries;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ModCommandsRegister {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("emd").requires(commandSourceStack -> commandSourceStack.hasPermission(0))
                .then(Commands.literal("give")
                    .then(Commands.literal("all items")
                        .executes(context -> getAllItems(context, List.of(context.getSource().getPlayerOrException())))
                        .then(Commands.argument("target", EntityArgument.players())
                                .executes(context -> getAllItems(context, EntityArgument.getPlayers(context, "target").stream().toList()))))
                    .then(Commands.literal("effects")
                            .executes(context -> applyEffects(context, List.of(context.getSource().getPlayerOrException())))
                            .then(Commands.argument("target", EntityArgument.players())
                                    .executes(context -> applyEffects(context, EntityArgument.getPlayers(context, "target").stream().toList()))))
                ));
    }

    private static int getAllItems(CommandContext<CommandSourceStack> context, List<ServerPlayer> targetPlayers) {

        StringBuilder playersName = new StringBuilder();
        boolean firstLoop = true;

        for (ServerPlayer player: targetPlayers) {
            if (firstLoop) {
                firstLoop = false;
                playersName.append(player.getScoreboardName());
            } else {
                playersName.append(", ").append(player.getScoreboardName());
            }

            player.getInventory().add(new ItemStack(ModItemsRegister.ETTMETAL.get(), 64));
            player.getInventory().add(new ItemStack(ModItemsRegister.ALUMINUM_INGOT.get(), 64));
        }

        context.getSource().sendSystemMessage(Component.translatable("command.message.emd_test.get_all_items").append(Component.literal(": "+ playersName)));

        return 1;
    }
    private static int applyEffects(CommandContext<CommandSourceStack> context, List<ServerPlayer> targetPlayers) {
        StringBuilder playersName = new StringBuilder();
        boolean firstLoop = true;

        for (ServerPlayer player: targetPlayers) {
            if (firstLoop) {
                firstLoop = false;
                playersName.append(player.getScoreboardName());
            } else {
                playersName.append(", ").append(player.getScoreboardName());
            }

            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, true, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0, true, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 0, true, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 300, 1, true, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 1, true, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 60, 0, true, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 600, 0, true, true, true));

        }

        context.getSource().sendSystemMessage(Component.translatable("command.message.emd_test.apply_effects").append(Component.literal(": "+ playersName)));

        return 1;

    }
}
