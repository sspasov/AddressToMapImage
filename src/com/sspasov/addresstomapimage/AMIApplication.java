package com.sspasov.addresstomapimage;

import com.sspasov.addresstomapimage.ui.AppFrame;
import com.sspasov.addresstomapimage.utils.C;

public class AMIApplication {
	// -----------------------------------------------------------------------------------------
	// Main method
	// -----------------------------------------------------------------------------------------
	public static void main(String[] args) {
		AppFrame appFrame = new AppFrame();
		appFrame.setResizable(true);
		appFrame.setSize(C.APP_FRAME_WIDTH, C.APP_FRAME_HEIGHT);
		appFrame.setVisible(true);
	}
}
