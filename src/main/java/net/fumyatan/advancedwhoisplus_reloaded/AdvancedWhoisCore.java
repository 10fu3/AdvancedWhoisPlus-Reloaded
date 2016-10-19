package net.fumyatan.advancedwhoisplus_reloaded;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.fumyatan.advancedwhoisplus_reloaded.Listener.CommandListener;
import net.fumyatan.advancedwhoisplus_reloaded.Listener.PlayerJoinEventListener;

public class AdvancedWhoisCore extends JavaPlugin {

	public static Plugin plugin;

	@Override
	public void onEnable(){
		super .onEnable();
		plugin = this;
		getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
		getCommand("whoisps").setExecutor(new CommandListener());
		saveDefaultConfig();
	}

}
