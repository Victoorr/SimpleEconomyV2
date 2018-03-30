package br.dev.victor696.simpleeconomy.utils;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.scheduler.BukkitRunnable;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.commands.MagnataCommand;
import br.dev.victor696.simpleeconomy.commands.MoneyCommand;
import br.dev.victor696.simpleeconomy.listeners.Listeners;
import br.dev.victor696.simpleeconomy.manager.MessagesManager;
import br.dev.victor696.simpleeconomy.object.Money;
import br.dev.victor696.simpleeconomy.thread.Update;
import net.milkbowl.vault.economy.Economy;

public class Methods {

	public static void loadPlayer(Player p) {
		Money m = new Money(p.getUniqueId());
		m.loadAccount();
		SimpleEconomy.getInstance().money.put(p.getUniqueId(), m);
	}

	public static void loadPlayer(OfflinePlayer p) {
		Money m = new Money(p.getUniqueId());
		m.loadAccount();
		SimpleEconomy.getInstance().money.put(p.getUniqueId(), m);
	}

	public static void savePlayer(Player p) {
		Money m = SimpleEconomy.getInstance().money.get(p.getUniqueId());
		m.saveAccount();
		SimpleEconomy.getInstance().money.remove(p.getUniqueId());
	}

	public static void saveOnlinePlayers() {
		if (Bukkit.getOnlinePlayers().size() == 0)
			return;

		for (Player p : Bukkit.getOnlinePlayers()) {
			Money m = SimpleEconomy.getInstance().money.get(p.getUniqueId());
			m.saveAccount();
		}

	}

	public static void loadOnlinePlayers() {
		if (Bukkit.getOnlinePlayers().size() == 0)
			return;

		for (Player p : Bukkit.getOnlinePlayers()) {
			loadPlayer(p);
		}
	}

	public static void onEnable() {

		SimpleEconomy.getInstance().saveDefaultConfig();
		SimpleEconomy.getInstance().database = false;
		
		Bukkit.getPluginManager().registerEvents(new Listeners(), SimpleEconomy.getInstance());

		SimpleEconomy.getInstance().sql.abrirConexao();
		SimpleEconomy.getInstance().sql.criarTabelas();
		
		SimpleEconomy.getInstance().economy.setup();
		SimpleEconomy.getInstance().getServer().getServicesManager().register(Economy.class, SimpleEconomy.getInstance().vault, SimpleEconomy.getInstance(), ServicePriority.Highest);

		loadOnlinePlayers();

		SimpleEconomy.getInstance().getCommand("magnata").setExecutor(new MagnataCommand());
		SimpleEconomy.getInstance().getCommand("money").setExecutor(new MoneyCommand());
		SimpleEconomy.getInstance().getCommand("money").setTabCompleter(new MoneyCommand());
		
		if (SimpleEconomy.getInstance().getServer().getPluginManager().getPlugin("Legendchat") != null) {
			SimpleEconomy.getInstance().lc = true;
		}
		
		if (SimpleEconomy.getInstance().getServer().getPluginManager().getPlugin("PermissionsEx") != null) {
			SimpleEconomy.getInstance().pex = true;
		}

		MessagesManager.loadMessages();
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Loader.loadTop();
			}
		}.runTaskLater(SimpleEconomy.getInstance(), 20l*5);
		Update.start();
	}

	public static void onDisable() {
		if (SimpleEconomy.getInstance().database) {
			saveOnlinePlayers();
			SimpleEconomy.getInstance().sql.fecharConexao();
		}
	}

	public static String formatDouble(double money) {
		DecimalFormat numberFormat = new DecimalFormat("###,###.###");
		String d = numberFormat.format(money);
		if (d.equalsIgnoreCase(".00")) {
			d = "0.0";
		}
		return d;
	}

}
