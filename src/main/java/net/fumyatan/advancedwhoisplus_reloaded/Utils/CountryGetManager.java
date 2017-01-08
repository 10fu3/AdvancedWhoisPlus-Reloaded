package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import org.bukkit.entity.Player;

public class CountryGetManager {

	public static String JoinCountry(Player target){
		// IPが最新かを確認
		if (PlayerDataManager.checkNewIP(target.getUniqueId()))
			PlayerDataManager.savePlayerData(target.getUniqueId());

		return PlayerDataManager.getJoinCountry(target.getUniqueId());
	}

	public static String JoinCountryCode(Player target){
		// IPが最新かを確認
		if (PlayerDataManager.checkNewIP(target.getUniqueId()))
			PlayerDataManager.savePlayerData(target.getUniqueId());

		return PlayerDataManager.getJoinCountryCode(target.getUniqueId());
	}

}
