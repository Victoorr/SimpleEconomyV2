package br.dev.victor696.simpleeconomy.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubCmdAdd;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubCmdPay;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubCmdRemove;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubCmdSet;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubCmdToggle;
import br.dev.victor696.simpleeconomy.commands.subcommands.MoneySubCmdTop;
import br.dev.victor696.simpleeconomy.manager.MessagesManager;
import br.dev.victor696.simpleeconomy.object.Money;

public class MoneyCommand implements CommandExecutor, TabCompleter {
	private List<EconomyCommand> commands;

	public MoneyCommand() {
		this.commands = new ArrayList<EconomyCommand>();
		this.newCommand(new MoneySubCmdAdd());
		this.newCommand(new MoneySubCmdRemove());
		this.newCommand(new MoneySubCmdPay());
		this.newCommand(new MoneySubCmdSet());
		this.newCommand(new MoneySubCmdToggle());
		this.newCommand(new MoneySubCmdTop());
	}

	public List<EconomyCommand> getCommands() {
		return this.commands;
	}

	public boolean newCommand(EconomyCommand cmd) {
		if (!this.commands.contains(cmd)) {
			this.commands.add(cmd);
		}
		return this.commands.contains(cmd);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length <= 0) {
				Money m = SimpleEconomy.getInstance().money.get(p.getUniqueId());
				p.sendMessage(MessagesManager.MoneyDoJogador.replace("{valor}", m.getFormattedMoney()));
			}
			if (args.length >= 1) {
				for (EconomyCommand cmd : getCommands()) {
					if (args[0].equalsIgnoreCase(cmd.getCommand())) {
						if (p.hasPermission(cmd.getPermission())) {
							cmd.onCommand(p, args);
						} else {
							p.sendMessage(MessagesManager.SemPermissao);
						}
						return false;
					}
					String[] aliases;
					for (int length = (aliases = cmd.getAliases()).length, i = 0; i < length; ++i) {
						String sub = aliases[i];
						if (args[0].equalsIgnoreCase(sub)) {
							if (p.hasPermission(cmd.getPermission())) {
								cmd.onCommand(p, args);
							} else {
								p.sendMessage(MessagesManager.SemPermissao);
							}
							return false;
						}
					}
				}
				Player pp = Bukkit.getPlayer(args[0]);
				if (pp == null) {
					p.sendMessage(MessagesManager.JogadorNaoEncontrado);
					return false;
				}

				Money m = SimpleEconomy.getInstance().money.get(pp.getUniqueId());
				p.sendMessage(MessagesManager.MoneyDeOutroJogador.replace("{player}", args[0]).replace("{valor}",
						m.getFormattedMoney()));
				return true;
			}
		}
		return false;
	}

	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> tabCompleter = new ArrayList<String>();
		if (args.length == 1) {
			for (EconomyCommand cmd : getCommands()) {
				if (sender.hasPermission(cmd.getPermission())) {
					tabCompleter.add(cmd.getCommand());
				}
			}
		}
		return tabCompleter;
	}
}