package net.shiroumi.central;

import java.io.File;
import java.util.logging.Logger;

import net.shiroumi.central.configuration.ConfigurationManager;

import org.bukkit.plugin.java.JavaPlugin;

public class CentralCore extends JavaPlugin {
	public static CentralCore Instance;
	public static Logger log;
	private static ConfigurationManager cfg;
	public CentralCore(){
		Instance = this;
		log = this.getLogger();
	}
	@Override
	public void onEnable(){
		cfg = new ConfigurationManager(this);
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
		return cfg.getString("Lang");
	}
}
