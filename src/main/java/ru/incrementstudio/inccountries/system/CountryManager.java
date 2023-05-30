package ru.incrementstudio.inccountries.system;

import org.bukkit.Material;
import ru.incrementstudio.inccountries.configs.Files;

import java.util.ArrayList;
import java.util.List;

public class CountryManager {
    private static final ArrayList<Country> countriesList = new ArrayList<>();

    public static void load() {
        for (String string: Files.countries.get().getConfigurationSection("").getKeys(false)) {
            loadCountry(string);
        }
    }

    private static void unload() {
        countriesList.clear();
    }

    public static void reload() {
        unload();
        load();
    }


    public static Country getCountryByName(String name){
        for (Country country: getCountriesList()) {
            if (country.getConfigName().equalsIgnoreCase(name)) return country;
        }
        return null;
    }

    public static void unloadCountry(String name) {
        countriesList.remove(getCountryByName(name));
    }

    public static void loadCountry(String name) {
        Country country = new Country(name, Files.countries.get().getString(name + ".text"), Material.valueOf(Files.countries.get().getString(name + ".icon")));
        if (country.getConfigName() != null && country.getText() != null && country.getIcon() != null) countriesList.add(country);
    }

    public static void reloadCountry(String name) {
        unloadCountry(name);
        loadCountry(name);
    }

    public static List<Country> getCountriesList(){
        return countriesList;
    }

}
