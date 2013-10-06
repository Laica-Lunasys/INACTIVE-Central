package net.shiroumi.central.Listener;

import net.shiroumi.central.i18n;
import net.shiroumi.central.Events.AFKEvent;
import net.shiroumi.central.Util.Util;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class AFKListener implements Listener {
	
	public AFKListener (JavaPlugin par1Plugin) {
		par1Plugin.getServer().getPluginManager().registerEvents(this, par1Plugin);
	}

	@EventHandler
	public void ListenerAFK(AFKEvent event) {
		StringBuilder sb = new StringBuilder();
		sb.append(i18n._("afkPrefix"));
		sb.append(" ");
		sb.append(event.getMessage());
		Util.broadcastMessage(sb.toString(), null);
	}
}
