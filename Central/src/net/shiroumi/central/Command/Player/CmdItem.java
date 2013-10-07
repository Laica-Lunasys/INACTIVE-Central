package net.shiroumi.central.Command.Player;

import net.shiroumi.central.CentralCore;
import net.shiroumi.central.Command.BaseCommand;
import net.shiroumi.central.Command.CommandArgs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdItem extends BaseCommand {

	private CentralCore plugin;

	public CmdItem(CentralCore par1Plugin) {
		super(new CommandArgs("item", "player.item", false));
		this.plugin = par1Plugin;
	}

	@Override
	public boolean execute(CommandSender par1Sender, Command par2Command,
			String par3Args, String[] par4Args) {
		return false;
	}
}
