package pl.amazingshit.has;

import org.bukkit.util.config.Configuration;

public class hns extends HideAndSeekReborn {

	public static void setConfig(Configuration confige) {
		config = confige;
	}

	private static Configuration config;

	public static Configuration getConfig() {
		return config;
	}
}