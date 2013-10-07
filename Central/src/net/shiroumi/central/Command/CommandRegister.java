package net.shiroumi.central.Command;

import net.shiroumi.central.CentralCore;


public final class CommandRegister {
	public static void Register(BaseCommand par1Command){
		CentralCore.getInstance().getServer().getPluginCommand(par1Command.getCmd()).setExecutor(par1Command);
	}
}
