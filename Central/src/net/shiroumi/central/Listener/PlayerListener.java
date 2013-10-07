package net.shiroumi.central.Listener;

import net.shiroumi.central.Worker.NopickupWorker;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerListener implements Listener {
	public void onPlayerPickupItem(final PlayerPickupItemEvent event) {
		Player p = event.getPlayer();
		if(NopickupWorker.isPlayerNopickup(p)) {
			event.setCancelled(true);
		}
	}
}
