package launcher.utility;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;

public class Utility {
	public static Image getImage(String dir) {
		return Toolkit.getDefaultToolkit().getImage("data" + File.separator + dir);
	}

	public static ImageIcon getImageIcon(String dir) {
		return new ImageIcon("data" + File.separator + dir);
	}
	
	public static void browse(String url) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(new URL(url).toURI());
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    }
	}
}
