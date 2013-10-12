package net.shiroumi.central;

import java.io.File;
import java.io.IOException;

import net.shiroumi.central.Ban.BanListManager;
import net.shiroumi.central.Ban.IPBanListManager;
import net.shiroumi.central.Configuration.ConfigurationManager;
import net.shiroumi.central.Util.Util;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CTServer {
	private static boolean islocked;
	private static BanListManager banManager;
	private static IPBanListManager ipBanManager;
	private static Server _server;
	private static ConfigurationManager cfg;
	private static String LockReason;

	private static final String banlistFile = "ban.list";
	private static final String ipBanlistFile = "ipban.list";

	public static boolean isLocked() {
		return islocked;
	}

	public static void toggleLock() {
		islocked = !islocked;
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

	public static ConfigurationManager getConfiguration() {
		return cfg;
	}

	public static String getLockReason() {
	    return LockReason;
    }

	public static void setLockReason(String lockReason) {
	    LockReason = lockReason;
    }

	public static void initialize(JavaPlugin par1Plugin) {
		_server = par1Plugin.getServer();
		cfg = new ConfigurationManager(par1Plugin);
		try {
			getBanManager().load(banlistFile);
		} catch (IOException e) {
			System.out.println(String.format("Banlist Notfound. Create %s.", banlistFile));
			try {
				new File(banlistFile).createNewFile();
			} catch(IOException e1) {
				
			}
		}
		try {
			getIPBanManager().load(ipBanlistFile);
		} catch (IOException e) {
			System.out.println(String.format("IPBanlist Notfound. Create %s.", ipBanlistFile));
			try {
				new File(ipBanlistFile).createNewFile();
			} catch(IOException e1) {
				
			}
		}
	}

	public static void kickAll(String par1Reason) {
		for(Player p : getServer().getOnlinePlayers()) {
			kick(p, par1Reason);
		}
	}

	public static void kick(Player par1Player, String par2Reason) {
		if(Util.hasPerm("Central.Lockdown.except", par1Player) ||
				Util.hasPerm("Central.Kick.except", par1Player)) return;
		par1Player.kickPlayer(par2Reason);
	}

	public static void save() throws IOException {
		getBanManager().save(banlistFile);
		getIPBanManager().save(ipBanlistFile);
	}

static {
	banManager = new BanListManager();
	ipBanManager = new IPBanListManager();
}
}
