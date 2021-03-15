package net.codejava.sql;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class GUI_Login {

	private JFrame frame;
	private JTextField userText;
	private JPasswordField passwordText;


    
	/**
	 * Create the application.
	 */
	public GUI_Login(Connection con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 602, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(243, 46, 116, 57);
		lblLogin.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 35));
		panel.add(lblLogin);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setForeground(new Color(0, 0, 0));
		lblUser.setBackground(new Color(0, 51, 153));
		lblUser.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 18));
		lblUser.setBounds(40, 158, 54, 22);
		panel.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 18));
		lblPassword.setBounds(40, 209, 116, 35);
		panel.add(lblPassword);
		
		userText = new JTextField();
		userText.setBounds(158, 157, 116, 22);
		panel.add(userText);
		userText.setColumns(10);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(158, 214, 116, 22);
		panel.add(passwordText);
		
		JLabel lblEroare = new JLabel("");
		lblEroare.setBounds(212, 262, 167, 16);
		panel.add(lblEroare);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(SystemColor.controlShadow);
		btnOk.setBounds(243, 285, 97, 35);
		btnOk.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = userText.getText();
				String pass = passwordText.getText();
				if ( user.equals("angajat") && pass.equals("database") ){
					//System.out.println("Succed");
					GUI_Menu obj = new GUI_Menu(con);
					frame.setVisible(false);
				}
				else
					lblEroare.setText("User sau parola incorecta!");		
			}
		});
		panel.add(btnOk);
		
		JLabel lblCarImage = new JLabel("");
		lblCarImage.setBounds(327, 116, 218, 146);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/img_car1.png"));
		lblCarImage.setIcon(img);
		panel.add(lblCarImage);
		
		frame.setVisible(true);
	}
}
