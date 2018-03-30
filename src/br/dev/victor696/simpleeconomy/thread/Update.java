package br.dev.victor696.simpleeconomy.thread;

import org.bukkit.scheduler.BukkitRunnable;

import br.dev.victor696.simpleeconomy.SimpleEconomy;
import br.dev.victor696.simpleeconomy.utils.Loader;

public class Update {

	public static void start() {

		int update = SimpleEconomy.getInstance().getConfig().getInt("Geral.AtualizarTop");
		new BukkitRunnable() {

			@Override
			public void run() {
				Loader.loadTop();
			}
		}.runTaskTimer(SimpleEconomy.getInstance(), 20 * update, 20 * update);

	}

}
