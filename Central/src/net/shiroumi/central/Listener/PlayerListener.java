package net.shiroumi.central.Listener;

import java.util.Date;

import net.shiroumi.central.CTServer;
import net.shiroumi.central.Util.Util;
import net.shiroumi.central.Util.i18n;
import net.shiroumi.central.Worker.NopickupWorker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerListener implements Listener {

	public PlayerListener(JavaPlugin par1Plugin) {
		par1Plugin.getServer().getPluginManager().registerEvents(this, par1Plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerLogin(final PlayerLoginEvent event) {
		Player p = event.getPlayer();
		Util.broadcastMessage(String.format("ID: %s IP: %s, Date: %s", p.getName(), p.getAddress().getAddress().getHostAddress(), new Date().toString()), null);
	}
}
