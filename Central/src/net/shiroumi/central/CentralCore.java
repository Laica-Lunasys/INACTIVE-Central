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
import net.shiroumi.central.Worker.AFKWorker;

import org.bukkit.plugin.java.JavaPlugin;

public class CentralCore extends JavaPlugin {
	private static CentralCore Instance;
	public static Logger log;
	private static ConfigurationManager cfg;

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
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, AFKWorker.getAFKChecker(), 20, 20);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, AFKWorker.getKickChecker(), 20, 20);
		AFKWorker.setAFKTime(cfg.getInteger("afktime") * 20);
		AFKWorker.setKickTime(cfg.getInteger("afkkicktime") * 20);
		AFKWorker.setKick(cfg.getBoolean("afkkick"));
		log.info("Enabled " + this.getDescription().getName() + "!");
	}

	@Override
	public void onDisable(){
		this.getServer().getScheduler().cancelTasks(this);
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
}
