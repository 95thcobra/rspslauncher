package launcher;

import java.io.File;

public class Configuration {

	public static final String TITLE = "OSRS-PVP";

	/**
	 * Update by checking versions textfile.
	 * You only need to update these if USE_TEXTFILE_UPDATING is enabled!
	 */
	public static final boolean USE_TEXTFILE_UPDATING = true;
	public static final String CLIENT_VERSION_URL = "https://dl.dropbox.com/s/yqa8xl3fxulvsis/clientversion.txt";
	public static final String CACHE_VERSION_URL = "https://dl.dropbox.com/s/287w5e05adw9ppk/cacheversion.txt";
	public static final String LOCAL_CLIENT_VERSION_PATH = System.getProperty("user.home") + File.separator + "clientversion.txt";
	public static final String LOCAL_CACHE_VERSION_PATH = System.getProperty("user.home") + File.separator + "cacheversion.txt";
	
	/**
	 * Client
	 */
	//public static final String CLIENT_URL = "https://dl.dropbox.com/s/bytzguqg0f7rc7x/eliminate2.jar";
	public static final String CLIENT_URL = "https://dl.dropbox.com/s/icg7o3votz237zl/eliminate3.jar?dl=0";
	public static final String CLIENT_SAVE = "eliminate.jar";
	public static final String CLIENT_LOCATION = System.getProperty("user.home") + File.separator + CLIENT_SAVE;
	
	/**
	 * Cache
	 */
	//public static final String CACHE_URL = "https://dl.dropbox.com/s/w0mvatqhvuvd834/Cache.zip";
	public static final String CACHE_URL = "https://dl.dropbox.com/s/hy69v5ewurlopps/cachetest.zip";
	public static final String CACHE_DIR = System.getProperty("user.home") + File.separator + "eliminatecache" + File.separator;
	public static final String CACHE_SAVE = "cache.zip";
	public static final String CACHE_LOCATION = CACHE_DIR + File.separator + CACHE_SAVE;
	
	/**
	 * Links
	 */
	public static final String FORUM_URL = "http://google.com/";
	public static final String VOTE_URL = "http://google.com/";
	public static final String STORE_URL = "http://google.com/";
}
