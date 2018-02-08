package pl.amazingshit.has.game;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.util.config.Configuration;

public class OptionsSet {

	public static Configuration locations = new Configuration(new File("plugins/HideAndSeek", "locations.yml"));

	public static void playerLoginLocation(String player, Location loc) {
		OptionsSet.locations.load();
		String locc = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
		locations.load();
		locations.setProperty(player, locc);
		locations.save();
	}
}