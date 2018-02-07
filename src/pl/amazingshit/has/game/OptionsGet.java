package pl.amazingshit.has.game;

import org.bukkit.util.config.Configuration;

import pl.amazingshit.has.hns;

public class OptionsGet {

	private static Configuration config = hns.getConfig();

	public static int minimumPlayers() {
		config.load();
		return config.getInt("min-players", 3);
	}

	public static String world() {
		config.load();
		return config.getString("world", "world");
	}

	public static long gameTime() {
		config.load();
		long l = 0L;
		l = (config.getInt("game-time", 5) * 60) * 20L; // 6000L if game-time is 5 minutes
		return l;
	}

	public static int rawGameTime() {
		config.load();
		return config.getInt("game-time", 5);
	}
}