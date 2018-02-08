package pl.amazingshit.has;

import org.bukkit.Material;

public class Hider {

	private Material block;
	private Gamer main;

	public Hider(Gamer p, Material b) {
		this.block = b;
		this.main = p;
	}

	public Material getBlock() {
		return this.block;
	}

	public Gamer getPlayer() {
		return this.main;
	}
}