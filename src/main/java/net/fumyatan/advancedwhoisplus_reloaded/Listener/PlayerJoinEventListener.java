package net.fumyatan.advancedwhoisplus_reloaded.Listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.CountryGetManager;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.UpdateChecker;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.WhoisInfoSender;

public class PlayerJoinEventListener implements Listener {
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e){
		String cCode = CountryGetManager.JoinCountryCode(e.getPlayer());
		String JoinMs = AdvancedWhoisCore.plugin.getConfig().getString("JoinMassage");

		if (cCode != null) {
			JoinMs = JoinMs.replaceAll("%Player%", e.getPlayer().getName());
			JoinMs = JoinMs.replaceAll("%JCountryC%", cCode);
			String JoinMs_c = ChatColor.translateAlternateColorCodes('&', JoinMs);
			e.setJoinMessage(JoinMs_c);
		}
		WhoisInfoSender.sendMinimalWhois(e.getPlayer());
		UpdateChecker.VersionCheck(e.getPlayer());
	}
}
