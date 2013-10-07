package net.shiroumi.central.Command.Player;

import net.shiroumi.central.CentralCore;
import net.shiroumi.central.i18n;
import net.shiroumi.central.Command.BaseCommand;
import net.shiroumi.central.Command.CommandDescription;
import net.shiroumi.central.Util.Util;
import net.shiroumi.central.Worker.NopickupWorker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdNoPickup extends BaseCommand {

	public CmdNoPickup(CentralCore par1Plugin) {
		super(new CommandDescription("nopickup", "player.nopickup", false));
	}

	@Override
	public boolean execute(CommandSender par1Sender, Command par2Command,
			String par3Args, String[] par4Args) {
		Player[] players;
		if (par4Args.length < 1) {
			if (!(par1Sender instanceof Player)) {
				Util.Message(par1Sender, i18n._("cmdillegalargument"), null);
				return false;
			} else {
				players = new Player[par4Args.length];
			}
		} else {
			Player player = (Player) par1Sender;
			if (par4Args.length < 1) {
				players = new Player[] { player };
			} else {
				players = new Player[par4Args.length];
				for (int i = 0; i < par4Args.length; i++) {
					players[i] = Util.findPlayer(par4Args[i],
							CentralCore.getInstance(), par1Sender);
					if (players[i] == null) {
						return false;
					}
				}
			}
		}
		for (Player p : players) {
			NopickupWorker.setPlayerNopickup(p, !NopickupWorker.isPlayerNopickup(p));
			Util.Message(par1Sender, i18n._((NopickupWorker.isPlayerNopickup(p) ? "enabled":"disabled").concat("Nopickup")), new String[][]{
				{"%player", p.getDisplayName()}
			});
		}
		return true;
	}
}
