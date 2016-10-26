package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import java.text.SimpleDateFormat;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore;
import net.fumyatan.advancedwhoisplus_reloaded.Tools.PrefixAdder;

public class WhoisInfoSender {

	public static void sendWhoisInfo(CommandSender sender, Player target, String s_target){

		if (sender.hasPermission("advwhois.whoisps")){
			if (target != null){
				// 初回ログインを呼び出す
				long date = target.getFirstPlayed();
				String timeStr = new SimpleDateFormat("yyyy/MM/dd").format(date);

				// 現在の体力を取得
				int health = (int)target.getHealth();

				// 最大体力を取得
				int healthall = (int)target.getHealthScale();

				// 隠し満腹度の取得
				int food = (int)target.getSaturation();

				// IPアドレス取得
				String adr = target.getAddress().getAddress().getHostAddress();

				// 名前の色コード置換
				String Dname = target.getDisplayName();
				Dname = ChatColor.translateAlternateColorCodes('&', Dname);

				// 送信
				sender.sendMessage(ChatColor.AQUA + "======== Whois Information ========");
				sender.sendMessage(ChatColor.GOLD + "名前: " + ChatColor.RESET + target.getName() + " (" + Dname + ")");
				sender.sendMessage(ChatColor.GOLD + "UUID: " + ChatColor.RESET + target.getUniqueId());
				sender.sendMessage(ChatColor.GOLD + "初回ログイン: " + ChatColor.RESET + timeStr);
				sender.sendMessage(ChatColor.GOLD + "HP: " + ChatColor.RESET + health + "/" + healthall);
				sender.sendMessage(ChatColor.GOLD + "空腹度: " + ChatColor.RESET + target.getFoodLevel() + " (" + food + ")");
				sender.sendMessage(ChatColor.GOLD + "経験値: " + ChatColor.RESET + target.getTotalExperience() + " (" + "Level " + target.getLevel() + ")");
				sender.sendMessage(ChatColor.GOLD + "座標: " + ChatColor.RESET + target.getLocation().getWorld().getName() + ", " + target.getLocation().getBlockX() + ", " + target.getLocation().getBlockY() + ", " + target.getLocation().getBlockZ());
				sender.sendMessage(ChatColor.GOLD + "ゲームモード: " + ChatColor.RESET + target.getGameMode());
				sender.sendMessage(ChatColor.GOLD + "Fly: " + ChatColor.RESET + target.getAllowFlight() + " (" + target.isFlying() + ")");
				sender.sendMessage(ChatColor.GOLD + "OP: " + ChatColor.RESET + target.isOp());
				sender.sendMessage(ChatColor.GOLD + "IP: " + ChatColor.RESET + adr);
				sender.sendMessage(ChatColor.GOLD + "ホストネーム: " + ChatColor.RESET + target.getAddress().getHostName().toString());
				sender.sendMessage(ChatColor.GOLD + "接続国名: " + ChatColor.RESET + CountryGetManager.JoinCountry(target) + " (" + CountryGetManager.JoinCountryCode(target) + ")");
				if (AdvancedWhoisCore.plugin.getConfig().getBoolean("AdditionalWhoisInfo"))
					AdditionalWhoisSender.sendAddWhois(target);
			} else {
				UUID puuid = UUIDFetcher.getUUID(s_target);
				if (puuid != null){
					OfflinePlayer getofp = Bukkit.getOfflinePlayer(puuid);
					if (getofp.hasPlayedBefore()){

						// 最終ログインを呼び出す
						long date_l = getofp.getLastPlayed();
						String timelst = new SimpleDateFormat("yyyy/MM/dd").format(date_l);

						// 送信
						sender.sendMessage(ChatColor.AQUA + "======== Whois Information ========");
						sender.sendMessage(ChatColor.GOLD + "名前: " + ChatColor.RESET + getofp.getName());
						sender.sendMessage(ChatColor.GOLD + "UUID: " + ChatColor.RESET + getofp.getUniqueId());
						sender.sendMessage(ChatColor.GOLD + "最終ログイン: " + ChatColor.RESET + timelst);
						sender.sendMessage(ChatColor.GOLD + "OP: " + ChatColor.RESET + getofp.isOp());
					}
					else {
						PrefixAdder.sendMessage(sender, ChatColor.RED , "プレイヤーはサーバーに参加したことがありません");
					}
				} else {
					PrefixAdder.sendMessage(sender, ChatColor.RED , "プレイヤーが存在しません");
				}
			}
		} else {
			PrefixAdder.sendMessage(sender, ChatColor.RED, "You don't have Permission.");
		}
	}

	public static void sendMinimalWhois(Player target){
		boolean EJoinM = AdvancedWhoisCore.plugin.getConfig().getBoolean("EnableJoinMassage");
		if (EJoinM){
			if (!target.hasPermission("advwhois.bypass")){
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if (p.hasPermission("advwhois.joinshow")){
						p.sendMessage("======== Whois Information ========");
						p.sendMessage(ChatColor.GOLD + "名前: " + ChatColor.RESET + target.getName());
						if (p.hasPermission("advancedwhoisplus.ipshow"))
							p.sendMessage(ChatColor.GOLD + "IP: " + ChatColor.RESET + target.getAddress().getAddress().getHostAddress());
						p.sendMessage(ChatColor.GOLD + "接続国: " + ChatColor.RESET + CountryGetManager.JoinCountry(target) + " (" + CountryGetManager.JoinCountryCode(target) + ")");
					}
				}
			}
		}

	}

}
