package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import org.bukkit.entity.Player;

public class CountryGetManager {

	public static String JoinCountry(Player target){
		// IPが最新かを確認
		if (PlayerDataManager.checkNewIP(target.getUniqueId().toString()))
			PlayerDataManager.savePlayerData(target.getUniqueId().toString());

		return PlayerDataManager.getJoinCountry(target.getUniqueId().toString());
	}

	public static String JoinCountryCode(Player target){
		// IPが最新かを確認
		if (PlayerDataManager.checkNewIP(target.getUniqueId().toString()))
			PlayerDataManager.savePlayerData(target.getUniqueId().toString());

		return PlayerDataManager.getJoinCountryCode(target.getUniqueId().toString());
	}

}
