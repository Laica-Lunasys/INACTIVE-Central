package net.shiroumi.central.Listener;

import net.shiroumi.central.CTServer;
import net.shiroumi.central.i18n;
import net.shiroumi.central.Util.Util;
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

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPickupItem(final PlayerPickupItemEvent event) {
		Player p = event.getPlayer();
		if (NopickupWorker.isPlayerNopickup(p)) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerLoginEvent event) {
		if (CTServer.getBanManager().isBanned(event.getPlayer()) || CTServer.getIPBanManager().isBanned(event.getPlayer())) {
			event.disallow(Result.KICK_BANNED, Util.maskedStringReplace(i18n._("banmessage_to_disconnectplayer"), null));
		}
		if(CTServer.isLocked()) {
			event.disallow(Result.KICK_BANNED, Util.maskedStringReplace(i18n._("serverlockmsg"), null));
		}
	}
}
