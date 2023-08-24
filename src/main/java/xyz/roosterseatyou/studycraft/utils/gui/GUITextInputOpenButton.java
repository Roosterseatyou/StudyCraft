package xyz.roosterseatyou.studycraft.utils.gui;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.studycraft.StudyCraft;
import xyz.roosterseatyou.studycraft.utils.ItemDataUtils;
import xyz.roosterseatyou.studycraft.utils.TextUtils;

import java.util.HashMap;
import java.util.UUID;

public class GUITextInputOpenButton implements Listener {
    private ItemStack buttonItem;
    private static String textInput;
    private String name;

    public GUITextInputOpenButton(ItemStack buttonItem, String name) {
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

    public static String getTextInput() {
        return textInput;
    }

    private static HashMap<UUID, Integer> taskMap = new HashMap<>();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        ItemDataUtils itemDataUtils = new ItemDataUtils(event.getCurrentItem());

        if(itemDataUtils.getBooleanData("is_gui_button")) {
            event.setCancelled(true);
            event.getWhoClicked().closeInventory();

            event.getWhoClicked().sendMessage(TextUtils.miniMessage("<gray>Enter the text you want to set:"));

            taskMap.put(event.getWhoClicked().getUniqueId(), Bukkit.getScheduler()
                    .scheduleSyncDelayedTask(StudyCraft.getInstance(), () -> {
                        event.getWhoClicked().sendMessage(TextUtils.miniMessage("<red>Text input timed out!"));
                        taskMap.remove(event.getWhoClicked().getUniqueId());
            }, 20 * 120));
        }
    }

    @EventHandler
    public void onChat(AsyncChatEvent e) {
        if(taskMap.containsKey(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            PlainTextComponentSerializer plainTextComponentSerializer = PlainTextComponentSerializer.plainText();
            textInput = plainTextComponentSerializer.serialize(e.message());
            e.getPlayer().sendMessage(TextUtils.miniMessage("<green>Text input set!"));
            Bukkit.getScheduler().cancelTask(taskMap.get(e.getPlayer().getUniqueId()));
            taskMap.remove(e.getPlayer().getUniqueId());
        }
    }

}
