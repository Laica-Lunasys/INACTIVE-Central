package net.shiroumi.central.Util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipFileBuilder implements Closeable, Flushable, AutoCloseable {

	private ZipOutputStream zos;

	public ZipFileBuilder(String par1Path) throws IOException {
		this(new File(par1Path));
	}

	public ZipFileBuilder(File par1File) throws IOException {
		zos = new ZipOutputStream(new FileOutputStream(par1File));
	}

	public void createDirectory(String par1InZipPath) throws IOException {
		if (!par1InZipPath.endsWith("/"))
			par1InZipPath = par1InZipPath.concat("/");
		ZipEntry entry = new ZipEntry(par1InZipPath);
		entry.setMethod(ZipEntry.STORED);
		entry.setCrc(0);
		entry.setSize(0);
		zos.putNextEntry(entry);
		zos.closeEntry();
	}

	public void createFile(String par1InZipPath, String par2Path)
			throws IOException {
		createFile(par1InZipPath, new File(par2Path));
	}

	public void createFile(String par1InZipPath, File par2File)
			throws IOException {
		FileChannel fc = null;
		ByteBuffer bb;
		try {
			fc = new FileInputStream(par2File).getChannel();
			bb = ByteBuffer.allocate((int) fc.size());
			bb.order(ByteOrder.nativeOrder());
			fc.read(bb);
			bb.flip();
			createFile(par1InZipPath, bb.array());
		} finally {
			if (fc != null)
				fc.close();
		}
	}

	public void createFile(String par1InZipPath, byte[] par2Data)
			throws IOException {
		ZipEntry entry = new ZipEntry(par1InZipPath);
		zos.putNextEntry(entry);
		zos.write(par2Data);
		zos.closeEntry();
	}

	public void copyFileToZip(File src, String dest) throws IOException {
		if (!src.exists())
			return;
		createDirectory(dest);
		for (File f : src.listFiles()) {
			String target = dest + f.getName();
			if (f.isDirectory())
				copyFileToZip(f, target + "/");
			if (f.isFile()) {
				createFile(target, f);
			}
		}
	}

	@Override
	public void close() throws IOException {
		zos.finish();
		zos.close();
	}

	@Override
	public void flush() throws IOException {
		zos.flush();
	}

	public static ZipFileBuilder loadAreadyExistsZipFile(File par1File)
			throws ZipException, IOException {
		ZipFile izf = null;
		ZipFileBuilder zfm = null;
		File tmpFile = new File(par1File.getAbsolutePath() + ".tmp");
		Enumeration<? extends ZipEntry> entries;
		par1File.renameTo(tmpFile);
		try {
			izf = new ZipFile(tmpFile);
			zfm = new ZipFileBuilder(par1File);
			entries = izf.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				if (entry.isDirectory()) {
					zfm.createDirectory(entry.getName());
				} else {
					byte[] data = new byte[(int) entry.getSize()];
					izf.getInputStream(entry).read(data);
					zfm.createFile(entry.getName(), data);
				}
			}
		} catch(FileNotFoundException e) {
			return new ZipFileBuilder(par1File);
		} finally {
			if (izf != null) izf.close();
			tmpFile.delete();
		}
		return zfm;
	}
}