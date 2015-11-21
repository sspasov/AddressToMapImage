package com.sspasov.addresstomapimage.models;

import java.awt.Image;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import com.sspasov.addresstomapimage.utils.C;

public class StaticMap {
	// -----------------------------------------------------------------------------------------
	// Fields
	// -----------------------------------------------------------------------------------------
	private String mAddress;
	private String mMapType;
	private Image mMapImage;
	private String mImageUrl;

	// -----------------------------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------------------------
	public StaticMap(String address, String mapType) {
		mAddress = address;
		mMapType = mapType;
		generateUrl();
	}

	public StaticMap(String address) {
		mAddress = address;
		mMapType = C.MAP_TYPE_ROADMAP;
		generateUrl();
	}

	public StaticMap() {
		mAddress = null;
		mImageUrl = null;
		mMapType = null;
	}

	// -----------------------------------------------------------------------------------------
	// Public methods
	// -----------------------------------------------------------------------------------------
	public void setMapType(String type) {
		mMapType = type;
	}

	public void setAddress(String adr) {
		mAddress = adr;
		generateUrl();
	}

	public Image getImage() throws IOException {
		try {
			URL url = new URL(mImageUrl); // open url connectio
			mMapImage = ImageIO.read((url)); // load image from url
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return mMapImage;
	}

	// -----------------------------------------------------------------------------------------
	// Private methods
	// -----------------------------------------------------------------------------------------
	private void generateUrl() {
		try {
			mImageUrl = "http://maps.google.com/maps/api/staticmap?center=" + URLEncoder.encode(mAddress, "UTF-8")
					+ "&size=640x640&maptype=" + mMapType + "&markers=size:mid%7Ccolor:red%7C"
					+ URLEncoder.encode(mAddress, "UTF-8") + "&sensor=false";
			System.out.println("Url = " + mImageUrl);
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
	}

}
