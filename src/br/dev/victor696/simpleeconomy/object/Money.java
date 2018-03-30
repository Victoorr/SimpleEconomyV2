package br.dev.victor696.simpleeconomy.object;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.manager.SqlManager;

public class Money {

	private UUID uuid;
	private double money;
	private boolean toggle;
	private Player p;

	public Money(UUID uuid) {
		this.uuid = uuid;
		this.money = 0;
		this.toggle = false;
		this.p = Bukkit.getPlayer(this.uuid);
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public Player getPlayer() {
		return this.p;
	}

	public String getName() {
		return this.p.getName();
	}

	public double getMoney() {
		return this.money;
	}
	
	public String getFormattedMoney() {
		return formatDouble(this.money);
	}

	public void setMoney(double value) {
		this.money = value;
	}

	public void addMoney(double value) {
		this.money += value;
	}

	public void removeMoney(double value) {
		this.money -= value;
	}

	public boolean getToggle() {
		return this.toggle;
	}

	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}

	public void loadAccount() {
		if (SqlManager.hasPlayer(uuid)) {
			try {
				ResultSet rs = SimpleEconomy.getInstance().sql.prepareStatement("SELECT * FROM seconomy WHERE UUID = '" + this.uuid.toString() + "'").executeQuery();
				while (rs.next()) {
					this.money = Double.parseDouble(rs.getString("Money"));
					this.toggle = Boolean.parseBoolean(rs.getString("Toggle"));
				}
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		money = 0.0;
		toggle = true;
		SqlManager.newPlayer(this.p);
	}

	public void saveAccount() {
		try {
			SimpleEconomy.getInstance().sql.executarUpdate("UPDATE seconomy SET Money = '" + this.money + "' WHERE UUID = '" + this.uuid.toString() + "'");
			SimpleEconomy.getInstance().sql.executarUpdate("UPDATE seconomy SET Toggle = '" + this.toggle + "' WHERE UUID = '" + this.uuid.toString() + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String formatDouble(double money) {
		DecimalFormat numberFormat = new DecimalFormat("###,###.###");
		String d = numberFormat.format(money);
		if (d.equalsIgnoreCase(".00")) {
			d = "0.0";
		}
		return d;
	}

}
