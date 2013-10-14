package net.shiroumi.central.Databases;

import net.shiroumi.central.Databases.DatabaseManager.SQLType;

public final class SQL {

	public static final String CREATE_TABLE_BLOCK_DATA;
	public static final String CREATE_TABLE_PLAYERS;

	public static final String INSERT_LOG_DATA = "INSERT INTO LogData(x,y,z,world,action,description, player) values(%x,%y,%z,%world,%action,%description,%player);";
	public static final String INSERT_PLAYER;
	static {
		if (DatabaseManager.getSQLType() == null) {
			throw new IllegalStateException("Not Initialized DatabaseManager!");
		}
		if (DatabaseManager.getSQLType() == SQLType.SQLite) {
			CREATE_TABLE_BLOCK_DATA = "CREATE TABLE LogData (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,x INT,y INT,z INT,world VARCHAR(32),[action] INT,player INT,description TEXT DEFAULT (''),date DATETIME DEFAULT (datetime('now','+09:00:00')));";
			CREATE_TABLE_PLAYERS    = "CREATE TABLE IF NOT EXISTS Players (ID INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, Name VARCHAR(32) NOT NULL UNIQUE, IPAddress VARCHAR(24));";
		} else if (DatabaseManager.getSQLType() == SQLType.MySQL) {
			CREATE_TABLE_BLOCK_DATA = "CREATE TABLE `LogData` (`id` int(11) NOT NULL AUTO_INCREMENT, `x` int(11) DEFAULT NULL,`y` int(11) DEFAULT NULL, `z` int(11) DEFAULT NULL, `world` varchar(48) DEFAULT NULL, `action` int(11) DEFAULT NULL, `player` int(11) DEFAULT NULL, `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP, `description` text NOT NULL, PRIMARY KEY (`id`), UNIQUE KEY `ID_UNIQUE` (`id`), KEY `id` (`id`,`player`), KEY `player` (`player`), CONSTRAINT `LogData_ibfk_1` FOREIGN KEY (`player`) REFERENCES `Player` (`ID`) ON UPDATE CASCADE) ENGINE=InnoDB;";
			CREATE_TABLE_PLAYERS    = "CREATE TABLE `Player` (`ID` int(11) NOT NULL AUTO_INCREMENT, `Name` varchar(45) NOT NULL, `IPAddress` varchar(45) NOT NULL, PRIMARY KEY (`ID`), UNIQUE KEY `ID_UNIQUE` (`ID`), UNIQUE KEY `Name_UNIQUE` (`Name`)) ENGINE=InnoDB;";
		} else {
			CREATE_TABLE_BLOCK_DATA = "";
			CREATE_TABLE_PLAYERS = "";
		}
		INSERT_PLAYER = "INSERT %OR IGNORE INTO Player ( Name , IPAddress ) VALUES ( %name , %addr );".replace("%OR", (DatabaseManager.getSQLType() == SQLType.SQLite ? "OR" : ""));
	}
}
