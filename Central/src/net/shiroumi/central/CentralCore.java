package net.shiroumi.central;

import java.io.File;
import java.util.logging.Logger;

import net.shiroumi.central.Configuration.ConfigurationManager;
import net.shiroumi.central.Listener.AFKListener;
import net.shiroumi.central.command.CommandRegister;
import net.shiroumi.central.command.player.CmdAFK;
import net.shiroumi.central.command.player.CmdClear;
import net.shiroumi.central.command.player.CmdGamemode;
import net.shiroumi.central.command.player.CmdGive;
import net.shiroumi.central.command.player.CmdHome;
import net.shiroumi.central.command.player.CmdInvisible;
import net.shiroumi.central.command.player.CmdItem;
import net.shiroumi.central.command.player.CmdNoPickup;
import net.shiroumi.central.command.player.CmdSpawn;
import net.shiroumi.central.command.player.CmdThor;
import net.shiroumi.central.command.server.CmdBan;
import net.shiroumi.central.command.server.CmdBroadCast;
import net.shiroumi.central.command.server.CmdKick;
import net.shiroumi.central.command.server.CmdLockDown;
import net.shiroumi.central.command.server.CmdOnlinePlayer;
import net.shiroumi.central.command.server.CmdTime;
import net.shiroumi.central.command.server.CmdWeather;
import net.shiroumi.central.worker.AFKWorker;

import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
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
		AFKWorker.setAFKTime(cfg.getInteger("afktime"));
		AFKWorker.setKickTime(cfg.getInteger("afkkicktime"));
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
