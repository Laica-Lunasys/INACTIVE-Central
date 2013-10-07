package net.shiroumi.central.Command.Player;

import net.shiroumi.central.CentralCore;
import net.shiroumi.central.i18n;
import net.shiroumi.central.Command.BaseCommand;
import net.shiroumi.central.Command.CommandArgs;
import net.shiroumi.central.Events.AFKEvent.AFKReason;
import net.shiroumi.central.Util.Util;
import net.shiroumi.central.Worker.AFKWorker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdAFK extends BaseCommand {

	public CmdAFK(CentralCore par1Plugin) {
		super(new CommandArgs("afk", "player.afk", false));
	}

	@Override
	public boolean execute(CommandSender par1Sender, Command par2Command,
			String par3Args, String[] par4Args) {
		if (par1Sender instanceof Player) {
			String message;
			Player player = (Player) par1Sender;
			if(par4Args.length < 1) {
				message = i18n._("genericreason_afk" + (AFKWorker.isPlayerAFK(player) ? "" : "_returned"));
			} else {
				message = par4Args[0];
			}
			message = Util.maskedStringReplace(message, new String[][]{
					{"%player", player.getDisplayName()}
			});
			if (AFKWorker.isPlayerAFK(player)) {
				AFKWorker.setAFK(player, AFKReason.DECIDED, message);
			} else {
				AFKWorker.setOnline(player, AFKReason.DECIDED, message);
			}
		}
		return true;
	}

}
