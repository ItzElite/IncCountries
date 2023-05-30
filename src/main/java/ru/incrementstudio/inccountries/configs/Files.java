package ru.incrementstudio.inccountries.configs;

public class Files {
    public final static Config config = new Config("plugins//IncCountries//config.yml");
    public final static Config messages = new Config("plugins//IncCountries//messages.yml");
    public final static Config countries = new Config("plugins//IncCountries//countries.yml");

    public static void reloadAllFiles() {
        config.reload();
        messages.reload();
        countries.reload();
    }

    public static void saveAllFiles() {
        config.save();
        messages.save();
        countries.save();
    }

    public static void updateAllFiles() {
        config.update();
        messages.update();
        countries.update();
    }
}
