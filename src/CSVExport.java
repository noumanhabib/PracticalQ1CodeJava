import java.awt.Color;
import java.awt.Font;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import javax.swing.SwingConstants;

public class CSVExport {
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void exportToCSV(ArrayList<Collection> myArray) {
		
		try {
			FileWriter out = new FileWriter("book_new.csv");
		    CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("Name", "Age", "Gender", "Invesment"));
	        myArray.forEach((mycollection) -> {
	            try {
					printer.printRecord(mycollection.getName(), mycollection.getAge(), mycollection.getGender(), mycollection.getInvesment());
					
	            } catch (IOException e) {
					e.printStackTrace();
				}
	        });
	        
	        printer.close();
	        
	        JFrame frame = new JFrame("Export");
	    	frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
			frame.setSize(358, 149);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Exported to CSV");
			lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel.setBounds(48, 27, 240, 60);
			frame.getContentPane().add(lblNewLabel);
			
			frame.setResizable(false);		
			frame.setVisible(true);
	        
		   
		}
	    catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
