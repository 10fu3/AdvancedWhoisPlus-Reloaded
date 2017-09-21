package net.fumyatan.tabprefixplus.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.AdditionalWhoisSender;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.PlayerDataManager;
import net.fumyatan.cgmpx.Manager.AFKManager;

public class TabUpdater implements Runnable {

	private static void setTabList(Player target, String ListName){
		target.setPlayerListName(ChatColor.translateAlternateColorCodes('&', ListName));
	}

	public void run() {
		for (Player target : Bukkit.getOnlinePlayers()){
			String prefix = AdditionalWhoisSender.getPrefix(target);
			String suffix = "";

			if (prefix == null)
				prefix = "";
			if (AdvancedWhoisCore.cgmpx && AFKManager.isAFK(target)){
				suffix = "&7[&9AFK&7]&f";
			} else if (target.getAllowFlight()) {
				suffix = "&7[&aFly&7]&f";
			} else {
				String jcc = PlayerDataManager.getJoinCountryCode(target.getUniqueId());
				suffix = "&7[&f" + jcc + "&7]&f";
			}
			setTabList(target, prefix + target.getDisplayName() + suffix);
		}
	}

}
