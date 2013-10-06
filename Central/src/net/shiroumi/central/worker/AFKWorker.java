package net.shiroumi.central.worker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.shiroumi.central.Events.AFKEvent;
import net.shiroumi.central.Events.AFKEvent.AFKReason;
import net.shiroumi.central.Events.AFKEvent.EventType;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AFKWorker {
	public static Map<Player, Long> playersTimeStamp = new ConcurrentHashMap<Player, Long>();
	public static Map<Player, Long> playersAFK = new ConcurrentHashMap<Player, Long>();

	public static void setAFK(Player par1Player, AFKReason par2Reason, String par3Message) {
		playersAFK.put(par1Player, System.currentTimeMillis());

		Bukkit.getPluginManager().callEvent(new AFKEvent(par1Player, par2Reason, EventType.Go_in_AFK, par3Message));

	}

	public static void setOnline(Player par1Player, AFKReason par2Reason, String par3Message) {
		playersAFK.remove(par1Player);

		Bukkit.getPluginManager().callEvent(new AFKEvent(par1Player, par2Reason, EventType.Returned_AFK, par3Message));
	}

	public static boolean isPlayerAFK(Player par1Player) {
		return playersAFK.containsKey(par1Player);
	}
}
