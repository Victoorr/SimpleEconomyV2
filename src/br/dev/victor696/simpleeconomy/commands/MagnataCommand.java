package br.dev.victor696.simpleeconomy.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.manager.MessagesManager;
import br.dev.victor696.simpleeconomy.object.Money;

public class MagnataCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (!SimpleEconomy.getInstance().magnata.equals("Ninguém...")) {
				OfflinePlayer pp = Bukkit.getOfflinePlayer(SimpleEconomy.getInstance().magnata);
				Money m = SimpleEconomy.getInstance().money.get(pp.getUniqueId());
				p.sendMessage(MessagesManager.MagnataAtual.replace("{magnata}", SimpleEconomy.getInstance().magnata)
						.replace("{valor}", m.getFormattedMoney()));
				return true;
			}
			p.sendMessage(MessagesManager.NaoHaMagnata);
			return true;
		}
		return false;
	}

}
