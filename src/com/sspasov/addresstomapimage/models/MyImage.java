package com.sspasov.addresstomapimage.models;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class MyImage extends JPanel {
	// -----------------------------------------------------------------------------------------
	// Fields
	// -----------------------------------------------------------------------------------------
	private Image mImage;

	// -----------------------------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------------------------
	public MyImage() {
		// TODO Auto-generated constructor stub
	}

	// -----------------------------------------------------------------------------------------
	// Public methods
	// -----------------------------------------------------------------------------------------
	public void setImage(Image image) {
		System.out.println("Image = " + image.getSource());
		mImage = image;
		repaint();
	}

	// -----------------------------------------------------------------------------------------
	// Override methods
	// -----------------------------------------------------------------------------------------
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mImage, 125, 80, null);
		System.out.println("in II");
	}
}
