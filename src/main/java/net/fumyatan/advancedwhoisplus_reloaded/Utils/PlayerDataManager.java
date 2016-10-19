package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore;

public class PlayerDataManager {

	private static File dir = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData");

	private static boolean checkFile(String uuid){
		File data = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData/" + uuid);
		return data.exists();
	}

	public static boolean checkNewIP(String uuid){
		File data = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData/" + uuid);
		YamlConfiguration pfile = YamlConfiguration.loadConfiguration(data);

		if (Bukkit.getPlayer(UUID.fromString(uuid)) != null){
			String NowIP = Bukkit.getPlayer(UUID.fromString(uuid)).getAddress().getAddress().getHostAddress();
			String DataIP = pfile.getString("IP");
			return !NowIP.equals(DataIP);
		}
		return false;
	}

	public static boolean checkNewIPfromIP(String adr_s, String uuid){
		File data = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData/" + uuid);
		YamlConfiguration pfile = YamlConfiguration.loadConfiguration(data);

		String NowIP = Bukkit.getPlayer(UUID.fromString(uuid)).getAddress().getAddress().getHostAddress();
		String DataIP = pfile.getString("IP");
		return DataIP.equals(NowIP);
	}

	public static boolean savePlayerData(String uuid){
		boolean debug = AdvancedWhoisCore.plugin.getConfig().getBoolean("debug");
		File data = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData/" + uuid);
		YamlConfiguration pfile = YamlConfiguration.loadConfiguration(data);

		if (!checkFile(uuid)){
			try {
				dir.mkdirs();
				data.createNewFile();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				if (debug)
					e.printStackTrace();
				return false;
			}
		}

		Player target = Bukkit.getPlayer(UUID.fromString(uuid));
		String IP = target.getAddress().getAddress().getHostAddress();

		// ユーザーデータの書き込み
		pfile.set("Nick", target.getDisplayName());
		pfile.set("UUID", target.getUniqueId().toString());
		pfile.set("IP", IP);
		pfile.set("JoinCountry", JoinCountryGetter.JoinCountry(IP));
		pfile.set("JoinCCode", JoinCountryGetter.JoinCountryCode(IP));

		try {
			pfile.save(data);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			if (debug)
				e.printStackTrace();
		}

		return false;
	}

	public static String getJoinCountry(String uuid){

		if (Bukkit.getPlayer(UUID.fromString(uuid)) != null){
			if (checkFile(uuid)){
				File data = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData/" + uuid);
				YamlConfiguration pfile = YamlConfiguration.loadConfiguration(data);
				return pfile.getString("JoinCountry");
			}
			Player target = Bukkit.getPlayer(UUID.fromString(uuid));
			String adr = target.getAddress().getAddress().getHostAddress();

			return JoinCountryGetter.JoinCountry(adr);
		} else {
			if (checkFile(uuid)){
				File data = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData/" + uuid);
				YamlConfiguration pfile = YamlConfiguration.loadConfiguration(data);
				return pfile.getString("JoinCountry");
			}
			Player target = Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getPlayer();
			String adr = target.getAddress().getAddress().getHostAddress();

			return JoinCountryGetter.JoinCountry(adr);
		}
	}

	public static String getJoinCountryCode(String uuid){
		if (Bukkit.getPlayer(UUID.fromString(uuid)) != null){
			if (checkFile(uuid)){
				File data = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData/" + uuid);
				YamlConfiguration pfile = YamlConfiguration.loadConfiguration(data);
				return pfile.getString("JoinCCode");
			}
			Player target = Bukkit.getPlayer(UUID.fromString(uuid));
			String adr = target.getAddress().getAddress().getHostAddress();

			return JoinCountryGetter.JoinCountryCode(adr);
		} else {
			if (checkFile(uuid)){
				File data = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData/" + uuid);
				YamlConfiguration pfile = YamlConfiguration.loadConfiguration(data);
				return pfile.getString("JoinCCode");
			}
			Player target = Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getPlayer();
			String adr = target.getAddress().getAddress().getHostAddress();

			return JoinCountryGetter.JoinCountryCode(adr);
		}
	}
}
