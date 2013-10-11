package net.shiroumi.central.Ban;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.shiroumi.central.Util.FileUtils;

import org.bukkit.entity.Player;

public class BanListManager {

	private static List<String> banList = new CopyOnWriteArrayList<String>();

	public boolean isBanned(Player par1Target) {
		return isBanned(par1Target.getName());
	}

	public boolean isBanned(String par1Target) {
		return banList.contains(par1Target);
	}

	public void setBan(Player par1Target) {
		setBan(par1Target.getName());
	}

	public void setBan(String par1Target) {
		banList.add(par1Target);
	}

	public void unBan(Player par1Target) {
		unBan(par1Target.getName());
	}

	public void unBan(String par1Target) {
		if(isBanned(par1Target)) banList.remove(par1Target);
	}

	public void save(String par1SaveFile) throws IOException {
		FileUtils.writeFileAsStringArrayWithNewLine(new File(par1SaveFile), banList.toArray(new String[]{}));
	}

	public void load(String par1SaveFile) throws IOException {
		banList.addAll(Arrays.asList(FileUtils.readFileAsStringArray(new File(par1SaveFile))));
	}

	public void clear() {
		banList.clear();
	}
}
