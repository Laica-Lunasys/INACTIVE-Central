package net.shiroumi.central.command.server;

import net.shiroumi.central.CentralCore;
import net.shiroumi.central.command.BaseCommand;
import net.shiroumi.central.command.CommandArgs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdLockDown extends BaseCommand {

	private CentralCore plugin;

	public CmdLockDown(CentralCore par1Plugin) {
		super(new CommandArgs("lockdown", "server.lockdown", true));
		this.plugin = par1Plugin;
	}

	@Override
	public boolean execute(CommandSender par1Sender, Command par2Command,
			String par3Args, String[] par4Args) {
		return false;
	}

}
