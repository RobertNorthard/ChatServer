import java.util.*;
import java.io.*;
import java.net.*;

/**
*	A chat server client.
*	@author Robert Northard
*/
public class ChatClient extends Thread{
	
	private List<OutputStream> clients = null;
	private Socket socket = null;
	
	/**
	* Constructor for class Chat Client
	* @param socket clients socket
	* @param clients list of clients to send message to
	*/
	public ChatClient(Socket socket, List<OutputStream> clients){
		
		this.socket = socket;
		this.clients = clients;
		
	}
	
	@Override
	public void run(){
		
		try{
			
			System.out.println(socket.getInetAddress().getHostName()
	          + " port " + socket.getPort() + " connected");
			this.clients.add(socket.getOutputStream());
			
			Scanner scan = new Scanner(socket.getInputStream());
			
			while(scan.hasNext()){
				
				String userInput = scan.nextLine() + "\n";
				
				for(OutputStream out: this.clients)
					out.write(userInput.getBytes());
				
			}
			
			System.out.println(socket.getInetAddress().getHostName()
		          + " port " + socket.getPort() + " disconnected");
			this.clients.remove(socket.getOutputStream());
			this.socket.close();
			
		}catch(Exception e){}
		
	}
	
}