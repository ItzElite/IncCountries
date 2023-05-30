package ru.incrementstudio.inccountries;

import org.bukkit.ChatColor;
import ru.incrementstudio.inccountries.utils.ColorUtil;

public class Logger {
    public static void info(String string) {
        Plugin.getInstance().getLogger().info(ColorUtil.toColor(string));
    }

    public static void warn(String string) {
        Plugin.getInstance().getLogger().warning(ColorUtil.toColor(string));
    }

    public static void importantWarn(String string) {
        Plugin.getInstance().getLogger().warning(ChatColor.RED + ColorUtil.toColor(string));
    }

    public static void error(String string) {
        Plugin.getInstance().getLogger().info(ChatColor.RED + "ОШИБКА: " + ColorUtil.toColor(string));
    }

    public static void fatalError(String string) {
        Plugin.getInstance().getLogger().info(ChatColor.DARK_RED + "ФАТАЛЬНАЯ ОШИБКА: " + ColorUtil.toColor(string));
    }
}