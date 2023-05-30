package ru.incrementstudio.inccountries;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.incrementstudio.inccountries.commands.CountriesCommand;
import ru.incrementstudio.inccountries.commands.CountryCommand;
import ru.incrementstudio.inccountries.commands.CountryCompleter;
import ru.incrementstudio.inccountries.configs.Files;
import ru.incrementstudio.inccountries.menu.MenuEvents;
import ru.incrementstudio.inccountries.system.CountryManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public final class Plugin extends JavaPlugin {

    private static Plugin instance;
    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Logger.info("Загрузка плагина...");
        Files.updateAllFiles();
        CountryManager.load();
        Settings.load();
        getServer().getPluginManager().registerEvents(new MenuEvents(), this);
        getCommand("country").setExecutor(new CountryCommand());
        getCommand("country").setTabCompleter(new CountryCompleter());
        setCommand();
        Logger.info("Плагин включен!");
    }

    @Override
    public void onDisable() {
        Logger.info("Выключение плагина...");
        Files.saveAllFiles();
        Logger.info("Плагин выключен!");
    }

    public void setCommand() {
        try {
            PluginCommand command;
            CommandMap map = null;
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, org.bukkit.plugin.Plugin.class);
            c.setAccessible(true);
            command = c.newInstance(Settings.getCommand(), this);
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field f = SimplePluginManager.class.getDeclaredField("commandMap");
                f.setAccessible(true);
                map = (CommandMap) f.get(Bukkit.getPluginManager());
            }
            if (map != null) {
                map.register(this.getDescription().getName(), command);
            }
            command.setExecutor(new CountriesCommand());
        } catch (Exception exception) {
            Logger.error("Не удалось зарегистрировать команду для открытия меню выбора стран. Ошибка: ");
            exception.printStackTrace();
            try {
                Bukkit.getPluginManager().disablePlugin(this);
            } catch (Exception exception1) {
                Logger.error("Произошла ошибка при остановке плагина. Ошибка: ");
                exception1.printStackTrace();
            }
        }
    }
}
