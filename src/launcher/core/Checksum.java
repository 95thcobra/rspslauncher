package launcher.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;

import launcher.Configuration;

public class Checksum {

	public static final boolean USE_TEXTFILE_UPDATING = true;
	
	public static String getLocalClientVersion() {
		String version = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(Configuration.LOCAL_CLIENT_VERSION_PATH));
			version = br.readLine();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("local client version: " + version);
		return version;
	}

	public static String getLocalCacheVersion() {
		String version = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(Configuration.LOCAL_CACHE_VERSION_PATH));
			version = br.readLine();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("local cache version: " + version);
		return version;
	}

	public static String getRemoteClientVersion() {
		String version = "";
		try {
			URL url = new URL(Configuration.CLIENT_VERSION_URL);
			Scanner s = new Scanner(url.openStream());
			version = s.nextLine();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("remote client version: " + version);
		return version;
	}

	public static String getRemoteCacheVersion() {
		String version = "";
		try {
			URL url = new URL(Configuration.CACHE_VERSION_URL);
			Scanner s = new Scanner(url.openStream());
			version = s.nextLine();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("remote cache version: " + version);
		return version;
	}

	public static void writeCurrentClientVersion() {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(Configuration.LOCAL_CLIENT_VERSION_PATH));
			System.out.println("write client: " + getRemoteClientVersion());
			writer.write(getRemoteClientVersion());
			writer.close();
		} catch (Exception e) {
			System.out.println("failed writing client version");
		}
	}

	public static void writeCurrentCacheVersion() {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(Configuration.LOCAL_CACHE_VERSION_PATH));
			System.out.println("write cache: " + getRemoteCacheVersion());
			writer.write(getRemoteCacheVersion());
			writer.close();
		} catch (Exception e) {
			System.out.println("failed writing cache version");
		}
	}

	public static void writeUpdateToFileClient() {
		File file = new File(Configuration.LOCAL_CLIENT_VERSION_PATH);
		if (!file.exists()) {
			try {
				file.createNewFile();

			} catch (Exception e) {
			}
		}
		writeCurrentClientVersion();
	}

	public static void writeUpdateToFileCache() {
		File file = new File(Configuration.LOCAL_CACHE_VERSION_PATH);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
			}
		}
		writeCurrentCacheVersion();
	}

	public static boolean handleTextClientUpdate() {
		File file = new File(Configuration.LOCAL_CLIENT_VERSION_PATH);
		if (!file.exists()) {
			return false;
		} else if (!getLocalClientVersion().equals(getRemoteClientVersion())) {
			return false;
		}
		return true;
	}

	public static boolean handleTextCacheUpdate() {
		File file = new File(Configuration.LOCAL_CACHE_VERSION_PATH);
		if (!file.exists()) {
			return false;
		} else if (!getLocalCacheVersion().equals(getRemoteCacheVersion())) {
			return false;
		}
		return true;
	}

	public static boolean isClientUpToDate() {
		if (USE_TEXTFILE_UPDATING) {
			return handleTextClientUpdate();
		}

		File client = new File(Configuration.CLIENT_LOCATION);
		String localChecksum = getLocalChecksum(client);
		String remoteChecksum = getRemoteChecksum(Configuration.CLIENT_URL);
		return localChecksum.equals(remoteChecksum);
	}

	public static boolean isCacheUpToDate() {
		if (USE_TEXTFILE_UPDATING) {
			return handleTextCacheUpdate();
		}

		File cache = new File(Configuration.CACHE_LOCATION);
		String localChecksum = getLocalChecksum(cache);
		String remoteChecksum = getRemoteChecksum(Configuration.CACHE_URL);
		return localChecksum.equals(remoteChecksum);
	}

	public static String getLocalChecksum(File file) {
		try (FileInputStream fileInputStream = new FileInputStream(file)) {
			return MD5Hash(fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getRemoteChecksum(String url) {
		try (InputStream stream = new URL(url).openStream()) {
			return MD5Hash(stream);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String MD5Hash(InputStream stream) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] dataBytes = new byte[1024];
		int nread = 0;
		while ((nread = stream.read(dataBytes)) != -1) {
			md.update(dataBytes, 0, nread);
		}
		return Arrays.toString(md.digest());
	}

}
