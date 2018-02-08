package pl.amazingshit.has.game;

import org.bukkit.Location;
import org.bukkit.World;
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

	public static Location lobby() throws Exception {
		config.load();
		Location lobby = null;
		try {
			String[] stringlocation = config.getString("lobby", null).split(",");
			World world = hns.server.getWorld(stringlocation[0]);
			int x = Integer.parseInt(stringlocation[1]);
			int y = Integer.parseInt(stringlocation[2]);
			int z = Integer.parseInt(stringlocation[3]);
			lobby = new Location(world, x, y, z);
		}
		catch (Exception ex) {
			throw new Exception(" [ Hide And Seek (Reborn) ]  LOBBY LOCATION NOT DEFINED");
		}
		return lobby;
	}

	public static Location startLocation() throws Exception {
		config.load();
		Location start = null;
		try {
			String[] stringlocation = config.getString("start-location", null).split(",");
			World world = hns.server.getWorld(stringlocation[0]);
			int x = Integer.parseInt(stringlocation[1]);
			int y = Integer.parseInt(stringlocation[2]);
			int z = Integer.parseInt(stringlocation[3]);
			start = new Location(world, x, y, z);
		}
		catch (Exception ex) {
			throw new Exception(" [ Hide And Seek (Reborn) ]  START LOCATION NOT DEFINED");
		}
		return start;
	}

	public static Location playerLoginLocation(String player) {
		Location login = null;
		try {
			OptionsSet.locations.load();
			String[] stringlocation = OptionsSet.locations.getString(player, null).split(",");
			World world = hns.server.getWorld(stringlocation[0]);
			int x = Integer.parseInt(stringlocation[1]);
			int y = Integer.parseInt(stringlocation[2]);
			int z = Integer.parseInt(stringlocation[3]);
			login = new Location(world, x, y, z);
		}
		catch (Exception ex) {
			return null;
		}
		return login;
	}
}