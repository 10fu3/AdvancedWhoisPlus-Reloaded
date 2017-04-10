package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import static net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class AdditionalWhoisSender {

	private static String getMoney(Player target){
		if (useEcon){
		double balamce = econ.getBalance(target.getPlayer());
		return Double.toString(balamce);
		} else {
			return null;
		}
	}

	private static String getPrefix(Player target){
		if (Bukkit.getPluginManager().getPlugin("PermissionsEx") == null){
			return null;
		} else {
			PermissionUser user = PermissionsEx.getUser(target);
			String prefix = user.getPrefix();
			return prefix;
		}
	}

	public static void sendAddWhois(CommandSender sender ,Player target){
		if (getMoney(target) != null)
			sender.sendMessage(ChatColor.GOLD + "所持金: " + ChatColor.RESET + getMoney(target));
		if (getPrefix(target) != null){
			sender.sendMessage(ChatColor.GOLD + "権限: " + ChatColor.RESET + ChatColor.translateAlternateColorCodes('&', getPrefix(target)));
		}
	}

}
