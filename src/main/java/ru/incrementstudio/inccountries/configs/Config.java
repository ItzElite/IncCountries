package ru.incrementstudio.inccountries.configs;

import com.google.common.base.Charsets;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.incrementstudio.inccountries.Logger;
import ru.incrementstudio.inccountries.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Config {
    private final File file;
    private FileConfiguration config = null;

    public Config(String path) {
        file = new File(path);
    }

    public FileConfiguration get() {
        if (config == null) {
            reload();
        }
        return config;
    }


    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
        final InputStream defConfigStream = Plugin.getInstance().getResource(file.getName());
        if (defConfigStream == null) {
            return;
        }
        config.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
    }

    public void save() {
        try {
            get().save(file);
        } catch (IOException e) {
            Logger.error("Не удалось сохранить файл: " + file);
        }
    }

    public void update() {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            Plugin.getInstance().saveResource(file.getName(), false);
        }
    }
}
