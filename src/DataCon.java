
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.sql.ResultSet;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
		
public class DataCon {
	static Statement stmt = null;
	static ResultSet rs = null;
	private static ArrayList<Collection> myArray = new ArrayList<Collection>();
	
	public static Connection connection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost:3306/RMS?" +
		                                   "user=root&password=noumanSERVER123*");
		    if(conn != null) {
		    	System.out.println("Connection to database created");
		    }
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		catch(Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
		return conn;
	}
	
	public static void main(String[] args) {
		
		Connection dbcon = connection();
		
		JFrame frame = new JFrame("RMS");
    	frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.setSize(700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 148, 382);
		frame.getContentPane().add(scrollPane);
		
		JList<Collection> list = new JList<Collection>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setBounds(168, 56, 302, 244);
		frame.getContentPane().add(textArea);
		
		list.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					Collection selectedContact = list.getSelectedValue();
					if(selectedContact != null) {
						textArea.setText(selectedContact.getName() + "\n" + selectedContact.getAge() + "\n" + selectedContact.getInvesment());
					}
				}
			}
	    });
		
		list.setCellRenderer(new ListCellRenderer<Collection>(){

			@Override
			public Component getListCellRendererComponent(JList<? extends Collection> list, Collection value, int index,
					boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				JLabel lblNewLabel_1 = new JLabel(value.getName());
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblNewLabel_1.setOpaque(true);
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setVerticalAlignment(SwingConstants.CENTER);
				if(isSelected) {
					lblNewLabel_1.setBackground(Color.CYAN);
				}
				
				return lblNewLabel_1;
			}
			
		});
		
		scrollPane.setViewportView(list);
		frame.getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("Import");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myArray.clear();
				try {
				    stmt = dbcon.createStatement();
				    rs = stmt.executeQuery("SELECT * FROM collections");
				    
				    while(rs.next()) {
				    	int id = rs.getInt("id");
			            String name = rs.getString("name");
			            int age = rs.getInt("age");
			            char gender = rs.getString("gender").charAt(0);
			            int invesment = rs.getInt("invesment");
			            //Saving in myArray array
			            myArray.add(new Collection(id, name, gender, age, invesment));
				    }
				    
				    list.setModel(new AbstractListModel<Collection>() {
						private static final long serialVersionUID = 1L;
						Collection[] values = new Collection[myArray.size()];
						
						public int getSize() {
							return values.length;
						}
						public Collection getElementAt(int index) {
							values[index] = myArray.get(index);
							return values[index];
						}
					});
				}
				catch (SQLException ex){
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				}
			}
		});
		btnNewButton.setBounds(168, 11, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Export");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(myArray == null || myArray.size() < 1) {
					System.out.println("No data to export");
				}
				else {
					CSVExport.exportToCSV(myArray);
				}
			}
		});
		btnNewButton_1.setBounds(287, 418, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		frame.setResizable(false);		
		frame.setVisible(true);
	}
}
