package Central;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import Central.Configuration.ConfigurationManager;

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
		return "en_us"; //cfg.getString("Lang");
	}
}
