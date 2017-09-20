package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import static net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore.*;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdditionalWhoisSender {

	public static String getMoney(Player target){
		if (useEcon){
			double balamce = econ.getBalance(target.getPlayer());
			String suffix = econ.currencyNamePlural();
			return Double.toString(balamce) + " " + suffix;
		} else {
			return null;
		}
	}

	public static String getPrefix(Player target){
		if (!useChat){
			return null;
		} else {
			return chat.getPlayerPrefix(target);
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
