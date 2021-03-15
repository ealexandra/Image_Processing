package net.codejava.sql;
import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;

public class GUI_Menu {

	private JFrame frame;


	/**
	 * Create the application.
	 */
	public GUI_Menu(Connection con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 497, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnMasini = new JButton("Masini");
		btnMasini.setBackground(SystemColor.controlShadow);
		btnMasini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MasiniClass objMasini = new MasiniClass(con);
			}
		});
		btnMasini.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		btnMasini.setBounds(71, 54, 112, 40);
		panel.add(btnMasini);
		
		JButton btnClienti = new JButton("Clienti");
		btnClienti.setBackground(SystemColor.controlShadow);
		btnClienti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientiClass objClienti = new ClientiClass(con);
			}
		});
		btnClienti.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		btnClienti.setBounds(71, 112, 112, 40);
		panel.add(btnClienti);
		
		JButton btnCautare = new JButton("Cautare");
		btnCautare.setBackground(SystemColor.controlShadow);
		btnCautare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchClass objSearch = new SearchClass(con);
			}
		});
		btnCautare.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		btnCautare.setBounds(282, 186, 121, 40);
		panel.add(btnCautare);
		
		JButton btnNewButton = new JButton("Interogari");
		btnNewButton.setBackground(SystemColor.controlShadow);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					InterogariClass objInterogari = new InterogariClass(con);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		btnNewButton.setBounds(282, 250, 121, 40);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		ImageIcon img2 = new ImageIcon(this.getClass().getResource("/img_car4.png"));
		lblNewLabel.setIcon(img2);
		lblNewLabel.setBounds(60, 198, 133, 127);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		ImageIcon img1 = new ImageIcon(this.getClass().getResource("/img_car6.png"));
		lblNewLabel_1.setIcon(img1);
		lblNewLabel_1.setBounds(255, 33, 164, 139);
		panel.add(lblNewLabel_1);
				
		frame.setVisible(true);
	}
}
