package net.shiroumi.central.Util;

import static net.shiroumi.central.i18n.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import net.shiroumi.central.CentralCore;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/** @author squarep */
public class Util {
	public static String maskedStringReplace(String par1Src,
			String[][] par2Masks) {
		String var1 = par1Src;
		if (par2Masks != null) {
			for (int i = 0; i < par2Masks.length; ++i) {
				var1 = var1.replace(par2Masks[i][0], par2Masks[i][1]);
			}
		}
		var1 = var1.replaceAll("&([0-9a-fk-or])", "\u00A7$1");
		return var1;
	}

	public static Player findPlayer(String par1Player, CentralCore par2Plugin) {
		return findPlayer(par1Player, par2Plugin, null);
	}

	public static Player findPlayer(String par1Player, CentralCore par2Plugin,
			CommandSender par3Sender) {
		if (par2Plugin.getServer().getPlayer(par1Player) == null) {
			if (par3Sender != null) {
				Message(par3Sender, _("playernotfound"), new String[][] { {
						"%player", par1Player } });
			}
			return null;
		}
		return par2Plugin.getServer().getPlayer(par1Player);
	}

	public static boolean hasPerm(String par1Perm, CommandSender par2Player,
			CentralCore par3Plugin) {
		return hasPerm(par1Perm, par2Player, par3Plugin, true);
	}

	public static boolean hasPerm(String par1Perm, CommandSender par2Player,
			CentralCore par3Plugin, boolean hasMessage) {
		if (!(par2Player.hasPermission(par1Perm)) && !(par2Player.isOp())) {
			Message(par2Player, _("msgPrefix") + _("errorperm"),
					new String[][] { { "%perm", par1Perm } });
			return false;
		}
		return true;
	}

	public static String getIp(Player p) {
		return p.getAddress().toString().split("/")[1].split(":")[0];
	}

	public static void copyFileFromJar(File targetFile, String sourceFilePath,
			boolean isBinary) {
		if (!targetFile.getParentFile().exists())
			targetFile.getParentFile().mkdirs();
		JarFile jar = null;
		ZipEntry entry = null;
		InputStream is = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try {
			jar = new JarFile(CentralCore.getInstance().getPluginJarFile());
			entry = jar.getEntry(sourceFilePath);
			is = jar.getInputStream(entry);
			if (isBinary) {
				os = new FileOutputStream(targetFile);
				byte[] buf = new byte[(int) entry.getSize()];
				is.read(buf);
				os.write(buf);
			} else {
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				pw = new PrintWriter(new BufferedWriter(new FileWriter(
						targetFile)));
				String buf;
				while ((buf = br.readLine()) != null) {
					pw.println(buf);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (jar != null)
					jar.close();
				if (is != null)
					is.close();
				if (br != null)
					br.close();
				if (os != null)
					os.close();
				if (pw != null)
					pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String BuildString(boolean hasNewLine, String... args) {
		StringBuilder var1 = new StringBuilder();
		for (String t : args) {
			var1.append(t);
			if (hasNewLine) {
				var1.append("\n");
			}
		}
		return var1.toString();
	}

	public static void Message(CommandSender par1Sender, String par2Messages,
			String[][] par3Maskes) {
		par1Sender.sendMessage(maskedStringReplace(_("msgPrefix")
				+ par2Messages, par3Maskes));
	}

	public static void BroatCastMessage(String par1Messages,
			String[][] par2Maskes) {
		CentralCore
				.getInstance()
				.getServer()
				.broadcastMessage(
						maskedStringReplace(_("msgPrefix") + par1Messages,
								par2Maskes));
	}

	public static String MD5Sum(byte[] par1Data) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			StringBuilder sb = new StringBuilder();
			for (byte t : md5.digest(par1Data)) {
				sb.append(Integer.toHexString(t & 0xFF));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String MD5Sum(String par1Data) {
		return MD5Sum(par1Data.getBytes());
	}

	public static String MD5Sum(FileInputStream par1Stream) {
		FileChannel ic = null;
		ByteBuffer  bb = null;
		try {
			ic = par1Stream.getChannel();
			bb = ByteBuffer.allocate((int) ic.size());
			ic.read(bb);
		} catch(IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (ic != null) ic.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return MD5Sum(bb.array());
	}
}

