package launcher;

import java.io.File;

public class Configuration {

	public static final String TITLE = "Guthix";

	/**
	 * Update by checking versions textfile. You only need to update these if
	 * USE_TEXTFILE_UPDATING is enabled!
	 */
	public static final boolean USE_TEXTFILE_UPDATING = true;
	public static final String CLIENT_VERSION_URL = "http://guthix.org/clientversion.txt";
	public static final String CACHE_VERSION_URL = "http://guthix.org/cacheversion.txt";
	public static final String LOCAL_CLIENT_VERSION_PATH = System.getProperty("user.home") + File.separator + "clientversion.txt";
	public static final String LOCAL_CACHE_VERSION_PATH = System.getProperty("user.home") + File.separator + "cacheversion.txt";

	/**
	 * Client
	 */
	public static final String CLIENT_URL = "http://guthix.org/eliminate.jar";
	public static final String CLIENT_SAVE = "eliminate.jar";
	public static final String CLIENT_LOCATION = System.getProperty("user.home") + File.separator + CLIENT_SAVE;

	/**
	 * Cache
	 */
	public static final String CACHE_URL = "http://guthix.org/cache.zip";
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
