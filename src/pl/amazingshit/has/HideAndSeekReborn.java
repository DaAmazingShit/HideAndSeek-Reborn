package pl.amazingshit.has;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class HideAndSeekReborn extends JavaPlugin {

	public static Logger log;

	public void onEnable() {
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

	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		return true;
	}
}