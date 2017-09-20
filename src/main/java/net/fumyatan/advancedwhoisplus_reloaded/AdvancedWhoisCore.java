package net.fumyatan.advancedwhoisplus_reloaded;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.fumyatan.advancedwhoisplus_reloaded.Listener.CommandListener;
import net.fumyatan.advancedwhoisplus_reloaded.Listener.PlayerJoinEventListener;
import net.fumyatan.advancedwhoisplus_reloaded.Tools.PrefixAdder;
import net.fumyatan.tabprefixplus.Utils.TabUpdater;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

public class AdvancedWhoisCore extends JavaPlugin {
	public static boolean useEcon;
	public static boolean useChat;
	public static Plugin plugin;
	public static Economy econ = null;
	public static Chat chat = null;

	public static boolean EnableTabPrefix;
	public static boolean cgmpx = false;

	public static boolean EnableJoinMassage;
	public static boolean AdditionalWhoisInfo;
	public static int SimplicityWhoisMode;
	public static String JoinMassage;
	public static boolean debug;

	public static boolean UseMySQL;
	public static String SQLAddress;
	public static int SQLPort;
	public static String SQLUser;
	public static String SQLPassword;
	public static String SQLDatabase;
	public static String SQLTable;

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

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		if (rsp != null) {
			chat = rsp.getProvider();
			useChat = chat != null;
			return chat != null;
		}
		useChat = false;
		return false;
    }

	@Override
	public void onEnable(){
		super .onEnable();
		plugin = this;
		saveDefaultConfig();
		reloadConf();

		if (AdditionalWhoisInfo){
			if (!(Bukkit.getPluginManager().getPlugin("Vault") == null)){
				if (!setupChat()){
					PrefixAdder.setLoggerWarn("権限管理プラグインとの連携に失敗しました");
				}
				if (!setupEconomy()){
					PrefixAdder.setLoggerWarn("経済Pluginとの連携に失敗しました");
				}
			}
		}

		if (EnableTabPrefix) {
			if (Bukkit.getPluginManager().isPluginEnabled("CGMPX"))
				cgmpx = true;
			getServer().getScheduler().runTaskTimer(this, new TabUpdater() , 0L, 60L);
		}

		getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(), this);
		getCommand("advancedwhois").setExecutor(new CommandListener());
	}

	public static void reloadConf() {
		plugin.reloadConfig();
		FileConfiguration conf = plugin.getConfig();
		EnableTabPrefix = conf.getBoolean("EnableTabPrefix");
		EnableJoinMassage = conf.getBoolean("EnableJoinMassage");
		AdditionalWhoisInfo = conf.getBoolean("AdditionalWhoisInfo");
		SimplicityWhoisMode = conf.getInt("SimplicityWhoisMode");
		JoinMassage = conf.getString("JoinMassage");
		debug = conf.getBoolean("debug");

		UseMySQL = conf.getBoolean("UseMySQL");
		if (UseMySQL){
			SQLAddress = conf.getString("SQLAddress");
			SQLUser = conf.getString("SQLUser");
			SQLDatabase = conf.getString("SQLDatabase");
			SQLTable = conf.getString("SQLTable");
		}
	}

}
