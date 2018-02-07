package pl.amazingshit.has.game;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.amazingshit.has.hns;

public class Game {
	private static Set<Player> players = new HashSet<Player>();
	private static Integer count = 5;

	public static void start() {
		// Countdown
		hns.server.getScheduler().scheduleAsyncRepeatingTask(hns.instance, new Runnable() {
			public void run() {
				if (count == 0) {
					for (Player playing: Game.getPlayers()) {
						playing.sendMessage(ChatColor.BLUE + " [" + ChatColor.RED + "Hide And Seek" + ChatColor.BLUE + "]" + ChatColor.WHITE + "GO!");
					}
					count = 5;
				}
				else {
					count--;
					for (Player playing: Game.getPlayers()) {
						playing.sendMessage(ChatColor.BLUE + " [" + ChatColor.RED + "Hide And Seek" + ChatColor.BLUE + "]" + ChatColor.WHITE + count);
					}
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
}