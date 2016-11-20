package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.bukkit.Bukkit;

import net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore;

public class UUIDFetcher {

	private static String getAPI(String pid) {
		boolean debug = AdvancedWhoisCore.plugin.getConfig().getBoolean("debug");
		//APIからjsonを取得
		URLConnection conn;
		StringBuilder entirePage = new StringBuilder();
		try {
			conn = new URL("https://api.mojang.com/users/profiles/minecraft/" + pid).openConnection();
			conn.setReadTimeout(1000);
			conn.setConnectTimeout(1000);
			conn.connect();
			BufferedReader stream = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String inputLine;
			while ((inputLine = stream.readLine()) != null)
				entirePage.append(inputLine);
			stream.close();
			return new String(entirePage);
		} catch (IOException | NullPointerException e) {
			// TODO 自動生成された catch ブロック
			if (debug == true){
				e.printStackTrace();}
			Bukkit.getLogger().info("Failed to connect to API.");
		}
		return null;

	}

	public static UUID getUUID(String pid) {
		boolean debug = AdvancedWhoisCore.plugin.getConfig().getBoolean("debug");
		//jsonからUUIDを抜き出し
		try{
			String[] uuid_s = getAPI(pid).split(",", -1);
			String[] uuid_st = uuid_s[0].split("\"", -1);

			String suuid = UUIDdasher.dash(uuid_st[3]);
			UUID uuid = UUID.fromString(suuid);

			return uuid;
		} catch (StringIndexOutOfBoundsException | IllegalArgumentException | ArrayIndexOutOfBoundsException | NullPointerException e){
			if (debug == true){
				e.printStackTrace();}
			Bukkit.getLogger().warning("Failed to get UUID.");
		}
		return null;
	}

}
