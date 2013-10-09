package net.shiroumi.central.Ban;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.shiroumi.central.Util.FileUtils;
import net.shiroumi.central.Util.Util;

import org.bukkit.entity.Player;

public class IPBanListManager {

	private static List<String> ipBanList = new CopyOnWriteArrayList<String>();

	public static boolean isBanned(Player par1Target) {
		return isBanned(Util.getIp(par1Target));
	}

	public static boolean isBanned(String par1Target) {
		return ipBanList.contains(par1Target);
	}

	public static void setBan(Player par1Target) {
		setBan((par1Target));
	}

	public static void setBan(String par1Target) {
		ipBanList.add(par1Target);
	}

	public static void clearBanned(Player par1Target) {
		unBanned(Util.getIp(par1Target));
	}

	public static void unBanned(String par1Target) {
		if(isBanned(par1Target)) ipBanList.remove(par1Target);
	}

	public static void save(String par1SaveFile) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(String t : ipBanList) {
			sb.append(t);
			sb.append("\n");
		}
		FileUtils.writeFileAsString(new File(par1SaveFile), sb.toString());
	}

	public static void load(String par1SaveFile) throws IOException {
		String[] data = FileUtils.readFileAsStringArray(new File(par1SaveFile));
		ipBanList = new CopyOnWriteArrayList<String>(data);
	}
}
