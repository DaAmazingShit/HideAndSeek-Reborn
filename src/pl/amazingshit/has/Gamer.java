package pl.amazingshit.has;

import org.bukkit.craftbukkit.CraftServer;

import net.minecraft.server.EntityPlayer;

public class Gamer extends org.bukkit.craftbukkit.entity.CraftPlayer {

	private Object role;

	public Gamer(CraftServer s, EntityPlayer p) {
		super(s, p);
	}

	public void setSeeker(Seeker seeker) {
		this.role = seeker;
	}

	public void setHider(Hider hider) {
		this.role = hider;
	}

	public boolean isHider() {
		if (role instanceof Hider) {
			return true;
		}
		return false;
	}

	public boolean isSeeker() {
		if (role instanceof Seeker) {
			return true;
		}
		return false;
	}

	public Object getRole() {
		return role;
	}
}