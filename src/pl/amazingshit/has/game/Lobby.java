package pl.amazingshit.has.game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import pl.amazingshit.has.hns;

public class Lobby {

	public static Set<Player> lobby = new HashSet<Player>();
	public static Map<Player, Location> locations = new HashMap<Player, Location>();
	public static Integer waitForOthersTask = 0;
	private static Integer task = 30; // 30 seconds of waiting for possible more players to join

	public static void join(Player p) {
		if (Game.game) {
			hns.broadcast(p, "The game is already running!");
			return;
		}

		locations.put(p, p.getLocation());
		lobby.add(p);
		try {
			p.teleport(OptionsGet.lobby());
		} catch (Exception e) {
			hns.broadcast(p, "Lobby location is not defined.");
			lobby.remove(p);
			locations.remove(p);
			return;
		}

		if (lobby.size() == OptionsGet.minimumPlayers()) {
			// Wait 
			waitForOthersTask = hns.server.getScheduler().scheduleAsyncRepeatingTask(hns.instance, new Runnable() {
				public void run() {
					if (task == 1) {
						Game.start(lobbyPlayers());
						lobby.clear();
						hns.server.getScheduler().cancelTask(waitForOthersTask);
					}
					else {
						task--;
					}
				}
			}, 10L, 10L);
		}
	}

	public static Set<Player> lobbyPlayers() {
		return lobby;
	}
}