package pl.amazingshit.has.listeners;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

import pl.amazingshit.has.game.Game;

public class Players extends PlayerListener {

	@Override public void onPlayerMove(PlayerMoveEvent e) {
		if (Game.isPlayer(e.getPlayer()) && Game.countdown) {
			e.setCancelled(true);
			e.getPlayer().teleport(e.getFrom());
		}
	}
}