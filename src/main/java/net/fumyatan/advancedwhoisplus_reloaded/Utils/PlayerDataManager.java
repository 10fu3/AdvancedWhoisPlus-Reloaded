package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.fcm_developers.playerdataapi.API.PlayerDataAPI;

import net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore;

public class PlayerDataManager {
	static boolean debug = AdvancedWhoisCore.plugin.getConfig().getBoolean("debug");

	public static boolean checkNewIP(UUID uuid){
		if (Bukkit.getPlayer(uuid) != null){
			String NowIP = Bukkit.getPlayer(uuid).getAddress().getAddress().getHostAddress();
			String DataIP = PlayerDataAPI.getPlayerData(uuid, "IP");
			return !NowIP.equals(DataIP);
		}
		return false;
	}

	public static boolean checkNewIP(String adr_s, UUID uuid){
		String NowIP = adr_s;
		String DataIP = PlayerDataAPI.getPlayerData(uuid, "IP");
		return !NowIP.equals(DataIP);
	}

	public static boolean savePlayerData(UUID uuid){
		Player target = Bukkit.getPlayer(uuid);
		String IP = target.getAddress().getAddress().getHostAddress();

		// ユーザーデータの書き込み
		PlayerDataAPI.savePlayerData(uuid, "Nick", target.getDisplayName());
		PlayerDataAPI.savePlayerData(uuid, "UUID", uuid.toString());
		PlayerDataAPI.savePlayerData(uuid, "IP", IP);
		PlayerDataAPI.savePlayerData(uuid, "JoinCountry", JoinCountryGetter.JoinCountry(IP));
		PlayerDataAPI.savePlayerData(uuid, "JoinCCode", JoinCountryGetter.JoinCountryCode(IP));

		return false;
	}

	public static boolean savePlayerData(String adr_s, UUID uuid){
		Player target = Bukkit.getPlayer(uuid);
		String IP = adr_s;

		// ユーザーデータの書き込み
		PlayerDataAPI.savePlayerData(uuid, "Nick", target.getDisplayName());
		PlayerDataAPI.savePlayerData(uuid, "UUID", uuid.toString());
		PlayerDataAPI.savePlayerData(uuid, "IP", IP);
		PlayerDataAPI.savePlayerData(uuid, "JoinCountry", JoinCountryGetter.JoinCountry(IP));
		PlayerDataAPI.savePlayerData(uuid, "JoinCCode", JoinCountryGetter.JoinCountryCode(IP));

		return false;
	}

	public static String getJoinCountry(UUID uuid){
		String data = PlayerDataAPI.getPlayerData(uuid, "JoinCountry");
		if (Bukkit.getPlayer(uuid) != null){
			if (data != null){
				return data;
			}
			Player target = Bukkit.getPlayer(uuid);
			String adr = target.getAddress().getAddress().getHostAddress();

			return JoinCountryGetter.JoinCountry(adr);
		} else {
			if (data != null){
				return data;
			}
			Player target = Bukkit.getOfflinePlayer(uuid).getPlayer();
			String adr = target.getAddress().getAddress().getHostAddress();

			return JoinCountryGetter.JoinCountry(adr);
		}
	}

	public static String getJoinCountryCode(UUID uuid){
		String data = PlayerDataAPI.getPlayerData(uuid, "JoinCCode");
		if (Bukkit.getPlayer(uuid) != null){
			if (data != null){
				return data;
			}
			Player target = Bukkit.getPlayer(uuid);
			String adr = target.getAddress().getAddress().getHostAddress();

			return JoinCountryGetter.JoinCountryCode(adr);
		} else {
			if (data != null){
				return data;
			}
			Player target = Bukkit.getOfflinePlayer(uuid).getPlayer();
			String adr = target.getAddress().getAddress().getHostAddress();

			return JoinCountryGetter.JoinCountryCode(adr);
		}
	}
}
