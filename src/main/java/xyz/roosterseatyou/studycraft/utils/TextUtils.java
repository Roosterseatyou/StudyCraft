package xyz.roosterseatyou.studycraft.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class TextUtils {
    public static Component textToComponent(String text) {
        return Component.text(text);
    }

    public static String componentToString(Component component) {
        PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
        return serializer.serialize(component);
    }

    public static Component miniMessage(String text) {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize(text);
    }
}
