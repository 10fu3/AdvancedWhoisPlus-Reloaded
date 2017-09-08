package net.fumyatan.advancedwhoisplus_reloaded.Sql;

import static net.fumyatan.advancedwhoisplus_reloaded.AdvancedWhoisCore.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MySQLConnector implements SQLConnector{
	private String SQLURL;
	private Connection connection;

	public MySQLConnector(){
		SQLURL = "jdbc:mysql://" + SQLAddress + ":" + SQLPort + "/" + SQLDatabase +
				"?useUnicode=true&characterEncoding=utf-8";
		mkTable();
	}

	@Override
	public Connection getConnection(String SQLURL) throws SQLException {
		return DriverManager.getConnection(SQLURL);
	}

	@Override
	public void mkTable() {
		try {
			connection = getConnection(SQLURL);
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS ? (username TEXT, uuid TEXT, ip TEXT, joincountry TEXT, joinccode TEXT)");
			ps.setString(1, SQLTable);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}

	@Override
	public boolean savePlayerData(UUID uuid, Map<String, String> data){
		boolean result = false;
		try {
			connection = getConnection(SQLURL);
			PreparedStatement ps = connection.prepareStatement("INSERT INTO ? VALUES(?, ?, ?, ?, ?)");
			ps.setString(1, SQLTable);
			ps.setString(2, data.get("username"));
			ps.setString(3, data.get("uuid"));
			ps.setString(4, data.get("ip"));
			ps.setString(5, data.get("joincountry"));
			ps.setString(6, data.get("joinccode"));
			result = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}
		return result;
	}

	@Override
	public boolean updatePlayerData(UUID uuid, Map<String, String> data) {
		boolean result = false;
		try {
			connection = getConnection(SQLURL);
			PreparedStatement ps = connection.prepareStatement("UPDATE ? SET username = ?, ip = ?, joincountry = ?, joinccode = ? WHERE uuid = ?");
			ps.setString(1, SQLTable);
			ps.setString(2, data.get("username"));
			ps.setString(3, data.get("ip"));
			ps.setString(4, data.get("joincountry"));
			ps.setString(5, data.get("joinccode"));
			ps.setString(6, data.get("uuid"));
			result = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}
		return result;
	}

	@Override
	public Map<String, String> getPlayerData(UUID uuid) {
		Map<String, String> result = new HashMap<>();
		try {
			connection = getConnection(SQLURL);
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ? WHERE uuid = ?");
			ps.setString(1, SQLTable);
			ps.setString(2, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.put("username", rs.getString("username"));
				result.put("uuid", rs.getString("uuid"));
				result.put("ip", rs.getString("ip"));
				result.put("joincountry", rs.getString("joincountry"));
				result.put("joinccode", rs.getString("joinccode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}
		return result;
	}

	@Override
	public Map<String, String> getPlayerData(String player) {
		Map<String, String> result = new HashMap<>();
		try {
			connection = getConnection(SQLURL);
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ? WHERE username = ?");
			ps.setString(1, SQLTable);
			ps.setString(2, player);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.put("username", rs.getString("username"));
				result.put("uuid", rs.getString("uuid"));
				result.put("ip", rs.getString("ip"));
				result.put("joincountry", rs.getString("joincountry"));
				result.put("joinccode", rs.getString("joinccode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}
		return result;
	}

	@Override
	public List<String> getDuplicationIPPlayer(String ip) {
		List<String> result = new ArrayList<>();
		try {
			connection = getConnection(SQLURL);
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ? WHERE ip = ?");
			ps.setString(1, SQLTable);
			ps.setString(2, ip);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}
		return result;
	}

}
