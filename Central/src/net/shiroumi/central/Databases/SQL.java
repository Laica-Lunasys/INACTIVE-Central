package net.shiroumi.central.Databases;

public final class SQL {
	public static final int limit = 10;

	public static final String AUTOINCREMENT_SQLITE = "autoincrement";
	public static final String AUTOINCREMENT_MYSQL = "auto_increment";

	public static final String CREATE_TABLE_BLOCK_DATA = 
			"CREATE TABLE IF NOT EXISTS LogData (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, x INT, y INT, z INT, blockid INT, world VARCHAR(32), [action] INT, player INT, date TEXT DEFAULT (datetime('now', '+09:00:00') ));";
	public static final String CREATE_TABLE_PLAYERS = 
			"CREATE TABLE IF NOT EXISTS Players (ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, Name VARCHAR(32) NOT NULL);";

	public static final String INSERT_LOG_DATA = "INSERT INTO LogData (x, y, z, blockid, world, [action], player) VALUES (%d,%d,%d,%d,%s,%d,%d);";
	public static final String INSERT_OR_REPLACE_PLAYERS = "insert into players values(0, %s) on duplicate key update ID = ID;";

	public static final String SELECT_BLOCK_DATA = "select * from blocks limit %d, 10";
	public static final String SELECT_PLAYER = "select * from players;";
	public static final String SELECT_WORLD = "select * from worlds;";

	/**
	 * 
	 * @param args (x, y, z, blockid, world, [action], player)
	 * @return
	 */
	public static String getInsertLogdataSQL(String... args) {
		return String.format(INSERT_LOG_DATA, (Object[]) args);
	}

}
