package com.sspasov.addresstomapimage.models;

import java.awt.Image;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

public class StaticMap {

	private String address;
	private String mapType;
	private Image map;
	private String imageUrl;

	public StaticMap(String address, String mapType) {
		this.address = address;
		this.mapType = mapType;
		createURL();
	}

	public StaticMap(String address) {
		this.address = address;
		this.mapType = "roadmap";
		createURL();
	}

	public StaticMap() {
		this.address = null;
		this.imageUrl = null;
		this.mapType = null;
	}

	public void setMapType(String type) {
		this.mapType = type;
	}

	public void setAddress(String adr) {
		this.address = adr;
		createURL();
	}

	private void createURL() {
		try {
			this.imageUrl = "http://maps.google.com/maps/api/staticmap?center=" + URLEncoder.encode(address, "UTF-8")
					+ "&size=640x640&maptype=" + mapType + "&markers=size:mid%7Ccolor:red%7C"
					+ URLEncoder.encode(address, "UTF-8") + "&sensor=false";
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
	}

	public Image getImage() throws IOException {
		try {
			URL url = new URL(imageUrl); // open url connectio
			map = ImageIO.read((url)); // load image from url
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return map;
	}
}
