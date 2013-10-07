package net.shiroumi.central.Worker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

public class NopickupWorker {
	private static Map<Player, Boolean> playerNopickup = new ConcurrentHashMap<Player, Boolean>();

	public synchronized static boolean isPlayerNopickup(Player par1Player) {
		return playerNopickup.get(par1Player);
	}

	public synchronized static void setPlayerNopickup(Player par1Player, boolean par2IsNopickup) {
		NopickupWorker.playerNopickup.put(par1Player, par2IsNopickup);
	}

	public synchronized static Map<Player, Boolean> getPlayerNopickupMap() {
		return playerNopickup;
	}

	public synchronized static void setPlayerNopickupMap(Map<Player, Boolean> playerNopickup) {
		NopickupWorker.playerNopickup = playerNopickup;
	}
	
}
