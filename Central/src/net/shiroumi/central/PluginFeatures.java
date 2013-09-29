package net.shiroumi.central;

import org.bukkit.plugin.Plugin;

/**
 * @author squarep
 */
public class PluginFeatures<T> {
	private final T featurePlugin;
	private final String PluginName;

	private PluginFeatures(T par1Instance, String par2Name){
		featurePlugin = par1Instance;
		PluginName = par2Name;
	}

	@SuppressWarnings("unchecked")
	public static <T> PluginFeatures<T> register(String par1PluginName, String par2ClassPath){
		try {
			return (PluginFeatures<T>) register(par1PluginName, Class.forName(par2ClassPath));
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> PluginFeatures<T> register(String par1PluginName, Class<T> par2PluginClass){
		Plugin var1 = null;
		if((var1 = CentralCore.getInstance().
				getServer().getPluginManager().getPlugin(par1PluginName)) != null){
			return new PluginFeatures<T>((T)var1, par1PluginName);
		}
		return null;
	}

	public boolean isEnable(){
		return featurePlugin != null &&((Plugin)featurePlugin).isEnabled();
	}

	public String getPluginName(){
		return PluginName;
	}

	public T getInstance(){
		return featurePlugin;
	}
}
