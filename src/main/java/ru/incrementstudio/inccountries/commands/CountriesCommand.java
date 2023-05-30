package ru.incrementstudio.inccountries.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.incrementstudio.inccountries.Settings;
import ru.incrementstudio.inccountries.configs.Files;
import ru.incrementstudio.inccountries.menu.Menu;
import ru.incrementstudio.inccountries.utils.ColorUtil;

public class CountriesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase(Settings.getCommand())) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ColorUtil.toColor(Files.messages.get().getString("only-players")));
                return true;
            }
            if (!sender.hasPermission("countries.menu")) {
                sender.sendMessage(ColorUtil.toColor(Files.messages.get().getString("no-permission")));
                return true;
            }
            Player player = (Player) sender;
            player.openInventory(new Menu(ColorUtil.toColor("&0Выбор страны #1"), 54, 0, player).getInventory());
        }
        return false;
    }
}
