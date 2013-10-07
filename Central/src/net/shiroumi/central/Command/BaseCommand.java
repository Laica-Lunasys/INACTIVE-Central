package net.shiroumi.central.Command;

import net.shiroumi.central.CentralCore;
import net.shiroumi.central.i18n;
import net.shiroumi.central.Util.Util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/**
 * @author squarep
 */

public abstract class BaseCommand implements CommandExecutor {
	protected final CommandDescription command;
	protected BaseCommand(CommandDescription par1Cmd){
		command = par1Cmd;
	}
	public String getCmd(){
		return command.getCmd();
	}
	public String getPerm(){
		return command.getPerm();
	}
	public boolean isConsoleExecute(){
		return command.isConsoleExecute();
	}
	@Override
	public boolean onCommand(CommandSender par1Sender, Command par2Command, String par3Args, String[] par4Args){
		if(!this.isConsoleExecute() && par1Sender instanceof ConsoleCommandSender){
			Util.Message(par1Sender, i18n._("cmdplayeronly"), null);
			return false;
		}
		if(!Util.hasPerm(this.getPerm(), par1Sender, CentralCore.getInstance())){
			return false;
		}
		return execute(par1Sender, par2Command, par3Args, par4Args);
	}
	abstract public boolean execute(CommandSender par1Sender, Command par2Command, String par3Args, String[] par4Args);
}
