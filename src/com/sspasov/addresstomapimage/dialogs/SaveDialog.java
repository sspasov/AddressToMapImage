package com.sspasov.addresstomapimage.dialogs;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class SaveDialog extends JDialog {
	private static final long serialVersionUID = -2764404884551391207L;

	private JFileChooser fileChooser;

	public SaveDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.addChoosableFileFilter(new JpgSaveFilter());
		fileChooser.addChoosableFileFilter(new PngSaveFilter());
		fileChooser.addChoosableFileFilter(new BMPSaveFilter());
		fileChooser.addChoosableFileFilter(new GifSaveFilter());
	}

	public void saveImage(Image buffer) throws IOException {
		int retrival = fileChooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			String extension = fileChooser.getFileFilter().getDescription();
			if (extension.equals("*.jpg,*.JPG")) {
				ImageIO.write((RenderedImage) buffer, "jpg", new File(fileChooser.getSelectedFile() + ".jpg"));
			}
			if (extension.equals("*.png,*.PNG")) {
				ImageIO.write((RenderedImage) buffer, "png", new File(fileChooser.getSelectedFile() + ".png"));
			}
			if (extension.equals("*.gif,*.GIF")) {
				ImageIO.write((RenderedImage) buffer, "gif", new File(fileChooser.getSelectedFile() + ".gif"));
			}
			if (extension.equals("*.bmp,*.BMP")) {
				ImageIO.write((RenderedImage) buffer, "bmp", new File(fileChooser.getSelectedFile() + ".bmp"));
			}
		}
	}

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
