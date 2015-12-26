package launcher.update;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import launcher.Configuration;
import launcher.core.Checksum;
import launcher.core.Frame;

public class CacheDownloader implements Runnable {

	private final int BUFFER = 4024;
	private final int VERSION = 1;
	private String cacheLink = Configuration.CACHE_URL;

	private String fileToExtract = getCacheDir() + getArchivedName();

	public CacheDownloader() {
	}

	private String getCacheDir() {
		String cacheLoc = Configuration.CACHE_DIR;
		File cacheDir = new File(cacheLoc);
		if (!cacheDir.exists()) {
			cacheDir.mkdir();
		}
		return cacheLoc;
	}

	private String getCacheLink() {
		return cacheLink;
	}

	private int getCacheVersion() {
		return VERSION;
	}

	public CacheDownloader downloadCache() {
		try {
			File location = new File(getCacheDir());
			File version = new File(getCacheDir() + "/cacheVersion" + getCacheVersion() + ".dat");
			if (!location.exists()) {
				downloadFile(getCacheLink(), getArchivedName());

				unZip();

				BufferedWriter versionFile = new BufferedWriter(new FileWriter(getCacheDir() + "/cacheVersion" + getCacheVersion() + ".dat"));
				versionFile.close();
			} else {
				if (!version.exists()) {
					downloadFile(getCacheLink(), getArchivedName());

					unZip();

					BufferedWriter versionFile = new BufferedWriter(new FileWriter(getCacheDir() + "/cacheVersion" + getCacheVersion() + ".dat"));
					versionFile.close();
				}
			}
			if (!Checksum.isCacheUpToDate()) {
				downloadFile(getCacheLink(), getArchivedName());

				unZip();
			}
			Frame.label.setText("Ready.");
		} catch (Exception e) {

		}
		return null;
	}

	private void downloadFile(String adress, String localFileName) {
		OutputStream out = null;
		URLConnection conn;
		InputStream in = null;

		try {

			URL url = new URL(adress);
			out = new BufferedOutputStream(new FileOutputStream(getCacheDir() + "/" + localFileName));

			conn = url.openConnection();
			in = conn.getInputStream();

			byte[] data = new byte[BUFFER];

			int numRead;
			long numWritten = 0;
			int length = conn.getContentLength();

			while ((numRead = in.read(data)) != -1) {
				out.write(data, 0, numRead);
				numWritten += numRead;

				int percentage = (int) (((double) numWritten / (double) length) * 100D);
				Frame.label.setText("Updating cache: " + percentage + "%");
			}

			Frame.label.setText("Ready.");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ioe) {
			}
		}

	}

	private String getArchivedName() {
		return Configuration.CACHE_SAVE;
	}

	private void unZip() {

		try {
			InputStream in = new BufferedInputStream(new FileInputStream(fileToExtract));
			ZipInputStream zin = new ZipInputStream(in);
			ZipEntry e;

			while ((e = zin.getNextEntry()) != null) {

				if (e.isDirectory()) {
					(new File(getCacheDir() + e.getName())).mkdir();
				} else {

					if (e.getName().equals(fileToExtract)) {
						unzip(zin, fileToExtract);
						break;
					}
					unzip(zin, getCacheDir() + e.getName());
				}
			}
			zin.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void unzip(ZipInputStream zin, String s) throws IOException {
		FileOutputStream out = new FileOutputStream(s);
		byte[] b = new byte[BUFFER];
		int len = 0;

		while ((len = zin.read(b)) != -1) {
			out.write(b, 0, len);
		}
		out.close();
	}

	public void download() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		downloadCache();
	}
}