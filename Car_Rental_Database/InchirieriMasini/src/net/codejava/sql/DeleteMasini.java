package net.codejava.sql;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class DeleteMasini {

	private JFrame frame;
	private JTextField textFieldID;



	/**
	 * Create the application.
	 */
	public DeleteMasini(Connection con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblIntroducetiIdulMasinii = new JLabel("Introduceti id-ul masinii pe care doriti sa o eliminati\r\ndin baza de date");
		lblIntroducetiIdulMasinii.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblIntroducetiIdulMasinii.setBounds(12, 58, 392, 36);
		panel.add(lblIntroducetiIdulMasinii);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(143, 110, 35, 16);
		panel.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(190, 107, 116, 22);
		panel.add(textFieldID);
		textFieldID.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOk.setBackground(SystemColor.controlShadow);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idMasinaString = textFieldID.getText();
				int id = Integer.parseInt(idMasinaString);
				try{
				String sql = "DELETE FROM Masini WHERE MasinaID=?";
				 
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setInt(1, id);
				 
				int rowsDeleted = statement.executeUpdate();
				if (rowsDeleted > 0) {
				    System.out.println("A user was deleted successfully!");
				}
				}catch (SQLException w){
		            w.printStackTrace();
		        }
				frame.setVisible(false);
			}
		});
		btnOk.setBounds(169, 187, 97, 25);
		panel.add(btnOk);
		frame.setVisible(true);
	}

}
