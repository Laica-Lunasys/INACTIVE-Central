package net.shiroumi.central;

import java.io.File;
import java.util.logging.Logger;

import net.shiroumi.central.Command.CommandRegister;
import net.shiroumi.central.Command.Player.CmdAFK;
import net.shiroumi.central.Command.Player.CmdClear;
import net.shiroumi.central.Command.Player.CmdGamemode;
import net.shiroumi.central.Command.Player.CmdGive;
import net.shiroumi.central.Command.Player.CmdHome;
import net.shiroumi.central.Command.Player.CmdInvisible;
import net.shiroumi.central.Command.Player.CmdItem;
import net.shiroumi.central.Command.Player.CmdNoPickup;
import net.shiroumi.central.Command.Player.CmdSpawn;
import net.shiroumi.central.Command.Player.CmdThor;
import net.shiroumi.central.Command.Server.CmdBan;
import net.shiroumi.central.Command.Server.CmdBroadCast;
import net.shiroumi.central.Command.Server.CmdKick;
import net.shiroumi.central.Command.Server.CmdLockDown;
import net.shiroumi.central.Command.Server.CmdOnlinePlayer;
import net.shiroumi.central.Command.Server.CmdTime;
import net.shiroumi.central.Command.Server.CmdWeather;
import net.shiroumi.central.Configuration.ConfigurationManager;
import net.shiroumi.central.Listener.AFKListener;
import net.shiroumi.central.Listener.PlayerListener;
import net.shiroumi.central.Util.Util;
import net.shiroumi.central.Worker.AFKWorker;
import net.shiroumi.central.Worker.NopickupWorker;

import org.bukkit.plugin.java.JavaPlugin;

import com.mcbans.firestar.mcbans.MCBans;

public class CentralCore extends JavaPlugin {
	private static CentralCore Instance;
	public static Logger log;
	private static ConfigurationManager cfg;
	private static PluginFeatures<MCBans> mcbansFeatures;

	public CentralCore(){
		Instance = this;
	}

	@Override
	public void onEnable(){
		log = this.getLogger();
		cfg = new ConfigurationManager(this);
		// Player Commands
		CommandRegister.Register(new CmdAFK(this));
		CommandRegister.Register(new CmdClear(this));
		CommandRegister.Register(new CmdGamemode(this));
		CommandRegister.Register(new CmdGive(this));
		CommandRegister.Register(new CmdHome(this));
		CommandRegister.Register(new CmdInvisible(this));
		CommandRegister.Register(new CmdItem(this));
		CommandRegister.Register(new CmdNoPickup(this));
		CommandRegister.Register(new CmdSpawn(this));
		CommandRegister.Register(new CmdThor(this));
		// Server Commands
		CommandRegister.Register(new CmdBan(this));
		CommandRegister.Register(new CmdBroadCast(this));
		CommandRegister.Register(new CmdKick(this));
		CommandRegister.Register(new CmdLockDown(this));
		CommandRegister.Register(new CmdOnlinePlayer(this));
		CommandRegister.Register(new CmdTime(this));
		CommandRegister.Register(new CmdWeather(this));

		new AFKListener(this);
		new PlayerListener(this);
		AFKWorker.setAFKTime(cfg.getInteger("afktime") * 20);
		AFKWorker.setKickTime(cfg.getInteger("afkkicktime") * 20);
		AFKWorker.setKick(cfg.getBoolean("afkkick"));
		NopickupWorker.getPlayerNopickupMap().clear();
		checkFeatures();
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, AFKWorker.getAFKChecker(), 0L, 20L);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, AFKWorker.getKickChecker(), 0L, 20L);
		log.info("Enabled " + this.getDescription().getName() + "!");
	}

	@Override
	public void onDisable(){
		this.getServer().getScheduler().cancelTasks(this);
		log.info("Disabled " + this.getDescription().getName() + "!");
	}

	public static CentralCore getInstance(){
		return Instance;
	}

	public File getPluginJarFile(){
		return this.getFile();
	}

	public static String getLang(){
		return cfg.getString("lang");
	}

	private void checkFeatures() {
		mcbansFeatures = PluginFeatures.register("MCBans");
		Util.broadcastMessage(i18n._((mcbansFeatures != null ? "en" : "dis") + "ablefeatures"), new String[][] {
			{"%plugin", "MCBans"}
		});
	}
}
