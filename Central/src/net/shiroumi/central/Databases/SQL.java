package net.shiroumi.central.Databases;

public final class SQL {
	public static final int limit                      = 10;

	public static final String AUTOINCREMENT_SQLITE    = "autoincrement";
	public static final String AUTOINCREMENT_MYSQL     = "auto_increment";

	public static final String CREATE_TABLE_BLOCK_DATA = "create table if not exists blocks  ( ID int unique %aic, x int, y int, z int, world int, blockid int, action_date date, action int, player int );"; 
	public static final String CREATE_TABLE_PLAYERS    = "create table if not exists players ( ID int unique %aic, Name varchar(32));";
	public static final String CREATE_TABLE_WORLDS     = "create table if not exists worlds  ( ID int unique     , Name text );";

	public static final String INSERT_BLOCK_DATA         = "insert into blocks  values(0, %s, %s, %s, %s, %s, now(), %s, %s);";
	public static final String INSERT_OR_REPLACE_WORLD   = "insert into worlds  values(0, %s) on duplicate key update ID = ID;";
	public static final String INSERT_OR_REPLACE_PLAYERS = "insert into players values(0, %s) on duplicate key update ID = ID;";

	public static final String SELECT_BLOCK_DATA       = "select * from blocks limit %d, 10";
	public static final String SELECT_PLAYER           = "select * from players;";
	public static final String SELECT_WORLD            = "select * from worlds;";
	

	public static final int ACTION_BREAK = 0;
	public static final int ACTION_PLACE = 1;
	public static String getInsertSQL(String base, String...args) {
		return String.format(base, (Object[])args);
	}
	public static String getSelectSQL(String base, String...args) {
		return String.format(base, (Object[])args);
	}
}
