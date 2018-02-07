package pl.amazingshit.has.game;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.amazingshit.has.hns;

public class Game {
	private static Set<Player> players = new HashSet<Player>();
	private static Integer count = 5;
	private static Long left = OptionsGet.gameTime();
	public static boolean countdown = false;
	private static int task;
	public static int gameTask;

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

		gameTask = hns.server.getScheduler().scheduleAsyncRepeatingTask(hns.instance, new Runnable() {
			public void run() {
				if (left == 1200L) {
					broadcast("One minute left!");
				}
				if (left < 201) {
					if (left == 200L) {
						broadcast("10");
					}
					if (left == 180L) {
						broadcast("9");
					}
					if (left == 160L) {
						broadcast("8");
					}
					if (left == 140L) {
						broadcast("7");
					}
					if (left == 120L) {
						broadcast("6");
					}
					if (left == 100L) {
						broadcast("5");
					}
					if (left == 80L) {
						broadcast("4");
					}
					if (left == 60L) {
						broadcast("3");
					}
					if (left == 40L) {
						broadcast("2");
					}
					if (left == 20L) {
						broadcast("1");
					}
					if (left == 0L) {
						broadcast("Game finished!");
						hns.server.getScheduler().cancelTask(gameTask);
					}
				}
			}
		}, 0L, OptionsGet.gameTime());
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
			playing.sendMessage(ChatColor.BLUE + " [" + ChatColor.RED + "Hide And Seek" + ChatColor.BLUE + "] " + 
		ChatColor.WHITE + message);
		}
	}
}