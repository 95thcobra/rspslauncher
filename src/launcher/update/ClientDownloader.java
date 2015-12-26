package launcher.update;

import java.io.File;

import launcher.Configuration;
import launcher.core.Checksum;
import launcher.core.Frame;

public class ClientDownloader implements Runnable {

	private File file = new File(Configuration.CLIENT_LOCATION);

	public void download() {
		Thread thread = new Thread(this);
		thread.start();
	}

	public void update() {
		Thread thread = new Thread(this);
		thread.start();
	}

	private boolean downloadClient() {
		if (!file.exists()) {
			new Downloader(Configuration.CLIENT_URL).download();
			return true;
		}
		return false;
	}

	@Override
	public void run() {
		if (downloadClient()) {
			return;
		}
		if (!Checksum.isClientUpToDate()) {
			Frame.label.setText("Updating client...");
			file.delete();
			downloadClient();
		}
	}
}