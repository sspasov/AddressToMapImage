package com.sspasov.addresstomapimage.models;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImageImplement extends JPanel {
	private Image image;

	public ImageImplement() {
		// TODO Auto-generated constructor stub
	}

	public void setImage(Image img) {
		image = img;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(image, 125, 80, null);
		System.out.println("in II");
	}
}
