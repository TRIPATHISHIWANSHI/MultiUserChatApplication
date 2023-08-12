package chatApp.users.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class DashBoard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
//	 */
////	public static void main(String[] args) {
////	
//					DashBoard frame = new DashBoard();
//					frame.setVisible(true);
//		
//	//}

	/**
	 * Create the frame.
	 */
	public DashBoard(String message) {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1223, 717);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu chatMenuwMenu = new JMenu("Chat");
		menuBar.add(chatMenuwMenu);
		
		JMenuItem startChat = new JMenuItem("Start Chat");
		startChat.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			try {
				new ClientChatScreen();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		chatMenuwMenu.add(startChat);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);
		setTitle(message);
		
		JLabel lblNewLabel=new JLabel("");
		lblNewLabel.setBackground(Color.white);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(DashBoard.class.getResource("/images/chitchat2.jpg")));;
	}

}
