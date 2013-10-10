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
		Player player;
		if (par4Args.length < 1) {
			if (!(par1Sender instanceof Player)) {
				Util.Message(par1Sender, i18n._("cmdillegalargument"), null);
				return false;
			} else {
				player = (Player) par1Sender;
			}
		} else {
			player = Util.findPlayer(par4Args[0], par1Sender);
			if(player == null) return false;
		}
			NopickupWorker.setPlayerNopickup(player, !NopickupWorker.isPlayerNopickup(player));
			Util.Message(par1Sender, i18n._((NopickupWorker.isPlayerNopickup(player) ? "enabled":"disabled").concat("Nopickup")), null);
		return true;
	}
}
