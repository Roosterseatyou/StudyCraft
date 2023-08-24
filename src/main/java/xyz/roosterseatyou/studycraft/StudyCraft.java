package xyz.roosterseatyou.studycraft;

import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.roosterseatyou.studycraft.utils.TextUtils;

public final class StudyCraft extends JavaPlugin {
    private static StudyCraft instance;
    private static ComponentLogger logger;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        logger = getComponentLogger();

        logger().info(TextUtils.miniMessage("<rainbow>StudyCraft is starting up!"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static StudyCraft getInstance() {
        return instance;
    }

    public static ComponentLogger logger() {
        return logger;
    }

    public static NamespacedKey getNamespacedKey(String key) {
        return new NamespacedKey(instance, key);
    }
}
