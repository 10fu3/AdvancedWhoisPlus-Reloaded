package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.matejdro.bukkit.jail.JailAPI;

import net.milkbowl.vault.economy.Economy;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class AdditionalWhoisSender {

	private static Economy econ = null;
	private static JailAPI jail = null;

	private static String getMoney(Player target){
		if (Bukkit.getPluginManager().getPlugin("Vault") == null){
			return null;
		} else {
			double balamce = econ.getBalance(Bukkit.getOfflinePlayer(target.getUniqueId()));
			return Double.toString(balamce);
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

	private static Boolean getJail(Player target){
		if (Bukkit.getPluginManager().getPlugin("Jail") == null){
			return null;
		} else {
			Boolean nowJail = jail.isPlayerJailed(target.toString());
			return nowJail;
		}
	}

	public static void sendAddWhois(Player target){
		String pre = ChatColor.translateAlternateColorCodes('&', getPrefix(target));
		target.sendMessage(ChatColor.AQUA + "======== Additional Whois Information ========");
		if (getMoney(target) != null)
			target.sendMessage(ChatColor.GOLD + "所持金: " + ChatColor.RESET + getMoney(target));
		if (getPrefix(target) != null)
			target.sendMessage(ChatColor.GOLD + "権限: " + ChatColor.RESET + pre);
		if (getJail(target) != null)
			target.sendMessage(ChatColor.GOLD + "Jail: " + ChatColor.RESET + getJail(target));
	}

}