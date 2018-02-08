package pl.amazingshit.has.listeners;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.amazingshit.has.Gamer;
import pl.amazingshit.has.game.Game;
import pl.amazingshit.has.game.OptionsGet;
import pl.amazingshit.has.game.OptionsSet;

public class Players extends PlayerListener {

	@Override public void onPlayerMove(PlayerMoveEvent e) {
		try {
			if (Game.isPlayer((Gamer)e.getPlayer()) && Game.countdown) {
				e.setCancelled(true);
				e.getPlayer().teleport(e.getFrom());
			}
		}
		catch (Exception ex) {}
	}

	@Override public void onPlayerQuit(PlayerQuitEvent e) {
		try {
			if (Game.isPlayer((Gamer)e.getPlayer())) {
				Game.quit((Gamer)e.getPlayer());
			}
		}
		catch (Exception ex) {}
	}

	@Override public void onPlayerJoin(PlayerJoinEvent e) {
		try {
			if (OptionsGet.playerLoginLocation(e.getPlayer().getName()) != null) {
				e.getPlayer().teleport(OptionsGet.playerLoginLocation(e.getPlayer().getName()));
				OptionsSet.playerLoginLocation(e.getPlayer().getName(), null);
			}
		}
		catch (Exception ex) {}
	}
}