package net.shiroumi.central.worker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.shiroumi.central.Events.EventAFK;
import net.shiroumi.central.Events.EventAFK.AFKReason;
import net.shiroumi.central.Events.EventAFK.EventType;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AFKWorker {
	public static Map<Player, Long> playersTimeStamp = new ConcurrentHashMap<Player, Long>();
	public static Map<Player, Long> playersAFK = new ConcurrentHashMap<Player, Long>();

	public static void setAFK(Player par1Player, AFKReason par2Reason) {
		playersAFK.put(par1Player, System.currentTimeMillis());

		Bukkit.getPluginManager().callEvent(new EventAFK(par1Player, par2Reason, EventType.Go_in_AFK));

	}

	public static void setOnline(Player par1Player, AFKReason par2Reason) {
		playersAFK.remove(par1Player);

		Bukkit.getPluginManager().callEvent(new EventAFK(par1Player, par2Reason, EventType.Returned_AFK));
	}
}
