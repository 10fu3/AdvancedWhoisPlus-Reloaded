package net.fumyatan.advancedwhoisplus_reloaded.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore;
import net.fumyatan.advancedwhoisplus_reloaded.Tools.PrefixAdder;

public class UpdateChecker implements Listener {

	private static InputStream cUpdate(){
		HttpURLConnection conn;
		try {
			URL url = new URL("http://download.fumyatan.net/AdvancedWhoisPlus-Reloaded/AdvancedWhoisPlus-Reloaded.xml");
			conn = (HttpURLConnection)url.openConnection();
			conn.setReadTimeout(1000);
			conn.setConnectTimeout(1000);
			conn.setRequestMethod("GET");
			conn.connect();
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				return conn.getInputStream();
			} else {
				conn.disconnect();
				return null;
			}

		} catch (IOException e){
			if (AdvancedWhoisCore.plugin.getConfig().getBoolean("debug") == true)
				e.getStackTrace();
				Bukkit.getLogger().warning("Failed to get update information.");
		return null;
		}
	}

	public static void VersionCheck(Player target){
		boolean debug = AdvancedWhoisCore.plugin.getConfig().getBoolean("debug");


		// xml下準備
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentbuilder = null;
		try {
			documentbuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO 自動生成された catch ブロック
			if (debug)
				e1.printStackTrace();
			PrefixAdder.setLoggerWarn("Failed to get update information.");
			return;
		}
		Document document = null;
		try {
			document = documentbuilder.parse(cUpdate());
		} catch (SAXException | IOException | IllegalArgumentException e1) {
			// TODO 自動生成された catch ブロック
			if (debug)
				e1.printStackTrace();
			PrefixAdder.setLoggerWarn("Failed to get update information.");
			return;
		}
		Element root = document.getDocumentElement();

		// xml解析
		String version = root.getAttribute("version");
		String config = root.getAttribute("config");
		NodeList childred = root.getChildNodes();

		if (!AdvancedWhoisCore.plugin.getDescription().getVersion().equals(version)){
			if (target.hasPermission("advwhois.updateinfo")){
				PrefixAdder.sendMessage(target, ChatColor.BLUE , "プラグインに更新があります");
				target.sendMessage(ChatColor.BLUE + "バージョン: " + ChatColor.RESET + version + " (現在のバージョン: " + AdvancedWhoisCore.plugin.getDescription().getVersion() + ")");
				for (int i = 0; i < childred.getLength(); i++){
					Node node = childred.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE){
						Element element = (Element) node;
						if (element.getNodeName().equals("details")){
							target.sendMessage(element.getAttribute("info"));
						}
					}
				}
				if (AdvancedWhoisCore.plugin.getConfig().getInt("ConfigVersion") < Integer.parseInt(config)){
					PrefixAdder.sendMessage(target, ChatColor.GREEN , "Configの更新があります (ConfigVer: " + config + " NowConfigVer: " + AdvancedWhoisCore.plugin.getConfig().getInt("ConfigVersion") + ")");
				}
			}
		} else {
			PrefixAdder.sendMessage(target, ChatColor.BLUE , "プラグインは最新です");
		}



	}

}