package pl.amazingshit.has;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.util.config.Configuration;

public class hns extends HideAndSeekReborn {

	public static HideAndSeekReborn instance;

	public static void setConfig(Configuration confige) {
		config = confige;
	}

	private static Configuration config;

	public static Configuration getConfig() {
		return config;
	}

	public static void broadcast(CommandSender cs, Object message) {
			cs.sendMessage(ChatColor.BLUE + " [" + ChatColor.RED + "Hide And Seek" + ChatColor.BLUE + "] " + 
		ChatColor.WHITE + message);
	}
}