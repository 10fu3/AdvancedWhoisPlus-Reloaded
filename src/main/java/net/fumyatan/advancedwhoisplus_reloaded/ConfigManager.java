package net.fumyatan.advancedwhoisplus_reloaded;

import static net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore.*;

public class ConfigManager {

	public static boolean EnableJoinMassage;
	public static boolean AdditionalWhoisInfo;
	public static int SimplicityWhoisMode;
	public static String JoinMassage;
	public static boolean debug;
	public static int ConfigVersion;

	public static void loadConfig(){
		plugin.reloadConfig();
		EnableJoinMassage = plugin.getConfig().getBoolean("EnableJoinMassage");
		AdditionalWhoisInfo = plugin.getConfig().getBoolean("AdditionalWhoisInfo");
		SimplicityWhoisMode = plugin.getConfig().getInt("SimplicityWhoisMode");
		JoinMassage = plugin.getConfig().getString("JoinMassage");
		debug = plugin.getConfig().getBoolean("debug");
		ConfigVersion = plugin.getConfig().getInt("ConfigVersion");
	}
}
