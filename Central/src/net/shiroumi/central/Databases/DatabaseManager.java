package net.shiroumi.central.Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class DatabaseManager {
	protected static Connection con = null;
	private static Map<ResultSet, Statement> openingStatements = new ConcurrentHashMap<ResultSet, Statement>();

	protected static void connect(SQLType par1Type, String par2Host, String par3DB
			, String par4User, String par5Pass) throws SQLException {
		try {
	        Class.forName(par1Type.getDriver());
        } catch (ClassNotFoundException e) {
	        throw new IllegalStateException("Error: Can\'t Load Databases Driver");
        }
		con = par1Type.getConnection(par2Host, par3DB, par4User, par5Pass);
	}

	public static ResultSet executeQuery(String par1SQL) throws SQLException {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(par1SQL);
		openingStatements.put(rs, st);
		return rs;
	}

	public static int executeUpdate(String par1SQL) throws SQLException {
		Statement st = con.createStatement();
		int ret = st.executeUpdate(par1SQL);
		st.close();
		return ret;
	}

	public static void closeResultSet(ResultSet par1ResultSet) throws SQLException {
		if(!openingStatements.containsKey(par1ResultSet)) {
			throw new IllegalArgumentException("Already Closed ResultSet");
		}
		Statement st = openingStatements.get(par1ResultSet);
		par1ResultSet.close();
		st.close();
	}

	public static void closeConnection() throws SQLException {
		if(openingStatements.size() > 0) {
			for(ResultSet t : openingStatements.keySet()) {
				closeResultSet(t);
			}
		}
		con.close();
	}

	public static enum SQLType {
		MySQL("com.mysql.jdbc.Driver", "jdbc:mysql://%host/%db"), 
		//PostgreSQL("org.postgresql.Driver", "jdbc:postgresql://%host/%db"),
		SQLite("org.sqlite.JDBC", "jdbc:sqlite:%file");
		private final String driver, uri;
		SQLType(String par1Driver, String par2URI) {
			driver = par1Driver;
			uri = par2URI;
		}

		public String getDriver() {
			return driver;
		}

		/**
		 * @param par1Host DB Hostname
		 * @param par2DB   Database Name
		 * @param par3User Username
		 * @param par4Pass Password
		 * @return Connection
		 * @throws SQLException
		 */
		public Connection getConnection(String par1Host, String par2DB,
				String par3User, String par4Pass) throws SQLException {
			return DriverManager.getConnection(uri.replace("%host", par1Host)
					.replace("%db", par2DB), par3User, par4Pass);
		}
 
		/**
		 * SQLite Only
		 * @param par1File SQLite DatabaseFile Path
		 * @return Connection
		 * @throws SQLException
		 */
		public Connection getConnection(String par1File) throws SQLException {
			return DriverManager.getConnection(uri.replace("%file", par1File));
		}
	}
}
