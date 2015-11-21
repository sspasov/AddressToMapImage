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

import com.sspasov.addresstomapimage.dialogs.AboutDialog;
import com.sspasov.addresstomapimage.dialogs.SaveDialog;
import com.sspasov.addresstomapimage.models.ImageImplement;
import com.sspasov.addresstomapimage.models.StaticMap;
import com.sspasov.addresstomapimage.utils.C;

public class AppFrame extends JFrame {
	// -----------------------------------------------------------------------------------------
	// Constants
	// -----------------------------------------------------------------------------------------
	private static final long serialVersionUID = 3754135940022783903L;

	// -----------------------------------------------------------------------------------------
	// Fields
	// -----------------------------------------------------------------------------------------
	private JPanel mContentPane;
	private JTextField mTextFieldAddress;
	private JLabel mMapImage;
	private JRootPane mRootPane;
	private String mAddress;
	private StaticMap mMap;
	private JComboBox mComboBoxMapTypes;
	private ImageImplement mImage;

	// -----------------------------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------------------------
	public AppFrame() {
		setFrameTitle(C.APP_FRAME_TITLE);

		setMenuBar();

		mMap = new StaticMap();

		setLayout();
	}

	// -----------------------------------------------------------------------------------------
	// Private methods
	// -----------------------------------------------------------------------------------------
	private void setFrameTitle(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 936, 626);
	}

	

	private void setMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		Font font = new Font(C.APP_MENU_FONT_TYPE, Font.PLAIN, C.APP_MENU_FONT_SIZE);

		setMenuFile(menuBar, font);

		setMenuEdit(menuBar, font);

		setMenuHelp(menuBar, font);

		setJMenuBar(menuBar);
	}

	private JMenuItem createMenuItem(String title, String tooltip, Font font, String iconPath) {
		JMenuItem menuItem = new JMenuItem();
		menuItem.setText(title);
		if (tooltip != null) {
			menuItem.setToolTipText(tooltip);
		}
		menuItem.setHorizontalAlignment(SwingConstants.LEFT);
		menuItem.setFont(font);
		menuItem.setIcon(new ImageIcon(AppFrame.class.getResource(iconPath)));

		return menuItem;
	}

	private void setMenuFile(JMenuBar menuBar, Font font) {
		/* FILE */
		JMenu menuFile = new JMenu(C.APP_MENU_FILE_TITLE);
		menuFile.setFont(font);
		menuBar.add(menuFile);

		JMenuItem menuItem;
		/* FILE->SAVE */
		menuItem = createMenuItem(C.APP_MENU_FILE_SAVE_TITLE, C.APP_MENU_FILE_SAVE_TOOLTIP, font,
				C.APP_MENU_FILE_SAVE_ICON);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				SaveDialog saveDialog = new SaveDialog();
				try {
					saveDialog.saveImage(mMap.getImage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		});
		menuFile.add(menuItem);

		/* FILE->EXIT */
		menuItem = createMenuItem(C.APP_MENU_FILE_EXIT_TITLE, C.APP_MENU_FILE_EXIT_TOOLTIP, font,
				C.APP_MENU_FILE_EXIT_ICON);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		menuFile.add(menuItem);
	}

	private void setMenuEdit(JMenuBar menuBar, Font font) {
		/* EDIT */
		JMenu menuEdit = new JMenu("Edit");
		menuEdit.setFont(font);
		menuBar.add(menuEdit);

		/* EDIT->CUT */
		JMenuItem menuItem;
		menuItem = createMenuItem(C.APP_MENU_EDIT_CUT_TITLE, C.APP_MENU_EDIT_CUT_TOOLTIP, font,
				C.APP_MENU_EDIT_CUT_ICON);
		menuItem.setAction(new DefaultEditorKit.CutAction());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		menuEdit.add(menuItem);

		/* EDIT->COPY */
		menuItem = createMenuItem(C.APP_MENU_EDIT_COPY_TITLE, C.APP_MENU_EDIT_COPY_TOOLTIP, font,
				C.APP_MENU_EDIT_COPY_ICON);
		menuItem.setAction(new DefaultEditorKit.CopyAction());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		menuEdit.add(menuItem);

		/* EDIT->PASTE */
		menuItem = createMenuItem(C.APP_MENU_EDIT_PASTE_TITLE, C.APP_MENU_EDIT_PASTE_TOOLTIP, font, C.APP_MENU_EDIT_PASTE_ICON);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		menuItem.setAction(new DefaultEditorKit.PasteAction());
		menuEdit.add(menuItem);

		/* EDIT->DELETE */
		menuItem = createMenuItem(C.APP_MENU_EDIT_DELETE_TITLE, C.APP_MENU_EDIT_DELETE_TOOLTIP, font, C.APP_MENU_EDIT_DELETE_ICON);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				mTextFieldAddress.replaceSelection("");
			}
		});
		menuEdit.add(menuItem);
	}

	private void setMenuHelp(JMenuBar menuBar, Font font) {
		/* HELP */
		JMenu menuHelp = new JMenu(C.APP_MENU_HELP_TITLE);
		menuHelp.setFont(font);
		menuBar.add(menuHelp);

		/* HELP->HELP */
		JMenuItem menuItem;
		menuItem = createMenuItem(C.APP_MENU_HELP_HELP_TITLE, null, font, C.APP_MENU_HELP_HELP_ICON);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menuHelp.add(menuItem);

		/* HELP->ABOUT */
		menuItem = createMenuItem(C.APP_MENU_HELP_ABOUT_TITLE, null, font, C.APP_MENU_HELP_ABOUT_ICON);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				AboutDialog aboutDialog = new AboutDialog();
				aboutDialog.setVisible(true);
			}
		});
		menuHelp.add(menuItem);
	}

	private void menuEditDelete(JMenu mnEdit) {
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.setToolTipText("Delete selection");
		mntmDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				mTextFieldAddress.replaceSelection("");
			}
		});
		mntmDelete.setIcon(new ImageIcon(AppFrame.class.getResource("/img/delete.png")));
		mntmDelete.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mnEdit.add(mntmDelete);
	}

	private void menuEditPaste(JMenu mnEdit) {
		JMenuItem mntmPaste = new JMenuItem();
		mntmPaste.setText("Paste");
		mntmPaste.setIcon(new ImageIcon(AppFrame.class.getResource("/img/paste.png")));
		mntmPaste.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		mnEdit.add(mntmPaste);
	}

	private void setLayout() {
		mRootPane = getRootPane();

		mContentPane = new JPanel();
		mContentPane.setLayout(null);

		JLabel lblEnterAddress = new JLabel("Enter Address:");
		lblEnterAddress.setBounds(15, 10, 125, 25);
		lblEnterAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mContentPane.add(lblEnterAddress);

		mTextFieldAddress = new JTextField();
		mTextFieldAddress.setBounds(130, 10, 640, 25);
		mTextFieldAddress.setToolTipText("Enter the address here.");
		mTextFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mTextFieldAddress.setColumns(10);
		mContentPane.add(mTextFieldAddress);

		JButton btnGetMap = new JButton("  Get map");
		btnGetMap.setBounds(785, 10, 125, 25);
		btnGetMap.setToolTipText("Press to search for a map");
		btnGetMap.setIcon(new ImageIcon(AppFrame.class.getResource("/img/search.png")));
		btnGetMap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mRootPane.setDefaultButton(btnGetMap);

		// mapImage = new JLabel();
		// contentPane.add(mapImage);
		// mapImage.setBounds(125, 80, 640, 640);
		mImage = new ImageImplement();
		mContentPane.add(mImage);

		btnGetMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mAddress = mTextFieldAddress.getText();
				// System.out.println(textFieldInput);

				if (mAddress.isEmpty() == true) {
					mImage.setImage(new ImageIcon(AppFrame.class.getResource("/noImage.png")).getImage());
					System.out.println("in NOIMAGE");
					// mapImage.setIcon(new
					// ImageIcon(AppFrame.class.getResource("/noImage.png")));
					// mapImage.setVisible(true);
					repaint();
				} else {
					mMap.setAddress(mAddress);
					try {
						mMapImage.setIcon(new ImageIcon(mMap.getImage()));
						mMapImage.setVisible(true);
						repaint();
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		mContentPane.add(btnGetMap);

		mComboBoxMapTypes = new JComboBox(C.MAP_TYPES);
		mComboBoxMapTypes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox cb = (JComboBox) event.getSource();
				mMap.setMapType((String) cb.getSelectedItem());
			}
		});
		mComboBoxMapTypes.setSelectedIndex(0);
		mComboBoxMapTypes.setToolTipText("Select map type.");
		mComboBoxMapTypes.setBounds(785, 46, 125, 25);
		mContentPane.add(mComboBoxMapTypes);

		setContentPane(mContentPane);
	}
}
