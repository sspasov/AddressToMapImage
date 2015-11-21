package com.sspasov.addresstomapimage.dialogs;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.sspasov.addresstomapimage.utils.C;

public class AboutDialog extends JDialog {
	private static final long serialVersionUID = 4441502322395765067L;
	
	private final JPanel contentPanel;

	public AboutDialog() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("About");
		setBounds(100, 100, 450, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Address To Map ver. " + C.APP_VERSION + "\r\n\r\nРазработили: Здравко Атанасов, Радослав Йонов, Станимир Спасов.\r\n\r\nПрограмата е курсова работа по Програмни среди в ТУ София - филиал Пловдив.\r\n\r\nПловдив, 2014г.");
		textPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		textPane.setBounds(10, 11, 424, 145);
		contentPanel.add(textPane);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Close");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				dispose();
				repaint();
			}
		});
		cancelButton.setToolTipText("Close window");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
}
