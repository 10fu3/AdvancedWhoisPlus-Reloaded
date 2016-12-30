package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.matejdro.bukkit.jail.Jail;
import com.matejdro.bukkit.jail.JailAPI;

import net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class AdditionalWhoisSender {

	private static JailAPI jail = null;

	private static String getMoney(Player target){
		if (AdvancedWhoisCore.useEcon){
		double balamce = AdvancedWhoisCore.econ.getBalance(target.getPlayer());
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

	private static Boolean getJail(Player target){
		Plugin plugin = AdvancedWhoisCore.plugin.getServer().getPluginManager().getPlugin("Jail");
		if (Bukkit.getPluginManager().getPlugin("Jail") == null){
			return null;
		} else {
			jail = ((Jail) plugin).API;
			Boolean nowJail = jail.isPlayerJailed(target.toString());
			return nowJail;
		}
	}

	public static void sendAddWhois(CommandSender sender ,Player target){
		String pre = ChatColor.translateAlternateColorCodes('&', getPrefix(target));
		sender.sendMessage(ChatColor.AQUA + "======== Additional Whois Information ========");
		if (getMoney(target) != null)
			sender.sendMessage(ChatColor.GOLD + "所持金: " + ChatColor.RESET + getMoney(target));
		if (getPrefix(target) != null)
			sender.sendMessage(ChatColor.GOLD + "権限: " + ChatColor.RESET + pre);
		if (getJail(target) != null)
			sender.sendMessage(ChatColor.GOLD + "Jail: " + ChatColor.RESET + getJail(target));
	}

}
