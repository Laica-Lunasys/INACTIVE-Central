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
		Util.broadcastMessage("Start Process...", null);
		try {
			page = Integer.parseInt(par4Args.length > 1 ? par4Args[0] : "0") * 10;
		} catch (NumberFormatException e) {
			page = 0;
		}
		Util.broadcastMessage("page = %page", new String[][]{{"%page", Integer.toString(page)}});
		ResultSet rs = null;
		try {
			rs = DatabaseManager.executeQuery("select id, x, y, z, world, blockid, date, action, (select Name from Players where ID = player) as player from LogData limit 10 offset " + page);
			Util.broadcastMessage("ResultSet of %resultset", new String[][]{{"%resultset", rs.toString()}});
			Util.broadcastMessage(String.format("ResultSet Query Rows:%d", rs.getRow()), null);
			while(rs.next()) {
				String[] params = {
						rs.getDate("id").toString(),
						rs.getDate("date").toString(),
						Integer.toString(rs.getInt("x")),
						Integer.toString(rs.getInt("y")),
						Integer.toString(rs.getInt("z")),
						Integer.toString(rs.getInt("blockid")),
						rs.getString("world"),
						rs.getString("player"),
						getAction(rs.getInt("action")),
				};
				Util.Message(par1Sender, String.format("ID: %s %s | x: %s y: %s z:%s BlockID: %s World: %s Player: %s Action: %s", (Object[])params), null);
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
		String ret = "";
		switch(action) {
		case 0:
			ret = "Block-Break";
			break;
		case 1:
			ret = "Block-Place";
			break;
		case 2:
			ret = "Player-Kill";
			break;
		}
		return ret;
	}

}
