package pl.amazingshit.has;

import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event.*;
import org.bukkit.plugin.java.JavaPlugin;

import pl.amazingshit.has.listeners.*;

public class HideAndSeekReborn extends JavaPlugin {

	public static Server server;
	public static Logger log;

	public void onEnable() {
		hns.instance = this;
		server = this.getServer();
		log = this.getServer().getLogger();
		this.getConfiguration().load();
		hns.setConfig(this.getConfiguration());

		log.info(" [ Hide And Seek (Reborn) ]  Enabled successfully. Version " 
		+ this.getDescription().getVersion() + ", made by " + this.getAuthor());
	}

	public String getAuthor() {
		return "DaAmazingShit";
	}

	public void onDisable() {
		log.info(" [ Hide And Seek (Reborn) ]  Disabled.");
	}

	public void registerEvents() {
		server.getPluginManager().registerEvent(Type.PLAYER_MOVE, new Players(), Priority.Normal, this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		return true;
	}
}