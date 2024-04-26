package net.momirealms.sparrow.bukkit.command.feature;

import net.kyori.adventure.text.Component;
import net.momirealms.sparrow.bukkit.SparrowBukkitPlugin;
import net.momirealms.sparrow.bukkit.command.AbstractCommand;
import net.momirealms.sparrow.bukkit.component.ShadedAdventureComponentWrapper;
import net.momirealms.sparrow.common.locale.Message;
import net.momirealms.sparrow.common.locale.TranslationManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.Command;
import org.incendo.cloud.bukkit.BukkitCommandManager;
import xyz.xenondevs.inventoryaccess.InventoryAccess;

public class EnderChestPlayerCommand extends AbstractCommand {

    @Override
    public String getFeatureID() {
        return "enderchest_player";
    }

    @Override
    public Command.Builder<? extends CommandSender> assembleCommand(BukkitCommandManager<CommandSender> manager, Command.Builder<CommandSender> builder) {
        return builder
                .senderType(Player.class)
                .handler(commandContext -> {
                    final Player player = commandContext.sender();
                    InventoryAccess.getInventoryUtils().openCustomInventory(player, player.getEnderChest(), new ShadedAdventureComponentWrapper(Component.translatable("container.enderchest")));
                    SparrowBukkitPlugin.getInstance().getSenderFactory()
                            .wrap(commandContext.sender())
                            .sendMessage(
                                    TranslationManager.render(
                                            Message.COMMANDS_PLAYER_ENDER_CHEST_SUCCESS.build()
                                    ),
                                    true
                            );
                });
    }
}
