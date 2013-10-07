package net.shiroumi.central.Command.Player;

import net.shiroumi.central.CentralCore;
import net.shiroumi.central.i18n;
import net.shiroumi.central.Command.BaseCommand;
import net.shiroumi.central.Command.CommandDescription;
import net.shiroumi.central.Util.Util;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
/**
 * /gm [Mode] [Player] from Player
 * /gm [Mode]  Player  from Console
 * @author squarep
 *
 */

public class CmdGamemode extends BaseCommand {

	public CmdGamemode(CentralCore par1Plugin) {
		super(new CommandDescription("gm", "player.gamemode", true));
	}

	@Override
	public boolean execute(CommandSender par1Sender, Command par2Command,
			String par3Args, String[] par4Args) {
		Player target = null;
		GameMode mode = GameMode.SURVIVAL;
		if (par4Args.length < 1) {
			// /gm
			if (!(par1Sender instanceof Player)) {
				Util.Message(par1Sender, i18n._("cmdillegalargument"), null);
				return false;
			} else {
				target = (Player) par1Sender;
				mode = GameMode.values()[(target.getGameMode().ordinal() + 1 & 1)];
			}
		} else if (par4Args.length < 2) {
			// /gm Player or Mode
			if (par4Args[0].length() < 2) {
				int m = par4Args[0].charAt(0);
				if (m > 0x29 && m < 0x33 && (par1Sender instanceof Player)) {
					m = m < 0x31 ? 1 : m < 0x32 ? 0 : 2;
					mode = GameMode.values()[m];
					target = (Player) par1Sender;
				} else {
					Util.Message(par1Sender, i18n._("cmdillegalargument"), null);
					return false;
				}
			} else {
				target = Util.findPlayer(par4Args[0], CentralCore.getInstance(), par1Sender);
				if (target == null) return false;
				mode = GameMode.values()[(target.getGameMode().ordinal() + 1 & 1)];
			}
		} else {
			// /gm Mode Player
			target = Util.findPlayer(par4Args[1], CentralCore.getInstance(), par1Sender);
			if (target == null) return false;
			int m = par4Args[0].charAt(0);
			if (m > 0x29 && m < 0x33 && (par1Sender instanceof Player)) {
				m = m < 0x31 ? 1 : m < 0x32 ? 0 : 2;
				mode = GameMode.values()[m];
				target = (Player) par1Sender;
			} else {
				Util.Message(par1Sender, i18n._("cmdillegalargument"), null);
				return false;
			}
			mode = GameMode.values()[m];
		}

		target.setGameMode(mode);

		Util.broadcastMessage(i18n._("changegm"), new String[][] {
			{"%player", target.getDisplayName()}, 
			{"%gamemode", mode.name()}
		});
		return false;
	}
}
