package chatApp.users.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import chatApp.users.dao.UserDAO;
import chatApp.users.dto.UserDTO;
import chatApp.users.utils.UserInfo;

public class UserScreen extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UserScreen window=new UserScreen();
	}
	UserDAO userDAO=new UserDAO();
	private void doLogin() throws NoSuchAlgorithmException, SQLException, Exception {
		
			String UserId=textField.getText();
			char[] UserPassword=passwordField.getPassword();
			UserDTO userDTO=new UserDTO(UserId,UserPassword);
			try {
				String message="";
				if(userDAO.isLogin(userDTO)) {
					UserInfo.USER_NAME=UserId;
					message="WELCOME "+UserId;
					JOptionPane.showMessageDialog(this, message);
					setVisible(false);
					dispose();
					DashBoard dashBoard=new DashBoard(message);
					dashBoard.setVisible(true);
				}
				else {
					message="INVALID USER ID OR PASSWORD";
					JOptionPane.showMessageDialog(this, message);
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
	}
	private void register() {
		String UserId=textField.getText();
		char[] UserPassword=passwordField.getPassword();
		UserDTO userDTO=new UserDTO(UserId,UserPassword);
		try {
		int result=userDAO.add(userDTO);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "REGISTER SUCCESSFULLY");
			//System.out.println("record added");
		}
		else {
			JOptionPane.showMessageDialog(this, "REGISTRATION FAILED");
			//System.out.println("RECORD NOT ADDED");
		}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB ISSUE");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("Some generic exception");
			ex.printStackTrace();
		}
		System.out.println("user:"+UserId+"PASSWORD:"+UserPassword);
	}
	/**
	 * Create the application.
	 */
	public UserScreen() {
		setTitle("LOGIN");
		getContentPane().setBackground(new Color(0, 255, 255));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(430, 132, 161, 60);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(538, 250, 357, 27);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel userID = new JLabel("User ID");
		userID.setBackground(new Color(255, 255, 255));
		userID.setHorizontalAlignment(SwingConstants.CENTER);
		userID.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 20));
		userID.setBounds(337, 242, 152, 35);
		getContentPane().add(userID);
		
		JLabel pwd = new JLabel("PASSWORD");
		pwd.setHorizontalAlignment(SwingConstants.CENTER);
		pwd.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 20));
		pwd.setBackground(Color.WHITE);
		pwd.setBounds(337, 349, 152, 35);
		getContentPane().add(pwd);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(538, 349, 357, 32);
		getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doLogin();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 20));
		btnNewButton.setBounds(307, 485, 200, 27);
		getContentPane().add(btnNewButton);
		
		JButton REGISTER = new JButton("REGISTER");
		REGISTER.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		REGISTER.setFont(new Font("Arial Black", Font.BOLD, 20));
		REGISTER.setBounds(583, 485, 200, 27);
		getContentPane().add(REGISTER);
		setBackground(new Color(255, 255, 255));
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
