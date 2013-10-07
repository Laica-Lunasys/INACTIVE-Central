package net.shiroumi.central.Worker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

public class NopickupWorker {
	private static Map<Player, Boolean> playerNopickup = new ConcurrentHashMap<Player, Boolean>();

	public static boolean isPlayerNopickup(Player par1Player) {
		if (!playerNopickup.containsKey(par1Player)) return false;
		return playerNopickup.get(par1Player);
	}

	public static void setPlayerNopickup(Player par1Player, boolean par2IsNopickup) {
		NopickupWorker.playerNopickup.put(par1Player, par2IsNopickup);
	}

	public static Map<Player, Boolean> getPlayerNopickupMap() {
		return playerNopickup;
	}

	public static void setPlayerNopickupMap(Map<Player, Boolean> playerNopickup) {
		NopickupWorker.playerNopickup = playerNopickup;
	}
	
}
