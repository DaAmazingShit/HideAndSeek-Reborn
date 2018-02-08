package pl.amazingshit.has.listeners;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.amazingshit.has.game.Game;
import pl.amazingshit.has.game.OptionsGet;

public class Players extends PlayerListener {

	@Override public void onPlayerMove(PlayerMoveEvent e) {
		if (Game.isPlayer(e.getPlayer()) && Game.countdown) {
			e.setCancelled(true);
			e.getPlayer().teleport(e.getFrom());
		}
	}

	@Override public void onPlayerQuit(PlayerQuitEvent e) {
		if (Game.isPlayer(e.getPlayer())) {
			Game.quit(e.getPlayer());
		}
	}

	@Override public void onPlayerJoin(PlayerJoinEvent e) {
		if (OptionsGet.playerLoginLocation(e.getPlayer().getName()) != null) {
			e.getPlayer().teleport(OptionsGet.playerLoginLocation(e.getPlayer().getName()));
		}
	}
}