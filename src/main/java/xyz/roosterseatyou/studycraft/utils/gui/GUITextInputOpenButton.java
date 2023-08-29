package xyz.roosterseatyou.studycraft.utils.gui;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import xyz.roosterseatyou.studycraft.StudyCraft;
import xyz.roosterseatyou.studycraft.utils.ItemDataUtils;
import xyz.roosterseatyou.studycraft.utils.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class GUITextInputOpenButton extends GUIButton implements Listener  {
    private static String textInput = "";
    private ItemStack buttonItem = super.getButtonItem();


    public GUITextInputOpenButton(ItemStack buttonItem, String name) {
        super(buttonItem, name);

        ItemDataUtils itemDataUtils = new ItemDataUtils(buttonItem);
        itemDataUtils.editBooleanData("is_gui_button", true);
        itemDataUtils.editStringData("gui_button_name", name);
    }

    public String getTextInput() {
        return textInput;
    }

    private static HashMap<UUID, Integer> taskMap = new HashMap<>();

    private static ArrayList<Player> awaitingExtraTextInput = new ArrayList<>();

    private boolean awaitingEditing = true;

    public boolean isAwaitingEditing() {
        return awaitingEditing;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        ItemDataUtils itemDataUtils = new ItemDataUtils(event.getCurrentItem());

        if(itemDataUtils.getBooleanData("is_gui_button")) {
            event.setCancelled(true);
            event.getWhoClicked().closeInventory();

            if(taskMap.containsKey(event.getWhoClicked().getUniqueId())) {
                Bukkit.getScheduler().cancelTask(taskMap.get(event.getWhoClicked().getUniqueId()));
                taskMap.remove(event.getWhoClicked().getUniqueId());
            }

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
            textInput += plainTextComponentSerializer.serialize(e.message());
            e.getPlayer().sendMessage(TextUtils.miniMessage("<gray>Text input: <yellow>" + textInput));
            e.getPlayer().sendMessage(TextUtils.miniMessage("<gray>Click the button again to set the text!"));
            Bukkit.getScheduler().cancelTask(taskMap.get(e.getPlayer().getUniqueId()));
            taskMap.remove(e.getPlayer().getUniqueId());
            awaitingExtraTextInput.add(e.getPlayer());


            int task = Bukkit.getScheduler().scheduleSyncDelayedTask(StudyCraft.getInstance(), () -> {
                if(awaitingExtraTextInput.contains(e.getPlayer())) {
                    e.getPlayer().sendMessage(TextUtils.miniMessage("<red>Text input timed out!"));
                    awaitingExtraTextInput.remove(e.getPlayer());
                    awaitingEditing = false;
                    taskMap.remove(e.getPlayer().getUniqueId());
                }
            }, 20 * 20);
            taskMap.put(e.getPlayer().getUniqueId(), task);
        }
    }
}
