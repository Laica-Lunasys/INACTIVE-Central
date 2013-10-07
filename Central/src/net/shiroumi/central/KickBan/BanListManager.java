package net.shiroumi.central.KickBan;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.shiroumi.central.Util.FileUtils;

import org.bukkit.entity.Player;

public class BanListManager {

	private static List<String> banList = new CopyOnWriteArrayList<String>();

	public static boolean isBanned(Player par1Target) {
		return isBanned(par1Target.getName());
	}

	public static boolean isBanned(String par1Target) {
		return banList.contains(par1Target);
	}

	public static void setBan(Player par1Target) {
		setBan(par1Target.getName());
	}

	public static void setBan(String par1Target) {
		banList.add(par1Target);
	}

	public static void clearBanned(Player par1Target) {
		clearBanned(par1Target.getName());
	}

	public static void clearBanned(String par1Target) {
		if(isBanned(par1Target)) banList.remove(par1Target);
	}

	public static void save(String par1SaveFile) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(String t : banList) {
			sb.append(t);
			sb.append("\n");
		}
		FileUtils.writeFileAsString(new File(par1SaveFile), sb.toString());
	}

	public static void load(String par1SaveFile) throws IOException {
		String[] data = FileUtils.readFileAsStringArray(new File(par1SaveFile));
		banList = new CopyOnWriteArrayList<String>(data);
	}
}
