package chatApp.users.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import chatApp.users.utils.ConfigReader;

public class Server {

		ArrayList<ServerWorker> workers=new ArrayList<>();
	ServerSocket serverSocket;
	/*
	public Server() throws IOException {
		Integer PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket=new ServerSocket(PORT);
		System.out.println("SERVER STARTED AND WAITING FOR CLIENT CONNECTION");
		Socket socket=serverSocket.accept();//HandShaking
		System.out.println("CLIENTS JOIN THE SERVER");
		InputStream in=socket.getInputStream();//read bytes from the network
		byte arr[]=in.readAllBytes();
		String str=new String(arr);//convert into string
		System.out.print("message received from client "+str);
		in.close();
		socket.close();
	}
	*/
	public Server() throws IOException {
		Integer PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket=new ServerSocket(PORT);
		handleClientRequest();
		
	}
	//multiple client handshaking
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket=serverSocket.accept();//HandShaking
			//per client per thread
			ServerWorker serverWorker=new ServerWorker( clientSocket,this);//creating a new worker(new thread)
			workers.add(serverWorker);//adding woker in a thread 
			serverWorker.start();
			}
	}
	public static void main(String[] args) throws IOException {
		

		Server server=new Server();
	}

}
