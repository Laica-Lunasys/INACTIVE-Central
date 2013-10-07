package net.shiroumi.central.Listener;

import net.shiroumi.central.Worker.NopickupWorker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerListener implements Listener {

	public PlayerListener(JavaPlugin par1Plugin) {
		par1Plugin.getServer().getPluginManager().registerEvents(this, par1Plugin);
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerPickupItem(final PlayerPickupItemEvent event) {
		Player p = event.getPlayer();
		if(NopickupWorker.isPlayerNopickup(p)) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerCommandPreprocessEvent event) {
		
	}
}
