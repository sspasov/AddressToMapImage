package com.sspasov.addresstomapimage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultEditorKit;

public class AppFrame extends JFrame {
	private static final long serialVersionUID = 3754135940022783903L;

	private final String VERSION = "0.9";
	private JPanel contentPane;
	private JTextField textField;
	private JLabel mapImage;
	private JRootPane rootPane;
	private String textFieldInput;
	private StaticMap map;
	private JComboBox mapType;
	private ImageImplement image;
	private String[] typesOfMaps = { "roadmap", "satellite", "terrain", "hybrid" };

	public AppFrame() {
		setFrameTitle("Address To Google Map");

		setMenuBar();

		map = new StaticMap();

		setLayout();
	}

	private void setLayout() {
		rootPane = getRootPane();

		contentPane = new JPanel();
		contentPane.setLayout(null);

		JLabel lblEnterAddress = new JLabel("Enter Address:");
		lblEnterAddress.setBounds(15, 10, 125, 25);
		lblEnterAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblEnterAddress);

		textField = new JTextField();
		textField.setBounds(130, 10, 640, 25);
		textField.setToolTipText("Enter the address here.");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		contentPane.add(textField);

		JButton btnGetMap = new JButton("  Get map");
		btnGetMap.setBounds(785, 10, 125, 25);
		btnGetMap.setToolTipText("Press to search for a map");
		btnGetMap.setIcon(new ImageIcon(AppFrame.class.getResource("/img/search.png")));
		btnGetMap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rootPane.setDefaultButton(btnGetMap);

		// mapImage = new JLabel();
		// contentPane.add(mapImage);
		// mapImage.setBounds(125, 80, 640, 640);
		image = new ImageImplement();
		contentPane.add(image);

		btnGetMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldInput = textField.getText();
				// System.out.println(textFieldInput);

				if (textFieldInput.isEmpty() == true) {
					image.setImage(new ImageIcon(AppFrame.class.getResource("/noImage.png")).getImage());
					System.out.println("in NOIMAGE");
					// mapImage.setIcon(new
					// ImageIcon(AppFrame.class.getResource("/noImage.png")));
					// mapImage.setVisible(true);
					repaint();
				} else {
					map.setAddress(textFieldInput);
					try {
						mapImage.setIcon(new ImageIcon(map.getImage()));
						mapImage.setVisible(true);
						repaint();
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		contentPane.add(btnGetMap);

		mapType = new JComboBox(typesOfMaps);
		mapType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox cb = (JComboBox) event.getSource();
				map.setMapType((String) cb.getSelectedItem());
			}
		});
		mapType.setSelectedIndex(0);
		mapType.setToolTipText("Select map type.");
		mapType.setBounds(785, 46, 125, 25);
		contentPane.add(mapType);

		setContentPane(contentPane);
	}

	private void setMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		/* FILE */
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		menuBar.add(mnFile);

		/* FILE->SAVE */
		menuFileSave(mnFile);

		/* FILE->EXIT */
		menuFileExit(mnFile);

		/* EDIT */
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		menuBar.add(mnEdit);

		/* EDIT->CUT */
		menuEditCut(mnEdit);

		/* EDIT->COPY */
		menuEditCopy(mnEdit);

		/* EDIT->PASTE */
		menuEditPaste(mnEdit);

		/* EDIT->DELETE */
		menuEditDelete(mnEdit);

		/* HELP */
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		menuBar.add(mnHelp);

		/* HELP->HELP */
		menuHelpHelp(mnHelp);

		/* HELP->ABOUT */
		menuHelpAbout(mnHelp);

		setJMenuBar(menuBar);
	}

	private void menuHelpAbout(JMenu mnHelp) {
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				AboutDialog aboutDialog = new AboutDialog(VERSION);
				aboutDialog.setVisible(true);
			}
		});
		mntmAbout.setIcon(new ImageIcon(AppFrame.class.getResource("/img/about.png")));
		mntmAbout.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		mnHelp.add(mntmAbout);
	}

	private void menuHelpHelp(JMenu mnHelp) {
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmHelp.setIcon(new ImageIcon(AppFrame.class.getResource("/img/help.png")));
		mntmHelp.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		mnHelp.add(mntmHelp);
	}

	private void menuEditDelete(JMenu mnEdit) {
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.setToolTipText("Delete selection");
		mntmDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				textField.replaceSelection("");
			}
		});
		mntmDelete.setIcon(new ImageIcon(AppFrame.class.getResource("/img/delete.png")));
		mntmDelete.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mnEdit.add(mntmDelete);
	}

	private void menuEditPaste(JMenu mnEdit) {
		JMenuItem mntmPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
		mntmPaste.setText("Paste");
		mntmPaste.setIcon(new ImageIcon(AppFrame.class.getResource("/img/paste.png")));
		mntmPaste.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		mnEdit.add(mntmPaste);
	}

	private void menuEditCopy(JMenu mnEdit) {
		JMenuItem mntmCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
		mntmCopy.setText("Copy");
		mntmCopy.setToolTipText("Copy selection to clipboard");
		mntmCopy.setIcon(new ImageIcon(AppFrame.class.getResource("/img/copy.png")));
		mntmCopy.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mnEdit.add(mntmCopy);
	}

	private void menuEditCut(JMenu mnEdit) {
		JMenuItem mntmCut = new JMenuItem(new DefaultEditorKit.CutAction());
		mntmCut.setText("Cut");
		mntmCut.setToolTipText("Cut selection to clipboard");
		mntmCut.setIcon(new ImageIcon(AppFrame.class.getResource("/img/cut.png")));
		mntmCut.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mnEdit.add(mntmCut);
	}

	private void menuFileExit(JMenu mnFile) {
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setToolTipText("Exit application");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		mntmExit.setHorizontalAlignment(SwingConstants.LEFT);
		mntmExit.setIcon(new ImageIcon(AppFrame.class.getResource("/img/exit.png")));
		mntmExit.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		mnFile.add(mntmExit);
	}

	private void menuFileSave(JMenu mnFile) {
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setToolTipText("Save map");
		mntmSave.setHorizontalAlignment(SwingConstants.LEFT);
		mntmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				SaveDialog saveDialog = new SaveDialog();
				try {
					saveDialog.saveImage(map.getImage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		});
		mntmSave.setIcon(new ImageIcon(AppFrame.class.getResource("/img/save.png")));
		mntmSave.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);
	}

	private void setFrameTitle(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 936, 626);
	}
}
