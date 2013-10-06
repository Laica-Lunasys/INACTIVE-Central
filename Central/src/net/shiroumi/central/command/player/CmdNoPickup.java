package net.shiroumi.central.command.player;

import net.shiroumi.central.CentralCore;
import net.shiroumi.central.command.BaseCommand;
import net.shiroumi.central.command.CommandArgs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdNoPickup extends BaseCommand {

	private CentralCore plugin;

	public CmdNoPickup(CentralCore par1Plugin) {
		super(new CommandArgs("nopickup", "player.nopickup", false));
		this.plugin = par1Plugin;
	}

	@Override
	public boolean execute(CommandSender par1Sender, Command par2Command,
			String par3Args, String[] par4Args) {
		return false;
	}
}
