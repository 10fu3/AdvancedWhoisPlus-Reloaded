package net.fumyatan.advancedwhoisplus_reloaded.Listener;

import static net.fumyatan.advancedwhoisplus_reloaded.ConfigManager.*;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.fumyatan.advancedwhoisplus_reloaded.Utils.CountryGetManager;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.DuplicatePlayerChecker;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.UpdateChecker;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.WhoisInfoSender;

public class PlayerJoinEventListener implements Listener {
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e){
		String cCode = CountryGetManager.JoinCountryCode(e.getPlayer());

		if (cCode != null) {
			String JoinMs_c = ChatColor.translateAlternateColorCodes('&', JoinMassage.replaceAll("%Player%", e.getPlayer().getName()).replaceAll("%JCountryC%", cCode));
			e.setJoinMessage(JoinMs_c);
		}
		WhoisInfoSender.sendMinimalWhois(e.getPlayer());
		UpdateChecker.VersionCheck(e.getPlayer());
		DuplicatePlayerChecker.sendDuplicateinfo(e.getPlayer().getAddress().getAddress().getHostAddress(), e.getPlayer().getName());
		DuplicatePlayerChecker.saveAdressData(e.getPlayer().getAddress().getAddress().getHostAddress(), e.getPlayer());
	}
}
