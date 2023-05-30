package ru.incrementstudio.inccountries.system;

import org.bukkit.Material;

public class Country {

    private final String configName;
    private final String text;
    private final Material icon;

    public Country(String configName, String text, Material icon) {
        this.configName = configName;
        this.text = text;
        this.icon = icon;
    }

    public String getConfigName() {
        return configName;
    }

    public String getText() {
        return text;
    }

    public Material getIcon() {
        return icon;
    }
}
