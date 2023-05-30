package ru.incrementstudio.inccountries.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.incrementstudio.inccountries.Plugin;
import ru.incrementstudio.inccountries.Settings;
import ru.incrementstudio.inccountries.configs.Files;
import ru.incrementstudio.inccountries.system.CountryManager;
import ru.incrementstudio.inccountries.utils.ColorUtil;

public class CountryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("country")) {
            if (!sender.hasPermission("countries.admin")) {
                sender.sendMessage(ColorUtil.toColor(Files.messages.get().getString("no-permission")));
                return true;
            }
            if (args.length == 0) {
                for (String string: Files.messages.get().getStringList("help")) {
                    sender.sendMessage(ColorUtil.toColor(string));
                }
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                Files.reloadAllFiles();
                CountryManager.reload();
                Settings.load();
                Plugin.getInstance().setCommand();
                sender.sendMessage(ColorUtil.toColor(Files.messages.get().getString("reloaded")));
                return true;
            } else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
                String id = args[1];
                if (CountryManager.getCountryByName(id) == null) {
                    sender.sendMessage(ColorUtil.toColor(Files.messages.get().getString("country-not-found")));
                    return true;
                } else {
                    CountryManager.unloadCountry(id);
                    Files.countries.get().set(id, null);
                    Files.countries.save();
                    sender.sendMessage(ColorUtil.toColor(Files.messages.get().getString("country-removed")));
                    return true;
                }
            } else if (args.length == 4 && args[0].equalsIgnoreCase("create")) {
                String configName = args[1];
                String text = args[2];
                Material icon = null;
                try {
                    icon = Material.valueOf(args[3].toUpperCase());
                } catch (Exception exception) {
                    sender.sendMessage(ColorUtil.toColor(Files.messages.get().getString("country-add-error-icon")));
                    return true;
                }
                if (Files.countries.get().getConfigurationSection("").getKeys(false).contains(configName)) {
                    sender.sendMessage(ColorUtil.toColor(Files.messages.get().getString("country-already-exist")));
                    return true;
                }
                Files.countries.get().set(configName + ".text", text);
                Files.countries.get().set(configName + ".icon", icon.name());
                Files.countries.save();
                CountryManager.loadCountry(configName);
                sender.sendMessage(ColorUtil.toColor(Files.messages.get().getString("country-created")));
                return true;
            } else {
                for (String string: Files.messages.get().getStringList("help")) {
                    sender.sendMessage(ColorUtil.toColor(string));
                }
                return true;
            }
        }
        return false;
    }
}
