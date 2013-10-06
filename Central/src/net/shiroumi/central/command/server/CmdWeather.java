package net.shiroumi.central.command.server;

import net.shiroumi.central.CentralCore;
import net.shiroumi.central.command.BaseCommand;
import net.shiroumi.central.command.CommandArgs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdWeather extends BaseCommand {

	private CentralCore plugin;

	public CmdWeather(CentralCore par1Plugin) {
		super(new CommandArgs("weather", "server.weather", true));
		this.plugin = par1Plugin;
	}

	@Override
	public boolean execute(CommandSender par1Sender, Command par2Command,
			String par3Args, String[] par4Args) {
		return false;
	}
}
