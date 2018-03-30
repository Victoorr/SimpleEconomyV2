package br.dev.victor696.simpleeconomy;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import br.dev.victor696.simpleeconomy.database.Sql;
import br.dev.victor696.simpleeconomy.hook.VaultEconomy;
import br.dev.victor696.simpleeconomy.hook.VaultHandler;
import br.dev.victor696.simpleeconomy.object.Money;
import br.dev.victor696.simpleeconomy.utils.Methods;
import net.milkbowl.vault.economy.Economy;

public class SimpleEconomy extends JavaPlugin {

	public static SimpleEconomy instance;
	public HashMap<UUID, Money> money;
	public Sql sql;
	public boolean database;
	public String magnata = "Ninguém...";
	public VaultEconomy economy;
	public Economy vault;
	public boolean pex = false;
	public boolean lc = false;

	public static SimpleEconomy getInstance() {
		return instance;
	}

	public SimpleEconomy() {
		instance = this;
		money = new HashMap<>();
		sql = new Sql();
		economy = new VaultEconomy();
		vault = new VaultHandler();
	}

	@Override
	public void onEnable() {
		Methods.onEnable();
	}

	@Override
	public void onDisable() {
		Methods.onDisable();
	}

}
