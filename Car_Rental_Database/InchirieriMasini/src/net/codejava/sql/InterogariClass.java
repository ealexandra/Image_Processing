package net.codejava.sql;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import java.awt.List;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.Font;

public class InterogariClass {

	private JFrame frame;


	public InterogariClass(Connection con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 679, 478);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 661, 431);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(196, 31, 452, 285);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnOptiunea = new JButton("Optiunea 1");
		btnOptiunea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOptiunea.setBackground(SystemColor.controlShadow);
		btnOptiunea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("");
				try{
					textArea.append("Numarul de masini inchiriate de catre fiecare angajat:\n");
					
					Statement st = con.createStatement();
					ResultSet rs =st.executeQuery("select A.Nume, A.Prenume, (select count(*) from Inchirieri I where I.AngajatID = A.AngajatID) " +
							"from Angajati A");
					int i = 1;
					while (rs.next()){
						textArea.append("" + i + ". ");
						textArea.append("Nume: " + rs.getString(1) + "\n");
						textArea.append("Prenume: " + rs.getString(2) + "\n");
						textArea.append("Numar total de masini inchiriate: " + rs.getString(3) + "\n\n");
					i++;
					}
							
				}catch(SQLException w){
		            w.printStackTrace();				
				}
			}
		});
		btnOptiunea.setBounds(45, 57, 116, 27);
		panel.add(btnOptiunea);
		
		JButton btnOptiunea_1 = new JButton("Optiunea 2");
		btnOptiunea_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOptiunea_1.setBackground(SystemColor.controlShadow);
		btnOptiunea_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("");
				try{
					textArea.append("Persoanele care au fost angajate in firma dupa angajatul cu numele Angheloiu:\n");
			
					Statement st = con.createStatement();
					ResultSet rs =st.executeQuery("select A.Nume, A.Prenume " +
							"from Angajati A " + 
							"where DataAngajarii > (select DataAngajarii from Angajati where Nume = 'Angheloiu') " +
							"order by A.Nume, A.Prenume");
					int i = 1;
					while (rs.next()){
						textArea.append("" + i + ". ");
						textArea.append("Nume: " + rs.getString(1) + "\n");
						textArea.append("Prenume: " + rs.getString(2) + "\n\n");
					i++;
					}
							
				}catch(SQLException w){
		            w.printStackTrace();				
				}
			}
		});
		btnOptiunea_1.setBounds(45, 107, 116, 25);
		panel.add(btnOptiunea_1);
		
		JButton btnOptiunea_2 = new JButton("Optiunea 3");
		btnOptiunea_2.setBackground(SystemColor.controlShadow);
		btnOptiunea_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOptiunea_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("");
				try{
					textArea.append("Angajatii care au salariul mai mare decat salariul mediu pe firma:\n");
			
					Statement st = con.createStatement();
					ResultSet rs =st.executeQuery("select A.Nume, A.Prenume, A.Salariu " +
							"from Angajati A " + 
							"where A.Salariu > (select avg(Salariu) from Angajati)");
					int i = 1;
					while (rs.next()){
						textArea.append("" + i + ". ");
						textArea.append("Nume: " + rs.getString(1) + "\n");
						textArea.append("Prenume: " + rs.getString(2) + "\n");
						textArea.append("Salariu: " + rs.getString(3) + "\n\n");
					i++;
					}
							
				}catch(SQLException w){
		            w.printStackTrace();				
				}
			}
		});
		btnOptiunea_2.setBounds(45, 155, 116, 25);
		panel.add(btnOptiunea_2);
		
		JButton btnOptiunea_3 = new JButton("Optiunea 4");
		btnOptiunea_3.setBackground(SystemColor.controlShadow);
		btnOptiunea_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOptiunea_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("");
				try{
					textArea.append("Masinile care nu au fost inchiriate:\n");
			
					Statement st = con.createStatement();
					ResultSet rs =st.executeQuery("SELECT Marca, Model " +
							"FROM Masini " + 
							"WHERE MasinaID NOT IN (SELECT M.MasinaID FROM Inchirieri I JOIN Masini M ON M.MasinaID = I.MasinaID)");
					int i = 1;
					while (rs.next()){
						textArea.append("" + i + ". ");
						textArea.append("Marca: " + rs.getString(1) + "\n");
						textArea.append("Model: " + rs.getString(2) + "\n\n");
					i++;
					}
							
				}catch(SQLException w){
		            w.printStackTrace();				
				}
			}
		});
		btnOptiunea_3.setBounds(45, 204, 116, 25);
		panel.add(btnOptiunea_3);
		
		JComboBox<String> comboBoxMarca = new JComboBox<String>();
		comboBoxMarca.setModel(new DefaultComboBoxModel<String>(new String[] {"CLA", "A4", "A5 RS", "CLS", "Mustang", "G", "RAV4", "Seria 7"}));
		comboBoxMarca.setBounds(132, 358, 116, 22);
		panel.add(comboBoxMarca);
		
		JComboBox<String> comboBoxCuloare = new JComboBox<String>();
		comboBoxCuloare.setModel(new DefaultComboBoxModel<String>(new String[] {"Negru", "Alb", "Rosu", "Gri", "Portocaliu", "Albastru"}));
		comboBoxCuloare.setBounds(132, 393, 116, 22);
		panel.add(comboBoxCuloare);
		
		JButton btnNewButton = new JButton("Optiunea 5");
		btnNewButton.setBackground(SystemColor.controlShadow);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("");
				try{
					textArea.append("Masinile care nu au mai putini km decat modelul selectat si au culoarea specificata:\n");
					String marca = (String)comboBoxMarca.getSelectedItem();
					String culoare = (String)comboBoxCuloare.getSelectedItem();;
					Statement st = con.createStatement();
					ResultSet rs =st.executeQuery("SELECT Marca, Model " +
							"FROM Masini " + 
							"where KmParcursi < (select M.KmParcursi from Masini M where Model = '" + marca + "') AND Culoare = '" + culoare + "'");
					int i = 1;
					while (rs.next()){
						textArea.append("" + i + ". ");
						textArea.append("Marca: " + rs.getString(1) + "\n");
						textArea.append("Model: " + rs.getString(2) + "\n\n");
					i++;
					}
				//	textFieldCuloare.setText("");
				//	textFieldMarca.setText("");
				}catch(SQLException w){
		            w.printStackTrace();				
				}
			}
		});
		btnNewButton.setBounds(45, 249, 116, 25);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Introduceti o marca de masina din cele existente si o culoare pentru Optiunea 5:");
		lblNewLabel.setBounds(35, 329, 626, 16);
		panel.add(lblNewLabel);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(35, 361, 56, 16);
		panel.add(lblMarca);
		
		JLabel lblCuloare = new JLabel("Culoare");
		lblCuloare.setBounds(35, 390, 56, 16);
		panel.add(lblCuloare);
		frame.setVisible(true);
	}
}
