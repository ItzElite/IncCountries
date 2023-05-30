package ru.incrementstudio.inccountries.menu;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import ru.incrementstudio.inccountries.Settings;
import ru.incrementstudio.inccountries.configs.Files;
import ru.incrementstudio.inccountries.utils.ColorUtil;
import ru.incrementstudio.inccountries.utils.ItemUtil;

public class MenuEvents implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().contains(ItemUtil.getBorderItem())) event.setCancelled(true);
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null || clickedItem.getItemMeta() == null) return;
        if (!clickedItem.getItemMeta().getPersistentDataContainer().has(NamespacedKey.fromString("id"), PersistentDataType.STRING) ||
                !clickedItem.getItemMeta().getPersistentDataContainer().get(NamespacedKey.fromString("id"), PersistentDataType.STRING).equals("IncCountries"))
            return;
        Player player = (Player) event.getWhoClicked();
        player.playSound(player.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON, 10, 10);
        if (clickedItem.getItemMeta().getPersistentDataContainer().has(NamespacedKey.fromString("tag"), PersistentDataType.STRING)) {
            String tag = clickedItem.getItemMeta().getPersistentDataContainer().get(NamespacedKey.fromString("tag"), PersistentDataType.STRING);
            if (tag.equals("border")) {
                event.setCancelled(true);
                return;
            }
            if (tag.equals("country")) {
                event.setCancelled(true);
                String text = clickedItem.getItemMeta().getPersistentDataContainer().get(NamespacedKey.fromString("text"), PersistentDataType.STRING);
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Settings.getGiveCommand()
                        .replace("%player%", player.getName())
                        .replace("%prefix%", text));
                player.sendMessage(ColorUtil.toColor(Files.messages.get().getString("country-chosen").replace("%name%", text)));
                return;
            }
            if (tag.equals("next_page")) {
                event.setCancelled(true);
                int page = Integer.parseInt(clickedItem.getItemMeta().getPersistentDataContainer().get(NamespacedKey.fromString("page"), PersistentDataType.STRING));
                int titlePage = page + 1;
                player.openInventory(new Menu(ColorUtil.toColor("&0Выбор страны #" + titlePage), 54, page, player).getInventory());
                return;
            }
            if (tag.equals("previous_page")) {
                event.setCancelled(true);
                int page = Integer.parseInt(clickedItem.getItemMeta().getPersistentDataContainer().get(NamespacedKey.fromString("page"), PersistentDataType.STRING));
                int titlePage = page + 1;
                player.openInventory(new Menu(ColorUtil.toColor("&0Выбор страны #" + titlePage), 54, page, player).getInventory());
                return;
            }
            if (tag.equals("close")) {
                event.setCancelled(true);
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (event.getInventory().contains(ItemUtil.getBorderItem())) event.setCancelled(true);
    }
}
