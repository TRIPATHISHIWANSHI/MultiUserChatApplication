package chatApp.users.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

//Thread is worker
//thread need a job to perform
//for job give runnable=>runnable is an interface
//oncer job is created via runnable so write the job logic inside a run function
//assign the job to the thread
public class ServerWorker extends Thread {
//per thread a stackis created
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket,Server server) throws IOException {
		this.clientSocket=clientSocket;
		in=clientSocket.getInputStream();
		out=clientSocket.getOutputStream();
		this.server=server;
	}
	public void run() {
		//job to perform
		//logic
		//read data from client and broadcast to all
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String line;
		try {
		while(true) {//data come continuously
			
				line=br.readLine();
				if(line.equalsIgnoreCase("quit")) {
					break;//client chat end
				}
				//out.write(line.getBytes());
				//broadcast to all 
				for(ServerWorker serverWorker :server.workers) {
					line=line+"\n";
					serverWorker.out.write(line.getBytes());
				}
			}} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		finally {
			try {
			if(br!=null) {
				br.close();
			}
			if(in!=null) {
				in.close();
			}if(out!=null) {
				out.close();	
				}
			if(clientSocket!=null) {
				clientSocket.close();
			}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
//	public static void main(String[] args) {
//	
//		ServerWorker job=new ServerWorker();
//		//assign job to thread
//		Thread worker=new Thread(job);
//		worker.start();//internally it call run()
//
//	}
	
}
