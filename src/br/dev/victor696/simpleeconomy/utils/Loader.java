package br.dev.victor696.simpleeconomy.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.manager.MessagesManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Loader {

	public static List<String> top = new ArrayList<>();;

	public static List<String> getListTop() {
		return top;
	}

	public static List<String> loadTop() {
		try {
			Methods.saveOnlinePlayers();
			top.clear();
			ResultSet rs = SimpleEconomy.getInstance().sql
					.prepareStatement("SELECT * FROM seconomy ORDER BY Money DESC LIMIT " + MessagesManager.TopTamanho + ";").executeQuery();
			int i = 0;
			while (rs.next()) {
				i++;
				String player = rs.getString("Player");
				double coins = rs.getDouble("Money");

				String prefix = PermissionsEx.getUser(player).getPrefix().replaceAll("&", "§");
				String magnata = "";
				if (i == 1) {
					magnata = MessagesManager.TagMagnata;
					SimpleEconomy.getInstance().magnata = player;
				}
				
				String formato = 
						MessagesManager.TopFormato
						.replace("{player}", player)
						.replace("{p}", Integer.toString(i))
						.replace("{magnata}", magnata)
						.replace("{preffix}", prefix)
						.replace("{valor}", Methods.formatDouble(coins));

				top.add(formato);
			}
			rs.close();
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("" + e);
		}
		return top;
	}

}
