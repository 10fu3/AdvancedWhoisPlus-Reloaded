package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore;
import net.fumyatan.advancedwhoisplus_reloaded.Tools.PrefixAdder;

public class DuplicatePlayerChecker {

	private static File dir = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData");
	private static boolean debug = AdvancedWhoisCore.plugin.getConfig().getBoolean("debug");

	private static boolean checkFile(){
		File data = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData/addresslist");
		return data.exists();
	}

	public static String checkDuplicate(String IP){
		File data = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData/addresslist");
		if (!checkFile()){
			try {
				dir.mkdirs();
				data.createNewFile();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				if (debug)
					e.printStackTrace();
				return null;
			}
		}

		YamlConfiguration pfile = YamlConfiguration.loadConfiguration(data);
		String player = pfile.getString(IP);

		if (player != null){
			return player;
		}
		return null;
	}

	public static void saveAdressData(String IP, Player player){
		File data = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData/addresslist");
		if (!checkFile()){
			try {
				dir.mkdirs();
				data.createNewFile();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				if (debug)
					e.printStackTrace();
				return;
			}
		}

		YamlConfiguration pfile = YamlConfiguration.loadConfiguration(data);
		pfile.set(IP, player.getName());
		try {
			pfile.save(data);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			if (debug)
				e.printStackTrace();
		}
	}

	public static void removeData(){
		File data = new File("plugins/AdvancedWhoisPlus-Reloaded/PlayerData/addresslist");
		data.delete();
	}

	public static void sendDuplicateinfo(String IP, String player){
		if (checkDuplicate(IP) != null){
			if (!checkDuplicate(IP).equals(player)){
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if (p.hasPermission("advwhois.showalert")){
						PrefixAdder.sendMessage(p, ChatColor.RED, player + "は" + checkDuplicate(IP) + "と同一人物である可能性があります。");
					}
				}
			}
		}
	}

}
