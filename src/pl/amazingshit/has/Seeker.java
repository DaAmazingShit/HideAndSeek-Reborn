package pl.amazingshit.has;

import org.bukkit.entity.Player;

public class Seeker {

	private Player seeker;

	public Seeker(Player p) {
		this.seeker = p;
	}

	public Player getPlayer() {
		return this.seeker;
	}
}