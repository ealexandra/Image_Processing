package net.codejava.sql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MasiniClass {

	private JFrame frame;
	private JTextField textFieldMarca;
	private JTextField textFieldModel;
	private JTextField textFieldCp;
	private JTextField textFieldAn;
	private JTextField textFieldCuloare;
	private JTextField textFieldKm;



	public MasiniClass(Connection con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 587, 488);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMarca.setBounds(24, 66, 56, 16);
		panel.add(lblMarca);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblModel.setBounds(24, 95, 56, 16);
		panel.add(lblModel);
		
		JLabel lblCombustibil = new JLabel("Combustibil");
		lblCombustibil.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCombustibil.setBounds(24, 124, 77, 16);
		panel.add(lblCombustibil);
		
		JLabel lblCp = new JLabel("CP");
		lblCp.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCp.setBounds(24, 153, 56, 16);
		panel.add(lblCp);
		
		JLabel lblAnfabricatie = new JLabel("AnFabricatie");
		lblAnfabricatie.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAnfabricatie.setBounds(24, 182, 84, 16);
		panel.add(lblAnfabricatie);
		
		JLabel lblCuloare = new JLabel("Culoare");
		lblCuloare.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCuloare.setBounds(24, 211, 56, 16);
		panel.add(lblCuloare);
		
		JLabel lblKmparcursi = new JLabel("KmParcursi");
		lblKmparcursi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblKmparcursi.setBounds(24, 240, 77, 16);
		panel.add(lblKmparcursi);
		
		JLabel lblDateMasina = new JLabel("Date masina");
		lblDateMasina.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 24));
		lblDateMasina.setBounds(56, 24, 166, 29);
		panel.add(lblDateMasina);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setBounds(122, 63, 136, 22);
		panel.add(textFieldMarca);
		textFieldMarca.setColumns(10);
		
		textFieldModel = new JTextField();
		textFieldModel.setBounds(122, 92, 136, 22);
		panel.add(textFieldModel);
		textFieldModel.setColumns(10);
		
		textFieldCp = new JTextField();
		textFieldCp.setBounds(122, 150, 136, 22);
		panel.add(textFieldCp);
		textFieldCp.setColumns(10);
		
		textFieldAn = new JTextField();
		textFieldAn.setBounds(122, 179, 136, 22);
		panel.add(textFieldAn);
		textFieldAn.setColumns(10);
		
		textFieldCuloare = new JTextField();
		textFieldCuloare.setBounds(122, 208, 136, 22);
		panel.add(textFieldCuloare);
		textFieldCuloare.setColumns(10);
		
		textFieldKm = new JTextField();
		textFieldKm.setBounds(122, 237, 136, 22);
		panel.add(textFieldKm);
		textFieldKm.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 292, 515, 136);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		
		JButton btnAdd = new JButton("View");
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnAdd.setBackground(SystemColor.controlShadow);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("");
		        try {
		            Statement st = con.createStatement();
		            ResultSet rs =st.executeQuery("select * from Masini ");
		            while (rs.next()){
		            	textArea.append("******************\n");
		            	textArea.append("Id: " + rs.getInt(1) + "\n");         
		            	textArea.append("Marca: " + rs.getString(2) + "\n");
		            	textArea.append("Model: " + rs.getString(3) + "\n");
		            	textArea.append("Combustibil: " + rs.getString(4) + "\n");
		            	textArea.append("CP: " + rs.getString(5) + "\n");
		            	textArea.append("An Fabricatie: " + rs.getInt(6) + "\n");
		            	textArea.append("Culoare: " + rs.getString(7) + "\n");
		            	textArea.append("Km parcursi: " + rs.getInt(8) + "\n");
		            	textArea.append("******************\n");
		            }
		        } catch (SQLException e){
		            e.printStackTrace();
		        }
				
			}
		});
		btnAdd.setBounds(314, 178, 97, 25);
		panel.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnDelete.setBackground(SystemColor.controlShadow);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteMasini objDelete = new DeleteMasini(con);
			}
		});
		btnDelete.setBounds(314, 236, 97, 25);
		panel.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnUpdate.setBackground(SystemColor.controlShadow);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateMasini objUpdate = new UpdateMasini(con);
			}
		});
		btnUpdate.setBounds(442, 236, 97, 25);
		panel.add(btnUpdate);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Diesel", "Benzina", "Hibrid"}));
		comboBox.setBounds(122, 120, 136, 25);
		panel.add(comboBox);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnAdd_1.setBackground(SystemColor.controlShadow);
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String combustibil = (String)comboBox.getSelectedItem();
				try{
				String sql = "INSERT INTO Masini (Marca, Model, Combustibil, CP, AnFabricatie, Culoare, KmParcursi) VALUES (?, ?, ?, ?, ?, ?, ?)";
				 
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, textFieldMarca.getText());
				statement.setString(2, textFieldModel.getText());
				statement.setString(3, combustibil);
				statement.setString(4, textFieldCp.getText());
				statement.setString(5, textFieldAn.getText());
				statement.setString(6, textFieldCuloare.getText());
				statement.setString(7, textFieldKm.getText());
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
				    System.out.println("A new user was inserted successfully!");
				}
				textFieldMarca.setText("");
				textFieldModel.setText("");
				
				textFieldCp.setText("");
				textFieldAn.setText("");
				textFieldCuloare.setText("");
				textFieldKm.setText("");
				}catch (SQLException e){
		            e.printStackTrace();
		        }
			}
		});
		btnAdd_1.setBounds(442, 178, 97, 25);
		panel.add(btnAdd_1);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/img_car5.png"));
		lblNewLabel.setIcon(img);
		lblNewLabel.setBounds(342, 32, 160, 125);
		panel.add(lblNewLabel);
		

		frame.setVisible(true);
	}
}
