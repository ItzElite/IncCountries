package ru.incrementstudio.inccountries.utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemUtil {
    public static ItemStack getBorderItem() {
        return createItemStack(
                Material.BLACK_STAINED_GLASS_PANE,
                " ",
                1,
                new ArrayList<>(),
                List.of(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS),
                new HashMap<>() {{
                    put("id", "IncCountries");
                    put("tag", "border");
                }},
                true,
                false,
                false
        );
    }


    public static ItemStack createItemStack(Material material, String name, int amount, List<String> lore, List<ItemFlag> flags, HashMap<String, String> persistentData, boolean coloredName, boolean coloredLore, boolean enchanted) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(coloredName ? ColorUtil.toColor(name) : name);
        List<String> editedLore = new ArrayList<>(lore);
        itemMeta.setLore(coloredLore ? ColorUtil.toColor(editedLore) : editedLore);
        for (int i = 0; i < flags.size(); i++) {
            itemMeta.addItemFlags(flags.get(i));
        }
        for (String key : persistentData.keySet()) {
            itemMeta.getPersistentDataContainer().set(NamespacedKey.fromString(key), PersistentDataType.STRING, persistentData.get(key));
        }
        if (enchanted) {
            itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        }
        item.setItemMeta(itemMeta);
        return item;
    }
}
