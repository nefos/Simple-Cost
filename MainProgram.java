import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Toolkit.*;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.table.AbstractTableModel;
import javax.swing.JList;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class MainProgram {
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, int LoggedIn) {
		if (LoggedIn != 1) {
			System.exit(0);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainProgram window = new MainProgram();
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
	public MainProgram() {
		initialize1();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize1() {
		frame = new JFrame();
		frame.setTitle("Costing - Program");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(100, 100, 800, 441);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		try {
		    frame.setIconImage(ImageIO.read(new File("icon-16x16.gif")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
		
		//main panel
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 155, 768);
		frame.getContentPane().add(panel);
		
		//panel of menu
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(154, 0, screen.width, 45);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.hide();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(154, 0, screen.width, 45);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		//panel of printing
				JPanel panel_4 = new JPanel();
				panel_4.setBackground(Color.WHITE);
				panel_4.setBounds(154, 44, screen.width, screen.height);
				frame.getContentPane().add(panel_4);
				panel_4.setLayout(null);
		//PRINT BUTTON
		JButton btnNewButton_3 = new JButton("\u0395\u03BA\u03C4\u03CD\u03C0\u03C9\u03C3\u03B7 \u03A0\u03C1\u03BF\u03CA\u03CC\u03BD\u03C4\u03C9\u03BD");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String host1 = "jdbc:mysql://localhost:3306/products";
					String uName1 = "root";
					String uPass1 = "password";
					con = DriverManager.getConnection(host1, uName1, uPass1);
					stmt = con.createStatement();
					String SQL = String.format("SELECT * FROM INFORMATION_SCHEMA.TABLES");
					System.out.println(SQL);
					rs = stmt.executeQuery("SHOW TABLES");
					DefaultTableModel model = new DefaultTableModel();
					JTable table = new JTable(model){
						   public boolean isCellEditable(int row, int column){
						        return false;
						   }
						};
					model.addColumn("SKU-PRODUCT"); 
					model.addColumn("SKU-CUSTOMER"); 
					model.addColumn("SKU-ORDER"); 
					model.addColumn("WOOD"); 
					model.addColumn("DIMENSIONS"); 
					model.addColumn("COLOR"); 
					model.addColumn("CONSTRUCTION MANAGER");
					model.addColumn("button");
					int i = 1;
					int y = 16;
					while (rs.next()) {
						ResultSet rs1;
						System.out.println(rs.getString(1));
						stmt = con.createStatement();
						if (rs.getString(1).indexOf("costs") == -1) {
							System.out.println("OK IF");
						rs1 = stmt.executeQuery(String.format("SELECT * FROM %s", rs.getString(1)));
						rs1.next();
							model.addRow(new Object[]{rs1.getString("sku"), rs1.getString("customer"), rs1.getString("orderid"), rs1.getString("wood"), rs1.getString("dimensions"), rs1.getString("patine"), rs1.getString("client"), "ацояа"});
							table.setBounds(106, 80, 900, y + 16);
							i = i + 1;
							y = y + 16;
						}
						else {
							System.out.println(rs.getString(1).indexOf("costs"));
						}
					}
					JButton btnNewButton_9 = new JButton("New button");
					btnNewButton_9.setBounds(10, 11, 89, 23);
					table.getColumn("button").setCellRenderer(new ButtonRenderer());
					//table.addMouseListener(new JTableButtonMouseListener(table));
					table.setPreferredScrollableViewportSize(table.getPreferredSize());
					JScrollPane scroll_table;
					scroll_table = new JScrollPane(table);
					scroll_table.setBounds(106, 80, 900, 500);
				    scroll_table.setVisible(true);
				    panel_4.add(scroll_table);
					frame.validate();
				}
				catch (SQLException err) {
					System.out.println(err.getMessage());
				}
			}
		});
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(10, 11, 113, 23);
		panel_2.add(btnNewButton_3);
		panel_2.hide();
		
		//panel of activity
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(154, 44, screen.width, screen.height);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("\u039A\u03A9\u0394\u0399\u039A\u039F\u03A3 \u03A0\u03A1\u039F\u0399\u039F\u039D\u03A4\u039F\u03A3");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 200, 14);
		panel_3.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u039A\u03A9\u0394\u0399\u039A\u039F\u03A3 \u03A0\u0395\u039B\u0391\u03A4\u0397");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 36, 200, 14);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u039A\u03A9\u0394\u0399\u039A\u039F\u03A3 \u03A0\u0391\u03A1\u0391\u0393\u0393\u0395\u039B\u0399\u0391\u03A3");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 61, 200, 14);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u039E\u03A5\u039B\u039F");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 86, 200, 14);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u0394\u0399\u0391\u03A3\u03A4\u0391\u03A3\u0395\u0399\u03A3");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(10, 111, 200, 14);
		panel_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u03A7\u03A1\u03A9\u039C\u0391");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(10, 136, 200, 14);
		panel_3.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\u03A5\u03A0\u0395\u03A5\u0398\u0397\u039D\u039F\u03A3 \u039A\u0391\u03A4\u0391\u03A3\u039A\u0395\u03A5\u0397\u03A3");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(10, 161, 200, 14);
		panel_3.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBounds(220, 5, 164, 20);
		panel_3.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(220, 35, 164, 20);
		panel_3.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(220, 60, 164, 20);
		panel_3.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(220, 85, 164, 20);
		panel_3.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(220, 110, 164, 20);
		panel_3.add(textField_4);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(220, 160, 164, 20);
		panel_3.add(textField_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u039B\u039F\u03A5\u03A3\u03A4\u03A1\u039F", "\u03A7\u03A1\u03A9\u039C\u0391", "\u03A7\u03A1\u03A9\u039C\u0391 \u039A\u0391\u0399 \u039B\u039F\u03A5\u03A3\u03A4\u03A1\u039F"}));
		comboBox.setBounds(220, 134, 164, 20);
		panel_3.add(comboBox);
		panel_3.hide();
		
		
		//panel of add
		//panel of print
		
		//buttons
		//button for add product
				JButton btnNewButton_2 = new JButton("\u03A0\u03C1\u03BF\u03C3\u03B8\u03AE\u03BA\u03B7");
				btnNewButton_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							String host = "jdbc:mysql://localhost:3306/products";
							String uName = "root";
							String uPass = "loizos@123";
							con = DriverManager.getConnection(host, uName, uPass);
							stmt = con.createStatement();
							String SQL = String.format("CREATE TABLE %s_%s (sku varchar(255),customer varchar(255),orderid varchar(255),wood varchar(255),dimensions varchar(255),patine varchar(255),client varchar(255))", textField.getText(), textField_2.getText());
							System.out.println(SQL);
							stmt.executeUpdate(SQL);
							SQL = String.format("CREATE TABLE %s_%s_costs (description varchar(255),cost int)", textField.getText(), textField_2.getText());
							System.out.println(SQL);
							stmt.executeUpdate(SQL);
							SQL = String.format("INSERT INTO %s_%s VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')", textField.getText(), textField_2.getText(), textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_6.getText(), comboBox.getSelectedItem());
							System.out.println(SQL);
							stmt.executeUpdate(SQL);
							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
							textField_3.setText("");
							textField_4.setText("");
							textField_6.setText("");
						}
						catch (SQLException err) {
							System.out.println(err.getMessage());
						}
					}
				});
				btnNewButton_2.setBackground(Color.WHITE);
				btnNewButton_2.setBounds(10, 11, 113, 23);
				panel_1.add(btnNewButton_2);
		JButton btnNewButton_1 = new JButton("\u03A0\u03C1\u03BF\u03C3\u03B8\u03AE\u03BA\u03B7 \u03A0\u03C1\u03BF\u03CA\u03CC\u03BD\u03C4\u03BF\u03C2");
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				panel_1.show();
				panel_2.hide();
				panel_3.show();
				panel_4.hide();
				panel_4.removeAll();
				frame.validate();
			}
		});
		btnNewButton_1.setBackground(Color.WHITE);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("\u03A0\u03C1\u03BF\u03CA\u03CC\u03BD\u03C4\u03B1");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				panel_1.hide();
				panel_2.show();
				panel_3.hide();
				panel_4.show();
				frame.validate();
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		panel.add(btnNewButton);
		
	}
	
	class ButtonRenderer extends JButton implements TableCellRenderer {

		  public ButtonRenderer() {
		    setOpaque(true);
		  }

		  public Component getTableCellRendererComponent(JTable table, Object value,
		      boolean isPushed, boolean hasFocus, int row, int column) {
			  if (isPushed) {
			      setForeground(table.getSelectionForeground());
			      setBackground(table.getSelectionBackground());
			      int LoggedIn1 = 1;
			      Object var1 = table.getValueAt(row, 0);
			      Object var2 = table.getValueAt(row, 2);
					costs.main(null, LoggedIn1, var1, var2);
			      table.clearSelection();
			    }
			    setText((value == null) ? "" : value.toString());
			    return this;
		}
	}

	
	
	public void button() {
		System.out.println("WORKS");
	}
}
