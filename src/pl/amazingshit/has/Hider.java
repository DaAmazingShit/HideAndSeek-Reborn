package pl.amazingshit.has;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Hider {

	private Material block;
	private Player main;

	public Hider(Player p, Material b) {
		this.block = b;
		this.main = p;
	}

	public Material getBlock() {
		return this.block;
	}

	public Player getPlayer() {
		return this.main;
	}
}