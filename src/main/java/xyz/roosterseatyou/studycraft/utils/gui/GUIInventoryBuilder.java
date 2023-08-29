package xyz.roosterseatyou.studycraft.utils.gui;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class GUIInventoryBuilder {

        private InventoryType type;
        private Inventory inventory;

        public GUIInventoryBuilder(InventoryType type) {
            type = type;
            inventory = Bukkit.createInventory(null, type);

        }

        public void setItem(int slot, GUIButton item) {
            inventory.setItem(slot, item.getButtonItem());
        }

        public Inventory build() {
            return inventory;
        }
}
