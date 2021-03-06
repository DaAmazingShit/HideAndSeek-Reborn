package pl.amazingshit.has.game;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;

import pl.amazingshit.has.Gamer;
import pl.amazingshit.has.Hider;
import pl.amazingshit.has.hns;

public class Game {
	private static Set<Gamer> players = new HashSet<Gamer>();
	private static Integer count = 5;
	private static Long left = OptionsGet.gameTime();
	public static boolean countdown = false;
	public static boolean game = false;
	private static int task = 0;
	public static int gameTask = 0;

	public static void start(Set<Gamer> playerss) {
		players.clear();
		players.addAll(playerss);
		// Countdown
		countdown = true;

		for (Gamer p: players) {
			try {
				p.teleport(OptionsGet.startLocation());
			} catch (Exception e) {
				hns.broadcast(p, "Game start location is not defined.");
				countdown = false;
			}
		}

		if (countdown == false) {
			for (Gamer s: players) {
				s.teleport(Lobby.locations.get(s));
				players.clear();
				Lobby.locations.clear();
			}
			return;
		}

		task = hns.server.getScheduler().scheduleAsyncRepeatingTask(hns.instance, new Runnable() {
			public void run() {
				if (count == 0) {
					broadcast("GO!");
					count = 5;
					countdown = false;
					game = true;
					hns.server.getScheduler().cancelTask(task);
				}
				else {
					count--;
					broadcast(count);
				}
			}
		}, 10L, 10L);

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
						left = OptionsGet.gameTime();
						hns.server.getScheduler().cancelTask(gameTask);
					}
				}
				else {
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
					left--;
				}
			}
		}, 10L, 10L);
	}

	public static Set<Gamer> getPlayers() {
		return players;
	}

	public static void addPlayer(Gamer p) {
		players.add(p);
	}

	public static void removePlayer(Gamer p) {
		p.teleport(Lobby.locations.get(p));
		Lobby.locations.remove(p);
		players.remove(p);
		if (p.isHider()) {
			Lobby.hiders.remove(p.getRole());
		}
		if (p.isSeeker()) {
			Lobby.seekers.remove(p.getRole());
		}
		Lobby.lobby.remove(p);
		broadcast(p.getName() + " has left the Hide and Seek game.");
		hns.broadcast(p, "You've left the Hide and Seek game.");
		if (players.size() < OptionsGet.minimumPlayers()) {
			forceEnd(ForceReason.MIN_PLAYERS);
		}
	}

	public static void quit(Gamer p) {
		OptionsSet.playerLoginLocation(p.getName(), Lobby.locations.get(p));
		removePlayer(p);
	}

	public static void forceEnd(ForceReason fr) {
		for (Gamer p: getPlayers()) {
			p.teleport(Lobby.locations.get(p));
			Lobby.locations.remove(p);
			players.remove(p);
			if (fr == ForceReason.MIN_PLAYERS) {
				hns.broadcast(p, "Forced game end - there was not enough players to play with.");
			}
			if (fr == ForceReason.ADMIN) {
				hns.broadcast(p, "Forced game end.");
			}
		}
	}

	public static boolean isPlayer(Gamer p) {
		return players.contains(p);
	}

	// Object might be: String, Double, Integer, Float, etc.
	public static void broadcast(Object message) {
		for (Gamer playing: players) {
			playing.sendMessage(ChatColor.BLUE + " [" + ChatColor.RED + "Hide And Seek" + ChatColor.BLUE + "] " + 
		ChatColor.WHITE + message);
		}
	}

	public static void updateAppearances() {
		for (Gamer s: players) {
			for (Hider hide: Lobby.hiders) {
				s.sendBlockChange(hide.getPlayer().getLocation(), hide.getBlock(), (byte)0);
			}
		}
	}

	public enum ForceReason {

		ADMIN,

		MIN_PLAYERS;
	}
}