package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import static net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore.*;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.fumyatan.advancedwhoisplus_reloaded.Sql.MySQLConnector;
import net.fumyatan.advancedwhoisplus_reloaded.Sql.SQLConnector;
import net.fumyatan.advancedwhoisplus_reloaded.Sql.SQLiteConnector;
import net.fumyatan.advancedwhoisplus_reloaded.Tools.PrefixAdder;

public class DuplicatePlayerChecker {

	private static SQLConnector getSQL() {
		if (UseMySQL) {
			return new MySQLConnector();
		} else {
			return new SQLiteConnector();
		}
	}

	public static List<String> checkDuplicate(String IP){
		SQLConnector sql = getSQL();
		return sql.getDuplicationIPPlayer(IP);
	}

	public static void sendDuplicateinfo(String IP, String player){
		List<String> dpl = checkDuplicate(IP);
		while (dpl.remove(player));

		if (dpl != null){
			if (!dpl.isEmpty()){
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if (p.hasPermission("advwhois.showalert")){
						PrefixAdder.sendMessage(p, ChatColor.RED, player + "は以下のプレイヤーと同一人物である可能性があります。");
						for (String dp : checkDuplicate(IP)) {
							p.sendMessage(dp);
						}
					}
				}
			}
		}
	}

}
