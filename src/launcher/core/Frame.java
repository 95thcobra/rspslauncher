package launcher.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import launcher.Configuration;
import launcher.utility.Utility;
import launcher.core.buttons.Button;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	public static JLabel label = new JLabel("");
	public static JButton btnUpdate = new Button("Fetch Update", new Rectangle(200, 50, 130, 60));
	
	public Frame() {
		setPreferredSize(new Dimension(850, 300));
		setTitle(Configuration.TITLE);
		setIconImage(Utility.getImage("icon.png"));
	}

	public void init() {
		setup();
		setBackground();
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setBackground() {
		JLabel background = new JLabel(Utility.getImageIcon("background.png"));
		add(background);
	}
	
	public void setup() {
		add(new Button("Play Now", new Rectangle(50, 50, 130, 60)));
		add(btnUpdate);
		add(new Button("Forums", new Rectangle(350, 50, 130, 60)));
		add(new Button("Vote", new Rectangle(500, 50, 130, 60)));
		add(new Button("Store", new Rectangle(650, 50, 130, 60)));

		label.setBounds(50, 150, 500, 50);
		label.setForeground(new Color(116,118,120));
		label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		add(label);
	}
}
