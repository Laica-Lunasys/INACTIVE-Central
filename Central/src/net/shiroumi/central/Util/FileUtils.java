package net.shiroumi.central.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileUtils {

	private static Charset _charset = Charset.defaultCharset();

	public static Charset getCharset() {
		return _charset;
	}

	public static void setCharset(Charset par1Charset) {
		_charset = par1Charset;
	}

	public static void setCharset(String par1CharsetName) {
		setCharset(Charset.forName(par1CharsetName));
	}

	@SuppressWarnings("resource")
	public static void copy(File src, File dest) throws IOException {
		if (!src.exists()) return;
		if (!dest.exists()) dest.mkdir();
		for (File f : src.listFiles()) {
			File target = new File(dest, f.getName());
			if (f.isDirectory()) copy(f, target);
			if (f.isFile()) {
				FileChannel ic = new FileInputStream(f).getChannel();
				FileChannel oc = new FileOutputStream(target).getChannel();
				try {
					ic.transferTo(0, ic.size(), oc);
				} finally {
					ic.close();
					oc.close();
				}
			}
		}
	}

	public static void delete(File src) throws IOException {
		if (!src.exists()) return;
		if (!src.isDirectory()) {
			src.delete();
			return;
		}
		for (File f : src.listFiles()) {
			if (f.isDirectory()) delete(f);
			if (f.isFile()) f.delete();
		}
	}

	public static String[] readFileAsStringArray(File file) throws IOException {
		return readFileAsString(file).split("\n");
	}

	public static String readFileAsString(File file) throws IOException {
		return new String(readFileAsByteArray(file), _charset);
	}

	public static byte[] readFileAsByteArray(File file) throws IOException {
		ByteBuffer bb;
		FileChannel fc = null;
		try {
			fc = new FileInputStream(file).getChannel();
			bb = ByteBuffer.allocate((int) fc.size());
			fc.read(bb);
		} finally {
			if (fc != null) fc.close();
		}
		return bb.array();
	}

	public static void writeFileAsStringArrayWithNewLine(File file, String[] par2DataArray) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (String t : par2DataArray) {
			sb.append(t);
			sb.append('\n');
		}
		writeFileAsString(file, sb.toString());
	}

	public static void writeFileAsStringArray(File file, String[] par2DataArray) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (String t : par2DataArray) {
			sb.append(t);
		}
		writeFileAsString(file, sb.toString());
	}

	public static void writeFileAsString(File file, String par2Data) throws IOException {
		writeFileAsByteArray(file, par2Data.getBytes(_charset));
	}

	public static void writeFileAsByteArray(File file, byte[] par2Data) throws IOException {
		ByteBuffer bb;
		FileChannel fc = null;
		try {
			fc = new FileOutputStream(file).getChannel();
			bb = ByteBuffer.wrap(par2Data);
			fc.write(bb);
		} finally {
			if (fc != null) fc.close();
		}
	}
}
