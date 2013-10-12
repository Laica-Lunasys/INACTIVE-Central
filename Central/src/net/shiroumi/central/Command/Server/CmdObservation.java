package net.shiroumi.central.Command.Server;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.shiroumi.central.CentralCore;
import net.shiroumi.central.Command.BaseCommand;
import net.shiroumi.central.Command.CommandDescription;
import net.shiroumi.central.Databases.DatabaseManager;
import net.shiroumi.central.Databases.SQL;
import net.shiroumi.central.Util.Util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
/**
 * /observation [page] [player]
 * @author squarep
 *
 */
public class CmdObservation extends BaseCommand {

	public CmdObservation(CentralCore par1Plugin) {
		super(new CommandDescription("observation", "server.observation", true));
	}

	@Override
	public boolean execute(CommandSender par1Sender, Command par2Command,
			String par3Args, String[] par4Args) {
		int page;
		try {
			page = Integer.parseInt(par4Args.length > 1 ? par4Args[0] : "10");
		} catch (NumberFormatException e) {
			page = 10;
		}
		ResultSet rs = null;
		try {
			rs = DatabaseManager.executeQuery(SQL.getSelectSQL(SQL.SELECT_BLOCK_DATA, new String[]{Integer.toString(page)}));
			while(rs.next()) {
				String[] params = {
						Integer.toString(rs.getInt("x")),
						Integer.toString(rs.getInt("y")),
						Integer.toString(rs.getInt("z")),
						Integer.toString(rs.getInt("world")),
						Integer.toString(rs.getInt("blockid")),
						rs.getDate("action_date").toString(),
						Integer.toString(rs.getInt("action")),
						Integer.toString(rs.getInt("player")),
				};
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
