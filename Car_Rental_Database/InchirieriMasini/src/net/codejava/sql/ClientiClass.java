package net.codejava.sql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ClientiClass {

	private JFrame frame;
	private JTextField textFieldNume;
	private JTextField textFieldPrenume;
	private JTextField textFieldCnp;
	private JTextField textFieldOras;
	private JTextField textFieldStrada;
	private JTextField textFieldNr;
	private JTextField textFieldData;
	private JTextField textFieldTelefon;


	/**
	 * Create the application.
	 */
	public ClientiClass(Connection con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 663, 542);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblDateClienti = new JLabel("Date clienti");
		lblDateClienti.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 26));
		lblDateClienti.setBounds(42, 25, 154, 29);
		panel.add(lblDateClienti);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNume.setBounds(12, 67, 56, 16);
		panel.add(lblNume);
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPrenume.setBounds(12, 96, 56, 16);
		panel.add(lblPrenume);
		
		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCnp.setBounds(12, 125, 56, 16);
		panel.add(lblCnp);
		
		JLabel lblOras = new JLabel("Oras");
		lblOras.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblOras.setBounds(12, 154, 56, 16);
		panel.add(lblOras);
		
		JLabel lblStrada = new JLabel("Strada");
		lblStrada.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblStrada.setBounds(12, 183, 56, 16);
		panel.add(lblStrada);
		
		JLabel lblNumar = new JLabel("Numar");
		lblNumar.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNumar.setBounds(12, 212, 56, 16);
		panel.add(lblNumar);
		
		JLabel lblDataNasterii = new JLabel("Data Nasterii");
		lblDataNasterii.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDataNasterii.setBounds(12, 241, 79, 16);
		panel.add(lblDataNasterii);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTelefon.setBounds(12, 273, 56, 16);
		panel.add(lblTelefon);
		
		JLabel lblPermis = new JLabel("Permis");
		lblPermis.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPermis.setBounds(12, 302, 56, 16);
		panel.add(lblPermis);
		
		textFieldNume = new JTextField();
		textFieldNume.setBounds(127, 64, 116, 22);
		panel.add(textFieldNume);
		textFieldNume.setColumns(10);
		
		textFieldPrenume = new JTextField();
		textFieldPrenume.setBounds(127, 96, 116, 22);
		panel.add(textFieldPrenume);
		textFieldPrenume.setColumns(10);
		
		textFieldCnp = new JTextField();
		textFieldCnp.setBounds(127, 122, 116, 22);
		panel.add(textFieldCnp);
		textFieldCnp.setColumns(10);
		
		textFieldOras = new JTextField();
		textFieldOras.setBounds(127, 154, 116, 22);
		panel.add(textFieldOras);
		textFieldOras.setColumns(10);
		
		textFieldStrada = new JTextField();
		textFieldStrada.setBounds(127, 180, 116, 22);
		panel.add(textFieldStrada);
		textFieldStrada.setColumns(10);
		
		textFieldNr = new JTextField();
		textFieldNr.setBounds(127, 209, 116, 22);
		panel.add(textFieldNr);
		textFieldNr.setColumns(10);
		
		textFieldData = new JTextField();
		textFieldData.setText("YYYY.MM.DD");
		textFieldData.setBounds(127, 238, 116, 22);
		panel.add(textFieldData);
		textFieldData.setColumns(10);
		
		textFieldTelefon = new JTextField();
		textFieldTelefon.setBounds(127, 270, 116, 22);
		panel.add(textFieldTelefon);
		textFieldTelefon.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 340, 621, 142);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnView = new JButton("View");
		btnView.setBackground(SystemColor.controlShadow);
		btnView.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("");
		        try {
		            Statement st = con.createStatement();
		            ResultSet rs =st.executeQuery("select * from Clienti ");
		            while (rs.next()){
		            	textArea.append("******************\n");
		            	textArea.append("Id: " + rs.getInt(1) + "\n");         
		            	textArea.append("Nume: " + rs.getString(2) + "\n");
		            	textArea.append("Prenume: " + rs.getString(3) + "\n");
		            	textArea.append("CNP: " + rs.getString(4) + "\n");
		            	textArea.append("Oras: " + rs.getString(5) + "\n");
		            	textArea.append("Strada: " + rs.getString(6) + "\n");
		            	textArea.append("Numar: " + rs.getString(7) + "\n");
		            	textArea.append("Data Nasterii: " + rs.getDate(8) + "\n");
		            	textArea.append("Telefon: " + rs.getString(9) + "\n");
		            	textArea.append("Permis: " + rs.getString(10) + "\n");
		            	textArea.append("******************\n");
		            }
		        } catch (SQLException w){
		            w.printStackTrace();
		        }
			}
		});
		btnView.setBounds(320, 221, 97, 25);
		panel.add(btnView);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"DA", "NU"}));
		comboBox.setBounds(127, 302, 116, 25);
		panel.add(comboBox);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnAdd.setBackground(SystemColor.controlShadow);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String permis = (String)comboBox.getSelectedItem();
				try{
					String sql = "INSERT INTO Clienti (Nume, Prenume, CNP, Oras, Strada, Nr, DataNasterii, Telefon, Permis) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
					 
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, textFieldNume.getText());
					statement.setString(2, textFieldPrenume.getText());
					statement.setString(3, textFieldCnp.getText());
					statement.setString(4, textFieldOras.getText());
					statement.setString(5, textFieldStrada.getText());
					statement.setString(6, textFieldNr.getText());
					statement.setString(7, textFieldData.getText());
					statement.setString(8, textFieldTelefon.getText());
					statement.setString(9, permis);
					int rowsInserted = statement.executeUpdate();
					if (rowsInserted > 0) {
					    System.out.println("A new user was inserted successfully!");
					}
					textFieldNume.setText("");
					textFieldPrenume.setText("");
					textFieldCnp.setText("");
					textFieldOras.setText("");
					textFieldStrada.setText("");
					textFieldNr.setText("");
					textFieldData.setText("YYYY.MM.DD");
					textFieldTelefon.setText("");
					
					}catch (SQLException w){
			            w.printStackTrace();
			        }
			}
		});
		btnAdd.setBounds(320, 283, 97, 25);
		panel.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(SystemColor.controlShadow);
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteClienti objDelete = new DeleteClienti(con);

			}
		});
		btnDelete.setBounds(461, 221, 97, 25);
		panel.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(SystemColor.controlShadow);
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateClienti objUpdate = new UpdateClienti(con);
			}
		});
		btnUpdate.setBounds(461, 283, 97, 25);
		panel.add(btnUpdate);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon img1 = new ImageIcon(this.getClass().getResource("/img_car8.png"));
		lblNewLabel.setIcon(img1);
		lblNewLabel.setBounds(338, 46, 195, 160);
		panel.add(lblNewLabel);
		

		
		frame.setVisible(true);
	}
}
