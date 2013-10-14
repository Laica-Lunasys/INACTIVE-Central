package net.shiroumi.central.Databases;

import java.sql.SQLException;

public class MySQLManager extends DatabaseManager {
	/**
	 * Connect Database
	 * @param par2File Database File
	 * @throws SQLException
	 */
	public static void connect(String par1Host, String par2DB, String par3User, String par4Pass) throws SQLException {
		DatabaseManager.setSQLType(SQLType.MySQL);
		connect(SQLType.MySQL, par1Host, par2DB, par3User, par4Pass);
	}
}
