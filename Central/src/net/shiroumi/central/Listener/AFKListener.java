package net.shiroumi.central.Listener;

import net.shiroumi.central.i18n;
import net.shiroumi.central.Events.AFKEvent;
import net.shiroumi.central.Events.AFKEvent.AFKReason;
import net.shiroumi.central.Events.AFKEvent.EventType;
import net.shiroumi.central.Util.Util;
import net.shiroumi.central.Worker.AFKWorker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AFKListener implements Listener {

	public AFKListener(JavaPlugin par1Plugin) {
		par1Plugin.getServer().getPluginManager().registerEvents(this, par1Plugin);
	}

	@EventHandler
	public void ListenerAFK(AFKEvent event) {
		StringBuilder sb = new StringBuilder();
		sb.append(i18n._("afkPrefix"));
		sb.append(" ");
		sb.append(event.getMessage());
		Util.broadcastMessage(sb.toString(), null);
		if (event.getType() == EventType.Long_AFK_Kick) {
			event.getPlayer().kickPlayer(event.getMessage());
		}
	}

	@EventHandler
	public void ListenerAFKMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (AFKWorker.isPlayerAFK(player)) {
			AFKWorker.setOnline(player, AFKReason.AUTO,
			        Util.maskedStringReplace(i18n._("genericreason_afk_returned"), new String[][] {
			        { "%player", event.getPlayer().getDisplayName() }
			        }));
		}
		AFKWorker.updateTimeStamp(player);
	}

	@EventHandler
	public void ListenerQuit(PlayerQuitEvent event) {
		AFKWorker.QuitPlayer(event.getPlayer());
	}
}
