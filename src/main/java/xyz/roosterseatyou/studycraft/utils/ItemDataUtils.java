package xyz.roosterseatyou.studycraft.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import xyz.roosterseatyou.studycraft.StudyCraft;

import java.util.UUID;

public class ItemDataUtils {
    ItemStack itemStack;
    Player player;
    private static final StudyCraft plugin = StudyCraft.getInstance();

    /**
     * @param player    The player that is editing the item
     * @param itemStack The item that is being edited
     */
    public ItemDataUtils(Player player, ItemStack itemStack) {
        this.itemStack = itemStack;
        this.player = player;
    }

    /**
     * @param itemStack The item that is being edited
     * @apiNote this constructor is to be used when there is no player currently marked as editing the item
     *          In this case, the player will be considered as the owner, stored in the item's PDC
     */
    public ItemDataUtils(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void editStringData(String key, String value) {
        itemStack.editMeta(meta -> meta.getPersistentDataContainer().set(plugin.getNamespacedKey(key), PersistentDataType.STRING, value));
    }

    public void editIntData(String key, int value) {
        itemStack.editMeta(meta -> meta.getPersistentDataContainer().set(plugin.getNamespacedKey(key), PersistentDataType.INTEGER, value));
    }

    public void editDoubleData(String key, double value) {
        itemStack.editMeta(meta -> meta.getPersistentDataContainer().set(plugin.getNamespacedKey(key), PersistentDataType.DOUBLE, value));
    }

    public void editBooleanData(String key, boolean value) {
        itemStack.editMeta(meta -> meta.getPersistentDataContainer().set(plugin.getNamespacedKey(key), PersistentDataType.BYTE, (byte) (value ? 1 : 0)));
    }

    public void editUUIDData(String key, UUID value) {
        itemStack.editMeta(meta -> meta.getPersistentDataContainer().set(plugin.getNamespacedKey(key), PersistentDataType.STRING, value.toString()));
    }

    public String getStringData(String key) {
        return itemStack.getItemMeta().getPersistentDataContainer().get(plugin.getNamespacedKey(key), PersistentDataType.STRING);
    }

    public int getIntData(String key) {
        if(itemStack.getItemMeta().getPersistentDataContainer().get(plugin.getNamespacedKey(key), PersistentDataType.INTEGER) == null) return 0;
        return itemStack.getItemMeta().getPersistentDataContainer().get(plugin.getNamespacedKey(key), PersistentDataType.INTEGER);
    }

    public double getDoubleData(String key) {
        if(itemStack.getItemMeta().getPersistentDataContainer().get(plugin.getNamespacedKey(key), PersistentDataType.DOUBLE) == null) return 0.0D;
        return itemStack.getItemMeta().getPersistentDataContainer().get(plugin.getNamespacedKey(key), PersistentDataType.DOUBLE);
    }

    public boolean getBooleanData(String key) {
        if(itemStack.getItemMeta().getPersistentDataContainer().get(plugin.getNamespacedKey(key), PersistentDataType.BYTE) == null) return false;
        return Boolean.TRUE.equals(itemStack.getItemMeta().getPersistentDataContainer().get(plugin.getNamespacedKey(key), PersistentDataType.BOOLEAN));
    }

    public UUID getUUIDData(String key) {
        if(itemStack.getItemMeta().getPersistentDataContainer().get(plugin.getNamespacedKey(key), PersistentDataType.STRING) == null) return null;
        return UUID.fromString(itemStack.getItemMeta().getPersistentDataContainer().get(plugin.getNamespacedKey(key), PersistentDataType.STRING));
    }
}
