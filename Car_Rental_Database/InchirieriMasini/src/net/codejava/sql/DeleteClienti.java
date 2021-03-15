package net.codejava.sql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class DeleteClienti {

	private JFrame frame;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();



	/**
	 * Create the application.
	 */
	public DeleteClienti(Connection  con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 1);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea txtrIntroducetiIdulCnpul = new JTextArea();
		txtrIntroducetiIdulCnpul.setBackground(SystemColor.menu);
		txtrIntroducetiIdulCnpul.setText("Introduceti id-ul, CNP-ul sau numarul de telefon al persoanei pe care\r\ndoriti sa o eliminati din baza de date");
		txtrIntroducetiIdulCnpul.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtrIntroducetiIdulCnpul.setBounds(12, 26, 408, 54);
		frame.getContentPane().add(txtrIntroducetiIdulCnpul);
		
		JRadioButton rdbtnId = new JRadioButton("ID");
		buttonGroup.add(rdbtnId);
		rdbtnId.setBounds(22, 102, 127, 25);
		frame.getContentPane().add(rdbtnId);
		
		JRadioButton rdbtnCnp = new JRadioButton("CNP");
		buttonGroup.add(rdbtnCnp);
		rdbtnCnp.setBounds(22, 133, 127, 25);
		frame.getContentPane().add(rdbtnCnp);
		
		JRadioButton rdbtnTelefon = new JRadioButton("Telefon");
		buttonGroup.add(rdbtnTelefon);
		rdbtnTelefon.setBounds(22, 163, 127, 25);
		frame.getContentPane().add(rdbtnTelefon);
		
		textField = new JTextField();
		textField.setBounds(200, 134, 142, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOk.setBackground(SystemColor.controlShadow);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnId.isSelected()){
					String idClientString = textField.getText();
					int id = Integer.parseInt(idClientString);
					try{
						String sql = "DELETE FROM Clienti WHERE ClientID=?";
				 
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setInt(1, id);
				 
						int rowsDeleted = statement.executeUpdate();
						if (rowsDeleted > 0) {
							System.out.println("A user was deleted successfully!");
						}
					}catch (SQLException w){
						w.printStackTrace();
					}
					textField.setText("");
				}
				if (rdbtnCnp.isSelected()){
					String cnp = textField.getText();
					try{
						String sql = "DELETE FROM Clienti WHERE CNP=?";
					 
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, cnp);
					 
						int rowsDeleted = statement.executeUpdate();
						if (rowsDeleted > 0) {
							System.out.println("A user was deleted successfully!");
						}
					}catch (SQLException w){
						w.printStackTrace();
					}
				}
				if (rdbtnTelefon.isSelected()){
					String telefon = textField.getText();
					try{
						String sql = "DELETE FROM Clienti WHERE Telefon=?";
					 
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, telefon);
					 
						int rowsDeleted = statement.executeUpdate();
						if (rowsDeleted > 0) {
							System.out.println("A user was deleted successfully!");
						}
					}catch (SQLException w){
						w.printStackTrace();
					}
				}
				frame.setVisible(false);
			}
		});
		btnOk.setBounds(164, 215, 97, 25);
		frame.getContentPane().add(btnOk);
		frame.setVisible(true);
	}
}
