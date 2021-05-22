import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class costs {

	JFrame frame;
	Connection con;
	Statement stmt;
	ResultSet rs;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, int LoggedIn1, Object var1, Object var2) {
		if (LoggedIn1 != 1) {
			System.exit(0);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					costs window = new costs(var1, var2);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public costs(Object var1, Object var2) {
		initialize(var1, var2);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object var1, Object var2) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame();
		frame.setTitle("LHCosting - Costs");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		try {
		    frame.setIconImage(ImageIO.read(new File("icon-16x16.gif")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, screen.width, 33);
		panel.setBackground(Color.GRAY);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("\u03A0\u03B5\u03C1\u03B9\u03B3\u03C1\u03B1\u03C6\u03AE");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u039A\u03CC\u03C3\u03C4\u03BF\u03C2");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 33, screen.width, screen.height);
		frame.getContentPane().add(panel_1);
		
		JButton btnNewButton = new JButton("\u03A0\u03A1\u039F\u03A3\u0398\u0397\u039A\u0397 \u0391\u0393\u039F\u03A1\u0391\u03A3");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String host1 = "jdbc:mysql://localhost:3306/products";
					String uName1 = "root";
					String uPass1 = "password";
					con = DriverManager.getConnection(host1, uName1, uPass1);
					stmt = con.createStatement();
					String SQL1 = String.format("INSERT INTO %s_%s_costs VALUES('%s', '%s')", var1, var2, textField.getText(), textField_1.getText());
					stmt.executeUpdate(SQL1);
					textField.setText("");
					textField_1.setText("");
					panel_1.removeAll();
					try {
						con = DriverManager.getConnection(host1, uName1, uPass1);
						stmt = con.createStatement();
						String SQL = String.format("SELECT * FROM %s_%s_costs", var1, var2);
						rs = stmt.executeQuery(SQL);
						DefaultTableModel model = new DefaultTableModel();
						JTable table = new JTable(model){
							public boolean isCellEditable(int row, int column){
						        	return false;
							}
							};
							model.addColumn("DESCRIPTION"); 
							model.addColumn("COST"); 
							int i = 1;
							int y = 16;
							while (rs.next()) {
								model.addRow(new Object[]{rs.getString("description"), rs.getString("cost")});
								table.setBounds(106, 80, 900, y + 16);
								i = i + 1;
								y = y + 16;
							}
							JScrollPane scroll_table;
							scroll_table = new JScrollPane(table);
							scroll_table.setBounds(0, 0, 434, 261);
							scroll_table.setVisible(true);
							frame.getContentPane().setLayout(null);
							panel_1.add(scroll_table);
							frame.validate();
						}
					catch (SQLException err) {
						System.out.println(err);
					}
					
					}
				catch (SQLException err) {
					System.out.println(err);
				}
			}
		});
		panel.add(btnNewButton);
		
		
		//panels
		
		try {
			String host1 = "jdbc:mysql://localhost:3306/products";
			String uName1 = "root";
			String uPass1 = "password";
			con = DriverManager.getConnection(host1, uName1, uPass1);
			stmt = con.createStatement();
			String SQL = String.format("SELECT * FROM %s_%s_costs", var1, var2);
			rs = stmt.executeQuery(SQL);
			DefaultTableModel model = new DefaultTableModel();
			JTable table = new JTable(model){
				public boolean isCellEditable(int row, int column){
			        	return false;
				}
				};
				model.addColumn("Description"); 
				model.addColumn("Cost"); 
				int i = 1;
				int y = 16;
				while (rs.next()) {
					model.addRow(new Object[]{rs.getString("description"), rs.getString("cost")});
					table.setBounds(106, 80, 900, y + 16);
					i = i + 1;
					y = y + 16;
				}
				JScrollPane scroll_table;
				scroll_table = new JScrollPane(table);
				scroll_table.setBounds(0, 0, 434, 261);
				scroll_table.setVisible(true);
				frame.getContentPane().setLayout(null);
				panel_1.add(scroll_table);
				frame.validate();
			}
		catch (SQLException err) {
			System.out.println(err);
		}
		
	}
}
