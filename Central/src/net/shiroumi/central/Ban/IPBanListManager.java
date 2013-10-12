package net.shiroumi.central.Ban;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.shiroumi.central.Util.FileUtils;
import net.shiroumi.central.Util.Util;

import org.bukkit.entity.Player;

public class IPBanListManager {

	private static List<String> ipBanList = new CopyOnWriteArrayList<String>();

	public boolean isBanned(Player par1Target) {
		return isBanned(Util.getIp(par1Target));
	}

	public boolean isBanned(InetAddress par1Target) {
		return isBanned(par1Target.getHostAddress());
	}

	public boolean isBanned(String par1Target) {
		return ipBanList.contains(par1Target);
	}

	public void setBan(Player par1Target) {
		setBan((par1Target));
	}

	public void setBan(String par1Target) {
		ipBanList.add(par1Target);
	}

	public void unBan(Player par1Target) {
		unBan(Util.getIp(par1Target));
	}

	public void unBan(String par1Target) {
		if(isBanned(par1Target)) ipBanList.remove(par1Target);
	}

	public void save(String par1SaveFile) throws IOException {
		FileUtils.writeFileAsStringArrayWithNewLine(new File(par1SaveFile), ipBanList.toArray(new String[]{}));
	}

	public void load(String par1SaveFile) throws IOException {
		ipBanList.addAll(Arrays.asList(FileUtils.readFileAsStringArray(new File(par1SaveFile))));
	}

	public void clear() {
		ipBanList.clear();
	}
}
