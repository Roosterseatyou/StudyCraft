package xyz.roosterseatyou.studycraft.utils.gui;

import org.bukkit.inventory.Inventory;

public class GUIInventoryBuilder<T extends Inventory> {

        private final T inventory;

        public GUIInventoryBuilder(T inventory) {
            this.inventory = inventory;
        }

        public GUIInventoryBuilder<T> setItem(int slot, GUIButton item) {
            inventory.setItem(slot, item.getButtonItem());
            return this;
        }

        public T build() {
            return inventory;
        }
}
