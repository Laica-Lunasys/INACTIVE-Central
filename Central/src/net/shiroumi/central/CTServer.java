package net.shiroumi.central;

import java.io.File;
import java.io.IOException;

import net.shiroumi.central.Ban.BanListManager;
import net.shiroumi.central.Ban.IPBanListManager;

import org.bukkit.Server;

public class CTServer {
	private static boolean islocked;
	private static BanListManager banManager;
	private static IPBanListManager ipBanManager;
	private static Server _server;

	private static final String banlistFile = "ban.list";
	private static final String ipBanlistFile = "ipban.list";

	public static boolean isLocked() {
	    return islocked;
    }

	public static void setIslocked(boolean islocked) {
	    CTServer.islocked = islocked;
    }

	public static BanListManager getBanManager() {
		return banManager;
	}

	public static IPBanListManager getIPBanManager() {
		return ipBanManager;
	}

	public static Server getServer() {
		return _server;
	}

	public static void initialize(Server par1Server) {
		_server = par1Server;
		try {
			getBanManager().load(banlistFile);
		} catch (IOException e) {
			System.out.println(String.format("Banlist Notfound Create %s.", banlistFile));
			try {
				new File(banlistFile).createNewFile();
			} catch(IOException e1) {
				
			}
		}
		try {
			getIPBanManager().load(ipBanlistFile);
		} catch (IOException e) {
			System.out.println(String.format("IPBanlist Notfound Create %s.", ipBanlistFile));
			try {
				new File(ipBanlistFile).createNewFile();
			} catch(IOException e1) {
				
			}
		}
	}

	public static void save () throws IOException {
		getBanManager().save(banlistFile);
		getIPBanManager().save(ipBanlistFile);
	}

static {
	banManager = new BanListManager();
	ipBanManager = new IPBanListManager();
}
}
