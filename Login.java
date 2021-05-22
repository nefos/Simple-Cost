import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class Login {
	int LoggedIn;

	private JFrame frame;
	public static int r;
	private JTextField textField;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAutoRequestFocus(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Costing - Login");
		try {
		    frame.setIconImage(ImageIO.read(new File("icon-16x16.gif")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 188, 8000);
		panel.setBackground(Color.BLACK);
		frame.getContentPane().add(panel);
		JLabel lblNewLabel = new JLabel("LoizosHouse CRM V1.0");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.WHITE);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(355, 133, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(355, 164, 86, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(219, 136, 98, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(219, 167, 98, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String valueUsername = textField.getText();
				String valuePassword = passwordField.getText();
				if (valueUsername.equals("admin") && valuePassword.equals("admin")) {
					System.out.println("OK WORKS");
					LoggedIn = 1;
					MainProgram.main(null, LoggedIn);
					if (LoggedIn == 1) {
						frame.dispose();
					}
					}
				else {
					JOptionPane.showMessageDialog(null, "Wrong password or username, please try again");
				}
			}
		});
		btnNewButton.setBounds(300, 214, 89, 23);
		frame.getContentPane().add(btnNewButton);
		frame.addComponentListener((ComponentListener) new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		    Dimension y = frame.getSize();
		    r = y.height;
		    int yy = r/2;
			lblNewLabel.setBorder(new EmptyBorder(yy,0,0,0));
		    }
		});
		frame.setBounds(100, 100, 624, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

