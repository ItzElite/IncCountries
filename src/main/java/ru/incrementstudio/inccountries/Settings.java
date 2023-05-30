package ru.incrementstudio.inccountries;

import ru.incrementstudio.inccountries.configs.Files;

public class Settings {
    private static String command;
    private static String separator;
    private static String giveCommand;

    public static void load() {
        command = Files.config.get().getString("command");
        separator = Files.config.get().getString("separator");
        giveCommand = Files.config.get().getString("give-command");
    }

    public static String getCommand() {
        return command;
    }

    public static String getSeparator() {
        return separator;
    }

    public static String getGiveCommand() {
        return giveCommand;
    }
}
