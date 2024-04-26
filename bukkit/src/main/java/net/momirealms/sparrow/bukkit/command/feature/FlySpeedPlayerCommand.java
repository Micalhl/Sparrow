package net.momirealms.sparrow.bukkit.command.feature;

import net.kyori.adventure.text.Component;
import net.momirealms.sparrow.bukkit.SparrowBukkitPlugin;
import net.momirealms.sparrow.bukkit.command.AbstractCommand;
import net.momirealms.sparrow.common.locale.Message;
import net.momirealms.sparrow.common.locale.TranslationManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.Command;
import org.incendo.cloud.bukkit.BukkitCommandManager;
import org.incendo.cloud.parser.standard.FloatParser;

public class FlySpeedPlayerCommand extends AbstractCommand {

    @Override
    public String getFeatureID() {
        return "flyspeed_player";
    }

    @Override
    public Command.Builder<? extends CommandSender> assembleCommand(BukkitCommandManager<CommandSender> manager, Command.Builder<CommandSender> builder) {
        return builder
                .senderType(Player.class)
                .required("speed", FloatParser.floatParser(-1, 1))
                .flag(manager.flagBuilder("silent").withAliases("s"))
                .handler(commandContext -> {
                    float speed = commandContext.get("speed");
                    commandContext.sender().setFlySpeed(speed);
                    SparrowBukkitPlugin.getInstance().getSenderFactory()
                            .wrap(commandContext.sender())
                            .sendMessage(
                                    TranslationManager.render(
                                            Message.COMMANDS_PLAYER_FLY_SPEED_SUCCESS
                                                    .arguments(Component.text(speed))
                                                    .build()
                                    ),
                                    true
                            );
                });
    }
}
