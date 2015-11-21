package com.sspasov.addresstomapimage.ui.dialogs;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class SaveDialog extends JDialog {
	// -----------------------------------------------------------------------------------------
	// Constants
	// -----------------------------------------------------------------------------------------
	private static final long serialVersionUID = -2764404884551391207L;

	// -----------------------------------------------------------------------------------------
	// Fields
	// -----------------------------------------------------------------------------------------
	private JFileChooser mFileChooser;

	// -----------------------------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------------------------
	public SaveDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		mFileChooser = new JFileChooser();
		mFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		mFileChooser.addChoosableFileFilter(new JpgSaveFilter());
		mFileChooser.addChoosableFileFilter(new PngSaveFilter());
		mFileChooser.addChoosableFileFilter(new BMPSaveFilter());
		mFileChooser.addChoosableFileFilter(new GifSaveFilter());
	}

	// -----------------------------------------------------------------------------------------
	// Public methods
	// -----------------------------------------------------------------------------------------
	public void saveImage(Image buffer) throws IOException {
		int retrival = mFileChooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			String extension = mFileChooser.getFileFilter().getDescription();
			if (extension.equals("*.jpg,*.JPG")) {
				ImageIO.write((RenderedImage) buffer, "jpg", new File(mFileChooser.getSelectedFile() + ".jpg"));
			}
			if (extension.equals("*.png,*.PNG")) {
				ImageIO.write((RenderedImage) buffer, "png", new File(mFileChooser.getSelectedFile() + ".png"));
			}
			if (extension.equals("*.gif,*.GIF")) {
				ImageIO.write((RenderedImage) buffer, "gif", new File(mFileChooser.getSelectedFile() + ".gif"));
			}
			if (extension.equals("*.bmp,*.BMP")) {
				ImageIO.write((RenderedImage) buffer, "bmp", new File(mFileChooser.getSelectedFile() + ".bmp"));
			}
		}
	}

	// -----------------------------------------------------------------------------------------
	// Inner classes
	// -----------------------------------------------------------------------------------------
	class JpgSaveFilter extends FileFilter {
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return false;
			}
			String s = f.getName();
			return s.endsWith(".jpg") || s.endsWith(".JPG");
		}

		public String getDescription() {
			return "*.jpg,*.JPG";
		}
	}

	class PngSaveFilter extends FileFilter {
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return false;
			}
			String s = f.getName();
			return s.endsWith(".png") || s.endsWith(".PNG");
		}

		public String getDescription() {
			return "*.png,*.PNG";
		}
	}

	class BMPSaveFilter extends FileFilter {
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return false;
			}
			String s = f.getName();
			return s.endsWith(".bmp") || s.endsWith(".BMP");
		}

		public String getDescription() {
			return "*.bmp,*.BMP";
		}
	}

	class GifSaveFilter extends FileFilter {
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return false;
			}
			String s = f.getName();
			return s.endsWith(".gif") || s.endsWith(".GIF");
		}

		public String getDescription() {
			return "*.gif,*.GIF";
		}
	}
}
