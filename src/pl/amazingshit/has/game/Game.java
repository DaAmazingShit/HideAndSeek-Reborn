package pl.amazingshit.has.game;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.amazingshit.has.hns;

public class Game {
	private static Set<Player> players = new HashSet<Player>();
	private static Integer count = 5;
	public static boolean countdown = false;
	private static int task;

	public static void start() {
		// Countdown
		countdown = true;
		task = hns.server.getScheduler().scheduleAsyncRepeatingTask(hns.instance, new Runnable() {
			public void run() {
				if (count == 0) {
					broadcast("GO!");
					count = 5;
					countdown = false;
					hns.server.getScheduler().cancelTask(task);
				}
				else {
					count--;
					broadcast(count);
				}
			}
		}, 0L, 20L);
	}

	public static Set<Player> getPlayers() {
		return players;
	}

	public static void addPlayer(Player p) {
		players.add(p);
	}

	public static void removePlayer(Player p) {
		players.remove(p);
	}

	public static boolean isPlayer(Player p) {
		return players.contains(p);
	}

	// Object might be: String, Double, Integer, Float, etc.
	public static void broadcast(Object message) {
		for (Player playing: players) {
			playing.sendMessage(ChatColor.BLUE + " [" + ChatColor.RED + "Hide And Seek" + ChatColor.BLUE + "]" + 
		ChatColor.WHITE + message);
		}
	}
}