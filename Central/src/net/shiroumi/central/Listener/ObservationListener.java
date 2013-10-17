package net.shiroumi.central.Listener;

import java.sql.SQLException;

import net.shiroumi.central.Databases.ActionType;
import net.shiroumi.central.Databases.DatabaseManager;
import net.shiroumi.central.Databases.SQL;
import net.shiroumi.central.Util.Util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ObservationListener implements Listener {

	public ObservationListener(JavaPlugin par1Plugin) {
		par1Plugin.getServer().getPluginManager().registerEvents(this, par1Plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=true)
	public void onPlayerLogin(final PlayerLoginEvent event) {
		try {
			DatabaseManager.executeUpdate(Util.maskedStringReplace(SQL.INSERT_PLAYER, new String[][] {
					{"%name", event.getPlayer().getName()},
					{"%addr", event.getAddress().getHostAddress()}
			}));

			DatabaseManager.executeUpdate(Util.maskedStringReplace(SQL.INSERT_LOG_DATA, new String[][] {
					{"%x", Integer.toString(event.getPlayer().getLocation().getBlockX())},
					{"%y", Integer.toString(event.getPlayer().getLocation().getBlockX())},
					{"%z", Integer.toString(event.getPlayer().getLocation().getBlockX())},
					{"%world", event.getPlayer().getLocation().getWorld().getName()},
					{"%desc", String.format("From %s.", event.getAddress().getHostAddress())},
					{"%action", Integer.toString(ActionType.PLAYER_JOIN.ordinal())},
					{"%player", event.getPlayer().getName()},
			}));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=true)
	public void onPlayerQuit(final PlayerQuitEvent event) {
		try {
			DatabaseManager.executeUpdate(Util.maskedStringReplace(SQL.INSERT_LOG_DATA, new String[][] {
					{"%x", Integer.toString(event.getPlayer().getLocation().getBlockX())},
					{"%y", Integer.toString(event.getPlayer().getLocation().getBlockX())},
					{"%z", Integer.toString(event.getPlayer().getLocation().getBlockX())},
					{"%world", event.getPlayer().getLocation().getWorld().getName()},
					{"%desc", String.format("From %s.", event.getPlayer().getAddress().getAddress().getHostAddress())},
					{"%action", Integer.toString(ActionType.PLAYER_QUIT.ordinal())},
					{"%player", event.getPlayer().getName()},
			}));
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=false)
	public void onPlayerChat(final AsyncPlayerChatEvent event) {
		try {
			DatabaseManager.executeUpdate(Util.maskedStringReplace(SQL.INSERT_LOG_DATA, new String[][] {
					{"%x", Integer.toString(event.getPlayer().getLocation().getBlockX())},
					{"%y", Integer.toString(event.getPlayer().getLocation().getBlockX())},
					{"%z", Integer.toString(event.getPlayer().getLocation().getBlockX())},
					{"%world", event.getPlayer().getLocation().getWorld().getName()},
					{"%desc", String.format("%s", event.getMessage())},
					{"%action", Integer.toString(ActionType.PLAYER_CHAT.ordinal())},
					{"%player", event.getPlayer().getName()},
			}));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=true)
	public void onPlayerBreakBlock(final BlockBreakEvent event) {
		try {
			OfflinePlayer p = Bukkit.getOfflinePlayer("Environment");
			if(event.getPlayer() != null) p = event.getPlayer().getPlayer();
			DatabaseManager.executeUpdate(Util.maskedStringReplace(SQL.INSERT_LOG_DATA, new String[][] {
					{"%x", Integer.toString(event.getBlock().getX())},
					{"%y", Integer.toString(event.getBlock().getY())},
					{"%z", Integer.toString(event.getBlock().getZ())},
					{"%world", event.getBlock().getWorld().getName()},
					{"%desc", String.format("%s:%s", event.getBlock().getTypeId(), event.getBlock().getData())},
					{"%action", Integer.toString(ActionType.BLOCK_BREAK.ordinal())},
					{"%player", p.getName()},
			}));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=true)
	public void onPlayerPlaceBlock(final BlockPlaceEvent event) {
		try {
			OfflinePlayer p = Bukkit.getOfflinePlayer("Environment");
			if(event.getPlayer() != null) p = event.getPlayer().getPlayer();
			DatabaseManager.executeUpdate(Util.maskedStringReplace(SQL.INSERT_LOG_DATA, new String[][] {
					{"%x", Integer.toString(event.getBlock().getX())},
					{"%y", Integer.toString(event.getBlock().getY())},
					{"%z", Integer.toString(event.getBlock().getZ())},
					{"%world", event.getBlock().getWorld().getName()},
					{"%desc", String.format("%s:%s", event.getBlock().getTypeId(), event.getBlock().getData())},
					{"%action", Integer.toString(ActionType.BLOCK_PLACE.ordinal())},
					{"%player", p.getName()},
			}));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}