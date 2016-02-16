package launcher;

import java.io.File;

public class Configuration {

	public static final String TITLE = "Launcher";

	/**
	 * Update paths
	 */
	public static final String CLIENT_VERSION_URL = "http://ownxile.org/clientversion.txt";
	public static final String CACHE_VERSION_URL = "http://ownxile.org/cacheversion.txt";
	public static final String LOCAL_CLIENT_VERSION_PATH = System.getProperty("user.home") + File.separator + "clientversion.txt";
	public static final String LOCAL_CACHE_VERSION_PATH = System.getProperty("user.home") + File.separator + "cacheversion.txt";

	/**
	 * Client
	 */
	public static final String CLIENT_URL = "http://ownxile.org/OwnXilePlay.jar";
	public static final String CLIENT_SAVE_NAME = "OwnXilePlay.jar";
	public static final String CLIENT_LOCATION = System.getProperty("user.home") + File.separator + CLIENT_SAVE_NAME;

	/**
	 * Cache
	 */
	public static final String CACHE_URL = "http://guthix.org/cache.zip";
	public static final String CACHE_DIRECTORY = System.getProperty("user.home") + File.separator + "eliminatecache1" + File.separator;
	public static final String CACHE_ZIP_NAME = "cache.zip";
	public static final String CACHE_LOCATION = CACHE_DIRECTORY + File.separator + CACHE_ZIP_NAME;

	/**
	 * Links
	 */
	public static final String FORUM_URL = "http://google.com/";
	public static final String VOTE_URL = "http://google.com/";
	public static final String STORE_URL = "http://google.com/";
	public static final String HIGHSCORES_URL = "http://google.com/";
}
