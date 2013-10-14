package net.shiroumi.central.Databases;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.SQLException;

import net.shiroumi.central.CentralCore;
import net.shiroumi.central.Util.Util;

public class SQLiteConnector extends DatabaseManager {

	/**
	 * Connect SQLite Database
	 * @param par1File Database File
	 * @throws SQLException
	 */
	public static void connect(String par1File) throws SQLException {
		DatabaseManager.setSQLType(SQLType.SQLite);
		loadFile();
		con = SQLType.SQLite.getConnection(par1File);
		
	}

	private static void loadFile() {
		File f = new File(CentralCore.getInstance().getDataFolder(), "sqlite.jar");
		if(!f.exists()) {
			Util.copyFileFromJar(f, "sqlite.jar", true);
		}
		File logdb = new File(CentralCore.getInstance().getDataFolder(), "log.db");
		if(!logdb.exists()) {
			Util.copyFileFromJar(logdb, "log.db", true);
		}
		try {
			URL[] jarURL = {new URL("jar:file:" + f.getAbsolutePath() + "!/")};
			URLClassLoader classloader = URLClassLoader.newInstance(jarURL);
			classloader.loadClass(SQLType.SQLite.getDriver()).newInstance();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
