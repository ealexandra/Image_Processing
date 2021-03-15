package net.codejava.sql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UpdateMasini {

	private JFrame frame;
	private JTextField textFieldId;
	private JTextField textFieldUpdate;



	/**
	 * Create the application.
	 */
	public UpdateMasini(Connection con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 497, 337);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblIntroducetiIdulMasinii = new JLabel("Introduceti id-ul masinii careia doriti sa ii faceti update");
		lblIntroducetiIdulMasinii.setBounds(12, 45, 332, 16);
		panel.add(lblIntroducetiIdulMasinii);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(22, 85, 35, 16);
		panel.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(86, 82, 116, 22);
		panel.add(textFieldId);
		textFieldId.setColumns(10);
		
		JRadioButton rdbtnCuloare = new JRadioButton("Culoare");
		rdbtnCuloare.setBounds(12, 181, 127, 25);
		panel.add(rdbtnCuloare);
		
		JRadioButton rdbtnKmParcursi = new JRadioButton("Km Parcursi");
		rdbtnKmParcursi.setBounds(157, 181, 127, 25);
		panel.add(rdbtnKmParcursi);
		
		textFieldUpdate = new JTextField();
		textFieldUpdate.setBounds(86, 215, 116, 22);
		panel.add(textFieldUpdate);
		textFieldUpdate.setColumns(10);
		
		JTextArea txtrSelectatiCampulAsupra = new JTextArea();
		txtrSelectatiCampulAsupra.setBackground(SystemColor.control);
		txtrSelectatiCampulAsupra.setText("Selectati campul asupra caruia doriti sa aduceti\r\nmodificari si introduceti noua valoare");
		txtrSelectatiCampulAsupra.setBounds(12, 119, 455, 53);
		panel.add(txtrSelectatiCampulAsupra);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOk.setBackground(SystemColor.controlShadow);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idMasinaString = textFieldId.getText();
				int id = Integer.parseInt(idMasinaString);
				if ( rdbtnCuloare.isSelected()){
					String culoareUpdate = textFieldUpdate.getText();
					try{
						String sql = "UPDATE Masini SET Culoare=? WHERE MasinaID=?";
					 
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, culoareUpdate);
						statement.setInt(2, id);
					 
						int rowsUpdated = statement.executeUpdate();
						if (rowsUpdated > 0) {
							System.out.println("An existing user was updated successfully!");
						}
					}catch (SQLException w){
			            w.printStackTrace();
			        }
				}
				if ( rdbtnKmParcursi.isSelected()){
					String kmUpdate = textFieldUpdate.getText();
					try{
						String sql = "UPDATE Masini SET KmParcursi=? WHERE MasinaID=?";
					 
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, kmUpdate);
						statement.setInt(2, id);
					 
						int rowsUpdated = statement.executeUpdate();
						if (rowsUpdated > 0) {
							System.out.println("An existing user was updated successfully!");
						}
					}catch (SQLException w){
			            w.printStackTrace();
			        }
				}
				
				frame.setVisible(false);
			}
		});
		btnOk.setBounds(187, 252, 97, 25);
		panel.add(btnOk);
		frame.setVisible(true);
	}
}
