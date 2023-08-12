package chatApp.users.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chatApp.users.network.Client;
import chatApp.users.utils.UserInfo;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ClientChatScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea ;
	private Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClientChatScreen c=new ClientChatScreen();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void sendIt() {
		String message=textField.getText();
		try {
			client.sendMessag(UserInfo.USER_NAME+"-"+message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ClientChatScreen() throws UnknownHostException, IOException {
		new JTextArea();
		new Client(textArea);
		setTitle("ChitChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 6, 768,313);
		contentPane.add(scrollPane);
		
		
		
//		JTextArea textArea = new JTextArea();
		textArea.setBounds(10,24, 713, 280);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(31, 343, 600, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton sendIt = new JButton("Send message");
		sendIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendIt();
			}
		});
		sendIt.setBounds(641, 346, 137, 29);
		contentPane.add(sendIt);
	
	}
}
