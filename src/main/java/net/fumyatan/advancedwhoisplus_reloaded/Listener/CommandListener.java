package net.fumyatan.advancedwhoisplus_reloaded.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore;
import net.fumyatan.advancedwhoisplus_reloaded.Tools.PrefixAdder;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.UpdateChecker;
import net.fumyatan.advancedwhoisplus_reloaded.Utils.WhoisInfoSender;

public class CommandListener implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0){
			sender.sendMessage("Unknown command. Type \"/whoisps help\" for help.");
		} else if (args[0].equals("help")) {
			PrefixAdder.sendMessage(sender, "/whoisps <playerID>: プレイヤーの情報を表示します");
			PrefixAdder.sendMessage(sender, "/whoisps checkver: プラグインに更新がないか確認します");
			PrefixAdder.sendMessage(sender, "/whoisps reload: Configをリロードします");
		} else if (args[0].equals("checkver")){
			if (sender instanceof Player){
				Player p = (Player) sender;
				UpdateChecker.VersionCheck(p);
			} else {
				PrefixAdder.sendMessage(sender, ChatColor.RED, "このコマンドはゲーム内で実行する必要があります!");
			}
		} else if (args[0].equals("reload")){
			if(sender.hasPermission("advwhois.reload")){
				AdvancedWhoisCore.reloadConf();
				PrefixAdder.sendMessage(sender, ChatColor.GREEN, "Success!");
			} else {
				PrefixAdder.sendMessage(sender, ChatColor.RED, "You don't have Permission.");
			}
		} else {
			Player target = Bukkit.getPlayer(args[0]);
			WhoisInfoSender.sendWhoisInfo(sender, args[0]);
		}
		return true;
	}

}
