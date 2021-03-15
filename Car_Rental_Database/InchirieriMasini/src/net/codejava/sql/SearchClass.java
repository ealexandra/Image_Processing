package net.codejava.sql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Font;

public class SearchClass {

	private JFrame frame;
	private JTextField textOptiunea6;



	/**
	 * Create the application.
	 */
	public SearchClass(Connection con) {
		initialize(con);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 658, 452);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 13, 414, 278);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnOpriunea1 = new JButton("Optiunea 1");
		btnOpriunea1.setBackground(SystemColor.controlShadow);
		btnOpriunea1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOpriunea1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("");
				try{
					textArea.append("Top 3 clienti care au inchiriat cele mai multe masini:\n");
					
					Statement st = con.createStatement();
					ResultSet rs =st.executeQuery("Select top 3 C.Nume, C.Prenume, count(I.ClientID) as NrInchirieri " +
							"From Clienti C inner join Inchirieri I on C.ClientID = I.ClientID " + 
							"GROUP BY C.Nume, C.Prenume " +
							"Order by count(I.ClientID) desc");
					int i = 1;
					while (rs.next()){
						textArea.append("\n" + i + ". ");
						textArea.append("Nume: " + rs.getString(1) + "\n");
						textArea.append("Prenume: " + rs.getString(2) + "\n");
						textArea.append("Numar inchirieri: " + rs.getInt(3) + "\n\n");
						i++;
					}
							
				}catch(SQLException w){
		            w.printStackTrace();				
				}
				
			}
		});
		btnOpriunea1.setBounds(48, 23, 116, 25);
		panel.add(btnOpriunea1);
		
		JButton btnOptiunea = new JButton("Optiunea 2");
		btnOptiunea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOptiunea.setBackground(SystemColor.controlShadow);
		btnOptiunea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("");
				try{
					textArea.append("Masina cu numarul cel mai mare de inchirieri:\n");
					
					Statement st = con.createStatement();
					ResultSet rs =st.executeQuery("Select top 1 M.Marca, M.Model, M.MasinaID, count(I.InchiriereID) as NrInchirieri " +
							"from Masini M inner join Inchirieri I on M.MasinaID =I.MasinaID " +
							"Group by M.Marca, M.Model, M.MasinaID " + 
							"Order by count(I.InchiriereID) desc");
					while (rs.next()){
						textArea.append("\nMarca: " + rs.getString(1) + "\n");
						textArea.append("Model: " + rs.getString(2) + "\n");
						textArea.append("Numar inchirieri: " + rs.getInt(3) + "\n\n");
					}
							
				}catch(SQLException w){
		            w.printStackTrace();				
				}
			}
		});
		btnOptiunea.setBounds(48, 61, 116, 25);
		panel.add(btnOptiunea);
		
		JButton btnOptiunea_1 = new JButton("Optiunea 3");
		btnOptiunea_1.setBackground(SystemColor.controlShadow);
		btnOptiunea_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOptiunea_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("");
				try{
					textArea.append("Masinile care au fost avariate:\n");
					
					Statement st = con.createStatement();
					ResultSet rs =st.executeQuery("select  M.Marca, M.Model " +
							"from Masini M inner join Inchirieri I  on I.MasinaID = M.MasinaID inner join Defectiuni D on D.InchiriereID = I.InchiriereID " +
							 "group by M.Marca, M.Model, M.MasinaID");
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
		btnOptiunea_1.setBounds(48, 99, 116, 25);
		panel.add(btnOptiunea_1);
		
		JButton btnOptiunea_2 = new JButton("Optiunea 4");
		btnOptiunea_2.setBackground(SystemColor.controlShadow);
		btnOptiunea_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOptiunea_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("");
				try{
					textArea.append("Angajatii care au inchiriat masinile marca Audi:\n");
					
					Statement st = con.createStatement();
					ResultSet rs =st.executeQuery("select distinct A.Nume, A.Prenume " +
							"from Angajati A inner join Inchirieri I on A.AngajatID = I.AngajatID inner join Masini M on M.MasinaID = I.MasinaID " +
							 "where M.Marca = 'Audi'");
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
		btnOptiunea_2.setBounds(48, 137, 116, 25);
		panel.add(btnOptiunea_2);
		
		JButton btnOptiunea_3 = new JButton("Optiunea 5");
		btnOptiunea_3.setBackground(SystemColor.controlShadow);
		btnOptiunea_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOptiunea_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("");
				try{
					textArea.append("Masinile inchiriate in anul 2019:\n");
					
					Statement st = con.createStatement();
					ResultSet rs =st.executeQuery("select distinct M.Marca, M.Model " +
							"from Masini M inner join Inchirieri I on M.MasinaID = I.MasinaID " +
							"where year(I.DataInchirierii) = '2019'");
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
		btnOptiunea_3.setBounds(48, 175, 116, 25);
		panel.add(btnOptiunea_3);
		
		JButton btnClienti = new JButton("Clienti");
		btnClienti.setBackground(SystemColor.controlShadow);
		btnClienti.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnClienti.addActionListener(new ActionListener() {
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
		btnClienti.setBounds(256, 304, 97, 25);
		panel.add(btnClienti);
		
		JButton btnMasini = new JButton("Masini");
		btnMasini.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnMasini.setBackground(SystemColor.controlShadow);
		btnMasini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		        } catch (SQLException w){
		            w.printStackTrace();
		        }
			}
		});
		btnMasini.setBounds(256, 367, 97, 25);
		panel.add(btnMasini);
		
		JButton btnAngajati = new JButton("Angajati");
		btnAngajati.setBackground(SystemColor.controlShadow);
		btnAngajati.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnAngajati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("");
		        try {
		            Statement st = con.createStatement();
		            ResultSet rs =st.executeQuery("select * from Angajati ");
		            while (rs.next()){
		            	textArea.append("******************\n");
		            	textArea.append("Id: " + rs.getInt(1) + "\n");         
		            	textArea.append("Nume: " + rs.getString(2) + "\n");
		            	textArea.append("Prenume: " + rs.getString(3) + "\n");
		            	textArea.append("CNP: " + rs.getString(4) + "\n");
		            	textArea.append("Salariu: " + rs.getInt(5) + "\n");
		            	textArea.append("Data angajarii: " + rs.getDate(6) + "\n");
		            	textArea.append("Data nasterii: " + rs.getDate(7) + "\n");
		            	textArea.append("******************\n");
		            }
		        } catch (SQLException w){
		            w.printStackTrace();
		        }
			}
		});
		btnAngajati.setBounds(392, 304, 97, 25);
		panel.add(btnAngajati);
		
		JButton btnInchirieri = new JButton("Inchirieri");
		btnInchirieri.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnInchirieri.setBackground(SystemColor.controlShadow);
		btnInchirieri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("");
		        try {
		            Statement st = con.createStatement();
		            ResultSet rs =st.executeQuery("select * from Inchirieri ");
		            while (rs.next()){
		            	textArea.append("******************\n");
		            	textArea.append("Id: " + rs.getInt(1) + "\n");         
		            	textArea.append("Data inchirierii: " + rs.getDate(5) + "\n");
		            	textArea.append("Durata: " + rs.getInt(6) + "\n");
		            	textArea.append("Restituire: " + rs.getString(7) + "\n");
		            	textArea.append("******************\n");
		            }
		        } catch (SQLException w){
		            w.printStackTrace();
		        }
			}
		});
		btnInchirieri.setBounds(392, 367, 97, 25);
		panel.add(btnInchirieri);
		
		JButton btnDefectiuni = new JButton("Defectiuni");
		btnDefectiuni.setBackground(SystemColor.controlShadow);
		btnDefectiuni.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnDefectiuni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("");
		        try {
		            Statement st = con.createStatement();
		            ResultSet rs =st.executeQuery("select * from Defectiuni ");
		            while (rs.next()){
		            	textArea.append("******************\n");
		            	textArea.append("Id: " + rs.getInt(1) + "\n");         
		            	textArea.append("Cod: " + rs.getString(4) + "\n");
		            	textArea.append("Data: " + rs.getDate(5) + "\n");
		            	textArea.append("Suma: " + rs.getInt(6) + "\n");
		            	textArea.append("Grad avarie: " + rs.getInt(7) + "\n");
		            	textArea.append("Descriere: " + rs.getString(8) + "\n");
		            	textArea.append("******************\n");
		            }
		        } catch (SQLException w){
		            w.printStackTrace();
		        }
			}
		});
		btnDefectiuni.setBounds(524, 304, 97, 25);
		panel.add(btnDefectiuni);
		
		JButton btnService = new JButton("Service");
		btnService.setBackground(SystemColor.controlShadow);
		btnService.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("");
		        try {
		            Statement st = con.createStatement();
		            ResultSet rs =st.executeQuery("select * from Service ");
		            while (rs.next()){
		            	textArea.append("******************\n");
		            	textArea.append("Id: " + rs.getInt(1) + "\n");         
		            	textArea.append("Nume: " + rs.getString(2) + "\n");
		            	textArea.append("Oras: " + rs.getString(3) + "\n");
		            	textArea.append("Strada: " + rs.getString(4) + "\n");
		            	textArea.append("******************\n");
		            }
		        } catch (SQLException w){
		            w.printStackTrace();
		        }
			}
		});
		btnService.setBounds(524, 367, 97, 25);
		panel.add(btnService);
		
		JButton btnOptiunea_4 = new JButton("Optiunea 6");
		btnOptiunea_4.setBackground(SystemColor.controlShadow);
		btnOptiunea_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnOptiunea_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					textArea.append("Clientii care au inchiriat masini pe o durata mai mare decat cea specificata:\n");
					String durataOre = textOptiunea6.getText();
					int nrOreInchiriere = Integer.parseInt(durataOre);
					Statement st = con.createStatement();
					ResultSet rs =st.executeQuery("select distinct A.Nume, A.Prenume " +
							"FROM Clienti A inner join Inchirieri I on (A.ClientID = I.ClientID)" +
							"WHERE I.DurataOre >" + nrOreInchiriere +
							"ORDER BY A.Nume, A.Prenume");
					int i = 1;
					while (rs.next()){
						textArea.append("" + i + ". ");
						textArea.append("Nume: " + rs.getString(1) + "\n");
						textArea.append("Prenume: " + rs.getString(2) + "\n\n");
					i++;
					}
					if (i == 1)
						textArea.append("Nu s-a gasit nici un client\n");
					textOptiunea6.setText("");		
				}catch(SQLException w){
		            w.printStackTrace();				
				}
			}
		});
		btnOptiunea_4.setBounds(48, 213, 116, 25);
		panel.add(btnOptiunea_4);
		
		textOptiunea6 = new JTextField();
		textOptiunea6.setBounds(48, 348, 116, 22);
		panel.add(textOptiunea6);
		textOptiunea6.setColumns(10);
		
		JTextArea txtrPentruInterogareaNr = new JTextArea();
		txtrPentruInterogareaNr.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtrPentruInterogareaNr.setBackground(SystemColor.control);
		txtrPentruInterogareaNr.setText("Interogarea nr 6 va returna\r\ntoti clientii care au inchiriat \r\no masina pentru cel putin\r\nnr specificat de ore:");
		txtrPentruInterogareaNr.setBounds(12, 261, 203, 90);
		panel.add(txtrPentruInterogareaNr);
		frame.setVisible(true);
	}
}
