package pl.amazingshit.has.game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import pl.amazingshit.has.Gamer;
import pl.amazingshit.has.Hider;
import pl.amazingshit.has.Seeker;
import pl.amazingshit.has.hns;

public class Lobby {

	public static Set<Gamer> lobby = new HashSet<Gamer>();
	public static Map<Player, Location> locations = new HashMap<Player, Location>();
	public static Integer waitForOthersTask = 0;
	private static Integer task = 30; // 30 seconds of waiting for possible more players to join

	public static Set<Hider> hiders = new HashSet<Hider>();
	public static Set<Seeker> seekers = new HashSet<Seeker>();

	public static void join(Player p) {
		if (Game.game) {
			hns.broadcast(p, "The game is already running!");
			return;
		}

		locations.put(p, p.getLocation());
		Gamer g = new Gamer((CraftServer)hns.server, ((CraftPlayer)p).getHandle());
		lobby.add(g);
		try {
			p.teleport(OptionsGet.lobby());
		} catch (Exception e) {
			hns.broadcast(p, "Lobby location is not defined.");
			leave(g);
			return;
		}

		if (hiders.size()+1 == OptionsGet.minimumPlayers() && seekers.size() == 0) {
			setRole(g, 1);
		}
		else {
			Random r = new Random();
			int role = r.nextInt(1);
			setRole(g, role);
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

	public static void leave(Gamer p) {
		if (Game.game && Game.isPlayer(p)) {
			Game.removePlayer(p);
			return;
		}
		if (!lobby.contains(p) && !Game.isPlayer(p)) {
			hns.broadcast(p, "You're not playing a Hide and Seek game!");
			return;
		}
		p.teleport(locations.get(p));
		lobby.remove(p);
		locations.remove(p);
		hns.broadcast(p, "You've left the Hide and Seek game.");
	}

	public static Set<Gamer> lobbyPlayers() {
		return lobby;
	}

	public static void setRole(Gamer p, Integer role) {
		if (role == 0) {
			Random r = new Random();
			BlockRepresenter br = BlockRepresenter.getBlockRepresenter(r.nextInt(15));
			Hider hider = new Hider(p, br.getMaterial());
			hiders.add(hider);
			p.setHider(hider);
			hns.broadcast(p, "You are a hider!");
		}
		if (role == 1) {
			Seeker seeker = new Seeker(p);
			seekers.add(seeker);
			p.setSeeker(seeker);
			hns.broadcast(p, "You are a seeker!");
		}
	}
}