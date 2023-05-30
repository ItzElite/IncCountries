package ru.incrementstudio.inccountries.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.incrementstudio.inccountries.system.Country;
import ru.incrementstudio.inccountries.system.CountryManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (sender.hasPermission("countries.admin")) {
            if (args.length == 1) {
                return List.of(
                        "create",
                        "remove",
                        "reload"
                );
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("create")) {
                    List<String> stringList = new ArrayList<>();
                    stringList.add("country_id");
                    return stringList;
                } else if (args[0].equalsIgnoreCase("remove")) {
                    List<String> stringList = new ArrayList<>();
                    for (Country country: CountryManager.getCountriesList()) {
                        stringList.add(country.getConfigName());
                    }
                    return stringList;
                } else {
                    return null;
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("create")) {
                    return List.of(
                            "страна"
                    );
                }
            } else if (args.length == 4) {
                if (args[0].equalsIgnoreCase("create")) {
                    List<String> stringList = new ArrayList<>();
                    for (Material material : Material.values()) {
                        stringList.add(material.name());
                    }
                    return stringList;
                }
            }
        }
        return null;
    }
}
