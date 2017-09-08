package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import static net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.bukkit.Bukkit;

public class UUIDFetcher {

	private static String getAPI(String pid) {
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
			if (debug == true){
				e.printStackTrace();}
			Bukkit.getLogger().info("APIとの通信に失敗しました.");
		}
		return null;

	}

	public static UUID getUUID(String pid) {
		//jsonからUUIDを抜き出し
		try{
			String[] uuid_s = getAPI(pid).split(",", -1);
			String[] uuid_st = uuid_s[0].split("\"", -1);

			String suuid = uuid_st[3];
			String[] raw_uuid = new String[5];

			raw_uuid[0] = suuid.substring(0, 8);
			raw_uuid[1] = suuid.substring(8, 12);
			raw_uuid[2] = suuid.substring(12, 16);
			raw_uuid[3] = suuid.substring(16, 20);
			raw_uuid[4] = suuid.substring(20, 32);

			suuid = raw_uuid[0] + "-" + raw_uuid[1] + "-" + raw_uuid[2] + "-" + raw_uuid[3] + "-" + raw_uuid[4];

			UUID uuid = UUID.fromString(suuid);
			return uuid;
		} catch (StringIndexOutOfBoundsException | IllegalArgumentException | ArrayIndexOutOfBoundsException | NullPointerException e){
			if (debug == true){
				e.printStackTrace();}
			Bukkit.getLogger().warning("UUIDの取得に失敗しました.");
		}
		return null;
	}

}
