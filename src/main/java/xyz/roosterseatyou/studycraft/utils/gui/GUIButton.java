package xyz.roosterseatyou.studycraft.utils.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.studycraft.utils.ItemDataUtils;

import java.util.function.Consumer;

public class GUIButton implements Listener {
    private ItemStack buttonItem;
    private Consumer<InventoryClickEvent> consumer;
    private GUIInventoryBuilder builder;
    private String name;

    public GUIButton(ItemStack buttonItem, String name) {
        this.buttonItem = buttonItem;
        this.name = name;

        ItemDataUtils itemDataUtils = new ItemDataUtils(buttonItem);
        itemDataUtils.editBooleanData("is_gui_button", true);
        itemDataUtils.editStringData("gui_button_name", name);
    }

    public ItemStack getButtonItem() {
        return buttonItem;
    }

    public void setButtonItem(ItemStack buttonItem) {
        this.buttonItem = buttonItem;
    }

    public Consumer<InventoryClickEvent> getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer<InventoryClickEvent> consumer) {
        this.consumer = consumer;
    }

public GUIInventoryBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(GUIInventoryBuilder builder) {
        this.builder = builder;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        ItemDataUtils itemDataUtils = new ItemDataUtils(event.getCurrentItem());
        if(itemDataUtils.getBooleanData("is_gui_button") && itemDataUtils.getStringData("gui_button_name").equals(name)) {
            consumer.accept(event);
        }
    }

}
