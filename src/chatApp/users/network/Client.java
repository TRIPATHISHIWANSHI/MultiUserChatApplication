package chatApp.users.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import chatApp.users.utils.ConfigReader;

public class Client {

	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		Integer PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket=new Socket(ConfigReader.getValue("SERVER_IP"),PORT);
		out=socket.getOutputStream();
		in=socket.getInputStream();
		this.textArea=textArea;
		readMessag();
//		System.out.println("CLIENT COMES");
//		System.out.println("ENTER THE MESSAGE SEND TO SERVER");
//		Scanner sc=new Scanner(System.in);
//		String message=sc.nextLine();
//		OutputStream out=socket.getOutputStream();
//		System.out.println("MESSAGE SEND TO SERVER");
//		out.write(message.getBytes());//it can't take string
//		out.close();
//		socket.close();
	}
	public void sendMessag(String message) throws IOException {
		message=message+"\n";
		out.write(message.getBytes());
	
	}
	
	public void readMessag() throws IOException {
		worker =new ClientWorker(in,textArea);//calling a read thread
		worker.start();
	}
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		// TODO Auto-generated method stub
//		Client client=new Client();
//
//	}

}
