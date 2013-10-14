package net.shiroumi.central.Command.Server;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.shiroumi.central.CentralCore;
import net.shiroumi.central.Command.BaseCommand;
import net.shiroumi.central.Command.CommandDescription;
import net.shiroumi.central.Databases.ActionType;
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
			page = Integer.parseInt(par4Args.length > 0 ? par4Args[0] : "0") * 10;
		} catch (NumberFormatException e) {
			page = 0;
		}
		ResultSet rs = null;
		try {
			rs = DatabaseManager.executeQuery(String.format(SQL.SELECT_LOG_DATA, page));
			while(rs.next()) {
				Object[] params = {
						rs.getInt("id"),
						rs.getDate("date"),
						rs.getInt("x"),
						rs.getInt("y"),
						rs.getInt("z"),
						rs.getString("world"),
						rs.getString("player"),
						getAction(rs.getInt("action")),
						rs.getString("description"),
				};
				Util.Message(par1Sender, String.format("ID: %s %s | x: %s y: %s z:%s World: %s Player: %s Action: %s | %s", params), null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(rs != null) DatabaseManager.closeResultSet(rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return true;
	}

	private String getAction (int action) {
		return ActionType.values()[action].name().toLowerCase();
	}

}
