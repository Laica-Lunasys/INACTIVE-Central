package net.shiroumi.central;

import java.io.File;
import java.util.logging.Logger;

import net.shiroumi.central.Configuration.ConfigurationManager;
import net.shiroumi.central.command.CommandRegister;
import net.shiroumi.central.commands.player.CmdGamemode;

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
		CommandRegister.Register(new CmdGamemode(this));
		log.info("Enabled " + this.getDescription().getName() + "!");
	}
	@Override
	public void onDisable(){
		
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
