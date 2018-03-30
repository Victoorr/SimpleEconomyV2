package br.dev.victor696.simpleeconomy.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import br.dev.victor696.simpleeconomy.SimpleEconomy;

public class Sql {

	public Connection conexao;

	public void criarTabelas() {
		executarUpdate("CREATE TABLE IF NOT EXISTS seconomy (UUID VARCHAR, Player VARCHAR, Money DOUBLE, Toggle BOOLEAN)");
	}

	public synchronized void abrirConexao() {
		if (SimpleEconomy.instance.getConfig().getBoolean("SQL.Use")) {

			try {
				String host = SimpleEconomy.instance.getConfig().getString("SQL.Host");
				int porta = SimpleEconomy.instance.getConfig().getInt("SQL.Port");
				String usuario = SimpleEconomy.instance.getConfig().getString("SQL.User");
				String senha = SimpleEconomy.instance.getConfig().getString("SQL.Password");
				String tabela = SimpleEconomy.instance.getConfig().getString("SQL.DB");
				conexao = DriverManager.getConnection("jdbc:mysql://" + host + ":" + porta + "/" + tabela, usuario,
						senha);
				SimpleEconomy.getInstance().database = true;
			} catch (SQLException ex) {
				Bukkit.getConsoleSender().sendMessage("" + ex);
				Bukkit.getPluginManager().disablePlugin(SimpleEconomy.instance);
			}

		} else {

			try {
				File file = new File(SimpleEconomy.instance.getDataFolder(), "seconomy.db");
				String URL = "jdbc:sqlite:" + file;
				Class.forName("org.sqlite.JDBC");
				conexao = DriverManager.getConnection(URL);
				SimpleEconomy.getInstance().database = true;
			} catch (Exception e) {
				Bukkit.getConsoleSender().sendMessage("" + e);
				Bukkit.getPluginManager().disablePlugin(SimpleEconomy.instance);
			}

		}
	}

	public PreparedStatement prepareStatement(String statement) {
		try {
			return conexao.prepareStatement(statement);
		} catch (SQLException ex) {
			Bukkit.getConsoleSender().sendMessage("" + ex);
			return null;
		}
	}

	public void executarUpdate(String update) {
		try {
			conexao.createStatement().executeUpdate(update);
		} catch (SQLException ex) {
			Bukkit.getConsoleSender().sendMessage("" + ex);
		}
	}

	public void execute(String update) {
		try {
			conexao.createStatement().execute(update);
		} catch (SQLException ex) {
			Bukkit.getConsoleSender().sendMessage("" + ex);
		}
	}

	public void fecharConexao() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (Exception ex) {
				Bukkit.getConsoleSender().sendMessage("" + ex);
			}
		}
	}

}
