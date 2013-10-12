package net.shiroumi.central.Command.Server;

import net.shiroumi.central.CTServer;
import net.shiroumi.central.CentralCore;
import net.shiroumi.central.Command.BaseCommand;
import net.shiroumi.central.Command.CommandDescription;
import net.shiroumi.central.Events.LockdownEvent;
import net.shiroumi.central.Events.LockdownEvent.EventType;
import net.shiroumi.central.Util.Util;
import net.shiroumi.central.Util.i18n;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdLockDown extends BaseCommand {

	public CmdLockDown(CentralCore par1Plugin) {
		super(new CommandDescription("lockdown", "server.lockdown", true));
	}

	@Override
	public boolean execute(CommandSender par1Sender, Command par2Command,
			String par3Args, String[] par4Args) {
		String message = i18n._("genericreason_lockdown");
		if(par4Args.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String t : par4Args) {
				sb.append(t);
				sb.append(" ");
			}
			message = sb.toString().trim();
		}
		Util.callEvent(new LockdownEvent((!CTServer.isLocked()?EventType.LOCKDOWN:EventType.UNLOCK), message));
		return true;
	}

}
