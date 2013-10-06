package net.squarep.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import net.shiroumi.central.Util.Util;
import net.shiroumi.central.Util.ZipFileBuilder;

public class TestMain {
	public static void main(String args[]) throws IOException {
		System.out.println("Test Start.");
		long time = System.currentTimeMillis();
		System.out.println(Util.MD5Sum("Yeah!!"));
		ZipFileBuilder zfb = new ZipFileBuilder("srcs.zip");
		try {
			zfb.copyFileToZip(new File("src/"), "src/");
		} finally {
			zfb.close();
		}
		System.out.println(Util.MD5Sum(new FileInputStream("srcs.zip")));
		System.out.printf("Test End(time : %d).%n", System.currentTimeMillis() - time);
	}
}
