package net.codejava.sql;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateClienti {

	private JFrame frame;
	private JTextField textFieldCriteriu;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldUpdate;
	private JTextField textUpdateStrada;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();



	/**
	 * Create the application.
	 */
	public UpdateClienti(Connection con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 563, 439);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JRadioButton rdbtnTelefon = new JRadioButton("Telefon");
		buttonGroup.add(rdbtnTelefon);
		rdbtnTelefon.setBounds(8, 210, 127, 25);
		panel.add(rdbtnTelefon);
		
		JRadioButton rdbtnPermis = new JRadioButton("Permis");
		buttonGroup.add(rdbtnPermis);
		rdbtnPermis.setBounds(139, 210, 127, 25);
		panel.add(rdbtnPermis);
		
		JTextArea txtrIntroducetiIdulClientului = new JTextArea();
		txtrIntroducetiIdulClientului.setBackground(SystemColor.control);
		txtrIntroducetiIdulClientului.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrIntroducetiIdulClientului.setText("Introduceti id-ul clientului sau CNP-ul clientului caruia doriti sa ii faceti\r\nupdate");
		txtrIntroducetiIdulClientului.setBounds(8, 13, 525, 53);
		panel.add(txtrIntroducetiIdulClientului);
		
		JRadioButton rdbtnOras = new JRadioButton("Oras");
		buttonGroup.add(rdbtnOras);
		rdbtnOras.setBounds(270, 210, 127, 25);
		panel.add(rdbtnOras);
		
		JLabel lblCnp = new JLabel("ID/CNP");
		lblCnp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCnp.setBounds(50, 125, 56, 16);
		panel.add(lblCnp);
		
		textFieldCriteriu = new JTextField();
		textFieldCriteriu.setBounds(118, 122, 116, 22);
		panel.add(textFieldCriteriu);
		textFieldCriteriu.setColumns(10);
		
		JRadioButton rdbtnStrada = new JRadioButton("Strada");
		rdbtnStrada.setBounds(395, 210, 127, 25);
		panel.add(rdbtnStrada);
		
		textFieldUpdate = new JTextField();
		textFieldUpdate.setBounds(71, 273, 148, 22);
		panel.add(textFieldUpdate);
		textFieldUpdate.setColumns(10);
		
		textUpdateStrada = new JTextField();
		textUpdateStrada.setBounds(359, 273, 148, 22);
		panel.add(textUpdateStrada);
		textUpdateStrada.setColumns(10);
		
		JLabel lblStrada = new JLabel("Strada");
		lblStrada.setBounds(311, 276, 56, 16);
		panel.add(lblStrada);
		
		JLabel lblSelectatiCampulCaruia = new JLabel("Selectati campul caruia doriti sa ii faceti update");
		lblSelectatiCampulCaruia.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblSelectatiCampulCaruia.setBounds(8, 165, 372, 16);
		panel.add(lblSelectatiCampulCaruia);
		
		JLabel lblDacaDoritiSa = new JLabel("Daca doriti sa faceti update la adresa trebuie sa selectati orasul si strada");
		lblDacaDoritiSa.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblDacaDoritiSa.setBounds(8, 185, 525, 16);
		panel.add(lblDacaDoritiSa);
		
		JRadioButton rdbtnId = new JRadioButton("ID");
		buttonGroup_1.add(rdbtnId);
		rdbtnId.setBounds(50, 73, 127, 25);
		panel.add(rdbtnId);
		
		JRadioButton rdbtnCnp = new JRadioButton("CNP");
		buttonGroup_1.add(rdbtnCnp);
		rdbtnCnp.setBounds(181, 75, 127, 25);
		panel.add(rdbtnCnp);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBackground(SystemColor.controlShadow);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				if(rdbtnCnp.isSelected()){
					String criteriuUpdate = textFieldCriteriu.getText();
					if (rdbtnTelefon.isSelected()){
						String tel = textFieldUpdate.getText();
						String sql = "UPDATE Clienti SET Telefon=? WHERE CNP=?";
						 
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, tel);
						statement.setString(2, criteriuUpdate);
					 
						int rowsUpdated = statement.executeUpdate();
						if (rowsUpdated > 0) {
							System.out.println("An existing user was updated successfully!");
						}
					}
					if (rdbtnPermis.isSelected()){
						String permis = textFieldUpdate.getText();
						String sql = "UPDATE Clienti SET Permis=? WHERE CNP=?";
						 
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, permis);
						statement.setString(2, criteriuUpdate);
					 
						int rowsUpdated = statement.executeUpdate();
						if (rowsUpdated > 0) {
							System.out.println("An existing user was updated successfully!");
						}
					}
					if (rdbtnOras.isSelected()){
						String oras = textFieldUpdate.getText();
						String str = textUpdateStrada.getText();
						String sql = "UPDATE Clienti SET Oras=?, Strada=? WHERE CNP=?";
						 
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, oras);
						statement.setString(2, str);
						statement.setString(3, criteriuUpdate);
					 
						int rowsUpdated = statement.executeUpdate();
						if (rowsUpdated > 0) {
							System.out.println("An existing user was updated successfully!");
						}
					}
			    }
				else{
					String criteriuUpdate = textFieldCriteriu.getText();
					int id = Integer.parseInt(criteriuUpdate);
					if (rdbtnTelefon.isSelected()){
						String tel = textFieldUpdate.getText();
						String sql = "UPDATE Clienti SET Telefon=? WHERE ClientID=?";
						 
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, tel);
						statement.setInt(2, id);
					 
						int rowsUpdated = statement.executeUpdate();
						if (rowsUpdated > 0) {
							System.out.println("An existing user was updated successfully!");
						}
					}
					if (rdbtnPermis.isSelected()){
						String permis = textFieldUpdate.getText();
						String sql = "UPDATE Clienti SET Permis=? WHERE ClientID=?";
						 
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, permis);
						statement.setInt(2, id);
					 
						int rowsUpdated = statement.executeUpdate();
						if (rowsUpdated > 0) {
							System.out.println("An existing user was updated successfully!");
						}
					}
					if (rdbtnOras.isSelected()){
						String oras = textFieldUpdate.getText();
						String str = textUpdateStrada.getText();
						String sql = "UPDATE Clienti SET Oras=?, Strada=? WHERE ClientID=?";
						 
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, oras);
						statement.setString(2, str);
						statement.setInt(3, id);
					 
						int rowsUpdated = statement.executeUpdate();
						if (rowsUpdated > 0) {
							System.out.println("An existing user was updated successfully!");
						}
					}
				}
				}catch (SQLException w){
		            w.printStackTrace();
		        }
				frame.setVisible(false);
			}
		});
		btnOk.setBounds(222, 354, 97, 25);
		panel.add(btnOk);
		
		
		frame.setVisible(true);
	}
}
