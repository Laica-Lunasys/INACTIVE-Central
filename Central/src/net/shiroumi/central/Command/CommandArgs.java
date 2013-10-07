package net.shiroumi.central.Command;

/**
 * @author squarep
 */
public class CommandArgs {
	private String perm, cmd;
	private boolean isCons;
	public CommandArgs(String par1Cmd, String par2Perm, boolean isConsole){
		cmd = par1Cmd;
		perm = "Central." + par2Perm;
		isCons = isConsole;
	}
	public String getCmd(){
		return cmd;
	}
	public String getPerm(){
		return perm;
	}
	public boolean isConsoleExecute(){
		return isCons;
	}
}
