package net.shiroumi.central.Command.Player;

import net.shiroumi.central.CentralCore;
import net.shiroumi.central.i18n;
import net.shiroumi.central.Command.BaseCommand;
import net.shiroumi.central.Command.CommandDescription;
import net.shiroumi.central.Util.Util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdClear extends BaseCommand {

	public CmdClear(CentralCore par1Plugin) {
		super(new CommandDescription("clear", "player.clear", false));
	}

	@Override
	public boolean execute(CommandSender par1Sender, Command par2Command,
			String par3Args, String[] par4Args) {
		Player p = (Player) par1Sender;
		p.getInventory().clear();
		Util.broadcastMessage(i18n._("clearinventory"), new String[][] {
			{ "%player", p.getDisplayName() }
		});
		return true;
	}
}
