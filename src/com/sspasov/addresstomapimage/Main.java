package com.sspasov.addresstomapimage;

import com.sspasov.addresstomapimage.utils.C;

public class Main {
	/* Launch the application. */
	public static void main(String[] args) {
		AppFrame mFrame = new AppFrame();
		mFrame.setResizable(true);
		mFrame.setSize(C.APP_FRAME_WIDTH, C.APP_FRAME_HEIGHT);
		mFrame.setVisible(true);
	}
}
