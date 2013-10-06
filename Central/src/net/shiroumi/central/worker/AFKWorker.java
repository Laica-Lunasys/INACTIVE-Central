package net.shiroumi.central.worker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.shiroumi.central.i18n;
import net.shiroumi.central.Events.AFKEvent;
import net.shiroumi.central.Events.AFKEvent.AFKReason;
import net.shiroumi.central.Events.AFKEvent.EventType;
import net.shiroumi.central.Util.Util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AFKWorker {

	private static AFKChecker afkchk;
	private static KickChecker kickchk;
	private static long afkTime = 1000;
	private static long kickTime = 10000;
	private static boolean isKick = true;
	private static Map<Player, Long> playersTimeStamp = new ConcurrentHashMap<Player, Long>();
	private static Map<Player, Long> playersAFK = new ConcurrentHashMap<Player, Long>();

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

	public static void updateTimeStamp(Player par1Player) {
		playersTimeStamp.put(par1Player, System.currentTimeMillis());
	}

	public static long getAFKTime() {
		return afkTime;
	}

	public static AFKChecker getAFKChecker() {
		return afkchk;
	}

	public static long getKickTime() {
		return kickTime;
	}

	public static KickChecker getKickChecker() {
		return kickchk;
	}

	public static boolean isKick() {
		return isKick;
	}


	public static void setKickTime(long kickTime) {
		AFKWorker.kickTime = kickTime;
	}

	public static void setAFKTime(long afkTime) {
		AFKWorker.afkTime = afkTime;
	}

	public static void setAFKChecker(AFKChecker afkchk) {
		AFKWorker.afkchk = afkchk;
	}

	public static void setKickChecker(KickChecker kickchk) {
		AFKWorker.kickchk = kickchk;
	}

	public static void setKick(boolean isKick) {
		AFKWorker.isKick = isKick;
	}

	private static class AFKChecker implements Runnable {
		@Override
		public void run() {
			for(Player p : playersTimeStamp.keySet()) {
				if ((System.currentTimeMillis() - playersTimeStamp.get(p) > afkTime)) {
					setAFK(p, AFKReason.AUTO, Util.maskedStringReplace( i18n._("genericreason_afk"), new String[][]{
						{"%player", p.getDisplayName()}
					}));
					playersTimeStamp.remove(p);
				}
			}
		}
	}

	private static class KickChecker implements Runnable {
		@Override
		public void run() {
			if (isKick) {
				for(Player p : playersAFK.keySet()) {
					if ((System.currentTimeMillis() - playersAFK.get(p) > kickTime)) {
						Bukkit.getPluginManager().callEvent(new AFKEvent(p, AFKReason.AUTO, EventType.Long_AFK_Kick, Util.maskedStringReplace( i18n._("afk_kick"), new String[][]{
							{"%player", p.getDisplayName()}
						})));
						QuitPlayer(p);
					}
				}
			}
		}
	}

	public static void init() {
		setAFKChecker(new AFKChecker());
		setKickChecker(new KickChecker());
	}
	

	public static void QuitPlayer(Player player) {
		if (playersTimeStamp.containsKey(player)) {
			playersTimeStamp.remove(player);
		}
		if (playersAFK.containsKey(player)) {
			playersAFK.remove(player);
		}
	}

static {
	init();
}

}
