package net.fumyatan.advancedwhoisplus_reloaded.Listener;

import static net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore.*;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.fumyatan.advancedwhoisplus_reloaded.Utils.DuplicatePlayerChecker;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.PlayerDataManager;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.UpdateChecker;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.WhoisInfoSender;

public class PlayerJoinEventListener implements Listener {
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e){
		PlayerDataManager.updatePlayerData(e.getPlayer());
		String cCode = PlayerDataManager.getJoinCountryCode(e.getPlayer().getUniqueId());

		if (cCode != null) {
			String JoinMs_c = ChatColor.translateAlternateColorCodes('&', JoinMassage.replaceAll("%Player%", e.getPlayer().getName()).replaceAll("%JCountryC%", cCode));
			e.setJoinMessage(JoinMs_c);
		}
		WhoisInfoSender.sendMinimalWhois(e.getPlayer());
		UpdateChecker.VersionCheck(e.getPlayer());
		DuplicatePlayerChecker.sendDuplicateinfo(e.getPlayer().getAddress().getAddress().getHostAddress(), e.getPlayer().getName());
	}

}
