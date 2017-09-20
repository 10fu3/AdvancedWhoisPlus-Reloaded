package net.fumyatan.tabprefixplus.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrefixAdder {

	public static void setLoggerInfo(String msg){
		Bukkit.getLogger().info("[TabPrefixPlus] " + msg);
	}

	public static void setLoggerWarn(String msg){
		Bukkit.getLogger().warning("[TabPrefixPlus] " + msg);
	}

	public static void sendMessage(Player To, String msg){
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		To.sendMessage(ChatColor.AQUA + "[TabPrefixPlus] " + ChatColor.RESET + msg);
	}

	public static void sendMessage(Player To, ChatColor color, String msg){
		To.sendMessage(color + "[TabPrefixPlus] " + msg);
	}

	public static void sendMessage(CommandSender To, String msg){
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		To.sendMessage(ChatColor.AQUA + "[TabPrefixPlus] " + ChatColor.RESET + msg);
	}

	public static void sendMessage(CommandSender To, ChatColor color, String msg){
		To.sendMessage(color + "[TabPrefixPlus] " + msg);
	}

}
