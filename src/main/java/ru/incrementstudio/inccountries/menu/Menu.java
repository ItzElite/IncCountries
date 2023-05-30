package ru.incrementstudio.inccountries.menu;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import ru.incrementstudio.inccountries.system.Country;
import ru.incrementstudio.inccountries.system.CountryManager;
import ru.incrementstudio.inccountries.utils.ItemUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Menu {
    private final Inventory inventory;
    private final int page;
    private final Player player;

    public Menu(String title, int size, int page, Player player) {
        this.page = page;
        this.player = player;
        inventory = Bukkit.createInventory(null, size, title);
        fill();
    }

    private void fill() {
        for (int i = 0; i < inventory.getSize(); i++) {
            if ((i < 9 || i > inventory.getSize() - 10) || (((i + 1) % 9) == 0 || (i % 9) == 0)) {
                if (inventory.getItem(i) == null) {
                    inventory.setItem(i, ItemUtil.getBorderItem());
                }
            }
        }
        getInventory().setItem(4, ItemUtil.createItemStack(
                Material.PAPER,
                "&dИнформация",
                1,
                PlaceholderAPI.setPlaceholders(getPlayer(), List.of("",
                        "&e&l| &fТекущая страна: %luckperms_prefix%.",
                        "&e&l| &fПрофиль: %luckperms_prefix% %player_name% %luckperms_suffix%")),
                List.of(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS),
                new HashMap<>() {{
                    put("id", "IncCountries");
                    put("tag", "border");
                }},
                true,
                true,
                true
        ));
        getInventory().setItem(49, ItemUtil.createItemStack(
                Material.RED_STAINED_GLASS_PANE,
                "&cЗакрыть",
                1,
                List.of("",
                        "&e&l| &fНажмите, чтобы закрыть",
                        "&e&l| &fЭто меню."),
                List.of(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS),
                new HashMap<>() {{
                    put("id", "IncCountries");
                    put("tag", "close");
                }},
                true,
                true,
                false
        ));
        List<Country> subkeys = new ArrayList<>();
        for (Country country: CountryManager.getCountriesList()) {
            subkeys.add(country);
        }
        List<Country> keys = new ArrayList<>();
        for (int i = getPage() * 28; i < (getPage() + 1) * 28 && i < subkeys.size(); i++) {
            keys.add(subkeys.get(i));
        }
        int offset = 10;
        for (int i = 0; i < keys.size(); i++) {
            if ((i + offset - 10 + 2) % 9 == 0) offset += 2;
            int finalI = i;
            getInventory().setItem(finalI + offset, ItemUtil.createItemStack(
                    keys.get(finalI).getIcon(),
                    "&e&l> &fСтрана - " + keys.get(finalI).getText(),
                    1,
                    PlaceholderAPI.setPlaceholders(getPlayer(), List.of(
                            "",
                            "&e&l| &fБудет выглядеть как: %luckperms_prefix% %player_name% %luckperms_suffix%",
                            "",
                            "&e&l| &fНажмите, чтобы выбрать страну."
                    )),
                    List.of(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS),
                    new HashMap<>() {{
                        put("id", "IncCountries");
                        put("tag", "country");
                        put("text", keys.get(finalI).getText());
                    }},
                    true,
                    true,
                    false
            ));
        }
        if (subkeys.size() >= 28 * (getPage() + 1)) {
            getInventory().setItem(26, ItemUtil.createItemStack(
                    Material.LIGHT_BLUE_STAINED_GLASS_PANE,
                    "&bСлудующая страница",
                    1,
                    new ArrayList<>(),
                    List.of(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS),
                    new HashMap<>() {{
                        put("id", "IncCountries");
                        put("tag", "next_page");
                        put("page", String.valueOf(getPage() + 1));
                    }},
                    true,
                    false,
                    false
            ));
        }
        if (getPage() > 0) {
            getInventory().setItem(18, ItemUtil.createItemStack(
                    Material.LIGHT_BLUE_STAINED_GLASS_PANE,
                    "&bПредыдущая страница",
                    1,
                    new ArrayList<>(),
                    List.of(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS),
                    new HashMap<>() {{
                        put("id", "IncCountries");
                        put("tag", "previous_page");
                        put("page", String.valueOf(getPage() - 1));
                    }},
                    true,
                    false,
                    false
            ));
        }
    }
    public Inventory getInventory() {
        return inventory;
    }

    public int getPage() {
        return page;
    }

    public Player getPlayer() {
        return player;
    }
}
