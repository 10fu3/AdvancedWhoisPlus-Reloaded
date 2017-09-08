package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import static net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.fumyatan.advancedwhoisplus_reloaded.Sql.MySQLConnector;
import net.fumyatan.advancedwhoisplus_reloaded.Sql.SQLConnector;
import net.fumyatan.advancedwhoisplus_reloaded.Sql.SQLiteConnector;

public class PlayerDataManager {

	private static SQLConnector getSQL() {
		if (UseMySQL) {
			return new MySQLConnector();
		} else {
			return new SQLiteConnector();
		}
	}

	public static String getIP(UUID uuid) {
		SQLConnector sql = getSQL();
		return sql.getPlayerData(uuid).get("ip");
	}

	public static boolean checkNewIP(UUID uuid){
		if (Bukkit.getPlayer(uuid) != null){
			String NowIP = Bukkit.getPlayer(uuid).getAddress().getAddress().getHostAddress();
			return !NowIP.equals(getIP(uuid));
		}
		return false;
	}

	public static boolean checkNewIP(String adr_s, UUID uuid){
		String NowIP = adr_s;
		return !NowIP.equals(getIP(uuid));
	}

	public static void updatePlayerData(Player p) {
		SQLConnector sql = getSQL();
		Map<String, String> data = new HashMap<>();
		data.put("username", p.getName());
		data.put("uuid", p.getUniqueId().toString());
		data.put("ip", p.getAddress().getAddress().getHostAddress());
		data.put("joincountry", JoinCountryGetter.JoinCountry(p.getAddress().getAddress().getHostAddress()));
		data.put("joinccode", JoinCountryGetter.JoinCountryCode(p.getAddress().getAddress().getHostAddress()));
		Map<String, String> old_data = sql.getPlayerData(p.getUniqueId());

		if (!old_data.isEmpty()) {
			if (checkNewIP(p.getUniqueId())) {
				sql.updatePlayerData(p.getUniqueId(), data);
			}
		} else {
			sql.savePlayerData(p.getUniqueId(), data);
		}
	}

	public static String getJoinCountry(UUID uuid) {
		SQLConnector sql = getSQL();
		return sql.getPlayerData(uuid).get("joincountry");
	}

	public static String getJoinCountryCode(UUID uuid){
		SQLConnector sql = getSQL();
		return sql.getPlayerData(uuid).get("joinccode");
	}
}
