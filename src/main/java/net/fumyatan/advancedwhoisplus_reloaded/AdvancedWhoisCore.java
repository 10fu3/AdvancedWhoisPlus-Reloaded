package net.fumyatan.advancedwhoisplus_reloaded;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.fumyatan.advancedwhoisplus_reloaded.Listener.CommandListener;
import net.fumyatan.advancedwhoisplus_reloaded.Listener.PlayerJoinEventListener;
import net.fumyatan.advancedwhoisplus_reloaded.Tools.PrefixAdder;
import net.milkbowl.vault.economy.Economy;

public class AdvancedWhoisCore extends JavaPlugin {

	public static boolean useEcon;
	public static Plugin plugin;
	public static Economy econ = null;

	private Boolean setupEconomy(){
		RegisteredServiceProvider<Economy> economyProvider = AdvancedWhoisCore.plugin.getServer().getServicesManager().getRegistration(Economy.class);
		if (economyProvider != null) {
			econ = economyProvider.getProvider();
			useEcon = econ != null;
			return econ != null;
		}
		useEcon = false;
        return false;
	}

	@Override
	public void onEnable(){
		super .onEnable();
		plugin = this;
		if (getConfig().getBoolean("AdditionalWhoisInfo")){
			if (!setupEconomy()){
				PrefixAdder.setLoggerWarn("経済Pluginとの連携に失敗しました");
			}
		}
		getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
		getCommand("whoisps").setExecutor(new CommandListener());
		saveDefaultConfig();
	}

}
