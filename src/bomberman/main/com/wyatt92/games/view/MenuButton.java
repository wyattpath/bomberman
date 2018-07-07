package com.wyatt92.games.view;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton
{

	private static final long serialVersionUID = 1L;

	public static final int STANDARD_WIDTH = 400;
	public static final int STANDARD_HEIGHT = 80;
	public static final Font STANDARD_FONT = new Font("Arial", Font.BOLD, 50);

	public MenuButton(String text) {
		super(text);
		this.setPreferredSize(new Dimension(STANDARD_WIDTH, STANDARD_HEIGHT));
		this.setFont(STANDARD_FONT);
		this.setBackground(Color.GRAY);
	}

}
