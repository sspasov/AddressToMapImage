package com.sspasov.addresstomapimage.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.Action;
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

import com.sspasov.addresstomapimage.ui.dialogs.AboutDialog;
import com.sspasov.addresstomapimage.ui.dialogs.SaveDialog;
import com.sspasov.addresstomapimage.models.MyImage;
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
	private JTextField mTextFieldAddress;
	private String mAddress;
	private JLabel mMapImage;
	private StaticMap mStaticMap;

	// -----------------------------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------------------------
	public AppFrame() {
		setFrameTitle(C.APP_FRAME_TITLE);

		setMenuBar();

		mStaticMap = new StaticMap();
		mMapImage = new JLabel();

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

	private JMenuItem createMenuItem(Action action, String title, String tooltip, Font font, String iconPath) {
		JMenuItem menuItem;

		if (action != null) {
			menuItem = new JMenuItem(action);
			menuItem.setText(title);
		} else {
			menuItem = new JMenuItem(title);
		}

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
		menuItem = createMenuItem(null, C.APP_MENU_FILE_SAVE_TITLE, C.APP_MENU_FILE_SAVE_TOOLTIP, font,
				C.APP_MENU_FILE_SAVE_ICON);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				SaveDialog saveDialog = new SaveDialog();
				try {
					saveDialog.saveImage(mStaticMap.getImage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		});
		menuFile.add(menuItem);

		/* FILE->EXIT */
		menuItem = createMenuItem(null, C.APP_MENU_FILE_EXIT_TITLE, C.APP_MENU_FILE_EXIT_TOOLTIP, font,
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
		JMenu menuEdit = new JMenu(C.APP_MENU_EDIT_TITLE);
		menuEdit.setFont(font);
		menuBar.add(menuEdit);

		JMenuItem menuItem;
		/* EDIT->CUT */
		menuItem = createMenuItem(new DefaultEditorKit.CutAction(), C.APP_MENU_EDIT_CUT_TITLE,
				C.APP_MENU_EDIT_CUT_TOOLTIP, font, C.APP_MENU_EDIT_CUT_ICON);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		menuEdit.add(menuItem);

		/* EDIT->COPY */
		menuItem = createMenuItem(new DefaultEditorKit.CopyAction(), C.APP_MENU_EDIT_COPY_TITLE,
				C.APP_MENU_EDIT_COPY_TOOLTIP, font, C.APP_MENU_EDIT_COPY_ICON);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		menuEdit.add(menuItem);

		/* EDIT->PASTE */
		menuItem = createMenuItem(new DefaultEditorKit.PasteAction(), C.APP_MENU_EDIT_PASTE_TITLE,
				C.APP_MENU_EDIT_PASTE_TOOLTIP, font, C.APP_MENU_EDIT_PASTE_ICON);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		menuEdit.add(menuItem);

		/* EDIT->DELETE */
		menuItem = createMenuItem(null, C.APP_MENU_EDIT_DELETE_TITLE, C.APP_MENU_EDIT_DELETE_TOOLTIP, font,
				C.APP_MENU_EDIT_DELETE_ICON);
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
		menuItem = createMenuItem(null, C.APP_MENU_HELP_HELP_TITLE, null, font, C.APP_MENU_HELP_HELP_ICON);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menuHelp.add(menuItem);

		/* HELP->ABOUT */
		menuItem = createMenuItem(null, C.APP_MENU_HELP_ABOUT_TITLE, null, font, C.APP_MENU_HELP_ABOUT_ICON);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				AboutDialog aboutDialog = new AboutDialog();
				aboutDialog.setVisible(true);
			}
		});
		menuHelp.add(menuItem);
	}

	private void setLayout() {
		JRootPane rootPane = getRootPane();

		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);

		Font font = new Font(C.LABEL_FONT_TYPE, Font.PLAIN, C.LABEL_FONT_SIZE);

		JLabel labelEnterAddress = new JLabel(C.LABEL_ENTER_ADDRESS);
		labelEnterAddress.setBounds(15, 10, 125, 25);
		labelEnterAddress.setFont(font);
		contentPane.add(labelEnterAddress);

		mTextFieldAddress = new JTextField();
		mTextFieldAddress.setBounds(130, 10, 640, 25);
		mTextFieldAddress.setToolTipText(C.TEXT_FIELD_ADDRESS_TOOLTIP);
		mTextFieldAddress.setFont(font);
		mTextFieldAddress.setColumns(10);
		contentPane.add(mTextFieldAddress);

		JButton btnGetMap = new JButton(C.BUTTON_GET_MAP_LABEL);
		btnGetMap.setBounds(785, 10, 125, 25);
		btnGetMap.setToolTipText(C.BUTTON_GET_MAP_TOOLTIP);
		btnGetMap.setIcon(new ImageIcon(AppFrame.class.getResource(C.BUTTON_GET_MAP_ICON)));
		btnGetMap.setFont(font);
		rootPane.setDefaultButton(btnGetMap);

		// mapImage = new JLabel();
		// contentPane.add(mapImage);
		// mapImage.setBounds(125, 80, 640, 640);
		MyImage image = new MyImage();
		contentPane.add(image);

		btnGetMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mAddress = mTextFieldAddress.getText();
				System.out.println("Entered address: " + mAddress);

				if (mAddress.isEmpty() == true) {
					image.setImage(new ImageIcon(AppFrame.class.getResource(C.NO_IMAGE)).getImage());
					System.out.println("Address is empty, loading no_image resource");
					// mapImage.setIcon(new
					// ImageIcon(AppFrame.class.getResource("/noImage.png")));
					// mapImage.setVisible(true);
					repaint();
				} else {
					mStaticMap.setAddress(mAddress);
					try {
						mMapImage.setIcon(new ImageIcon(mStaticMap.getImage()));
						mMapImage.setVisible(true);
						repaint();
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		contentPane.add(btnGetMap);

		JComboBox<String> comboBoxMapTypes = new JComboBox<String>(C.MAP_TYPES);
		comboBoxMapTypes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				mStaticMap.setMapType((String) ((JComboBox<String>) event.getSource()).getSelectedItem());
			}
		});
		comboBoxMapTypes.setSelectedIndex(C.MAP_TYPE_DEFAULT_INDEX);
		comboBoxMapTypes.setToolTipText(C.MAP_TYPE_TOOLTIP);
		comboBoxMapTypes.setBounds(785, 46, 125, 25);
		contentPane.add(comboBoxMapTypes);

		setContentPane(contentPane);
	}
}
