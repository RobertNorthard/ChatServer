import java.net.*;
import java.util.*;
import java.io.*;

/**
 *	A simple chat server, that can deal with many clients.
 *	@author RobertNorthard
 *	@version 15/12/2013
 */
public class ChatServer{

    private ServerSocket socket = null;

    private List<OutputStream> clients;

    /**
     * A constructor for ChatServer class
     * @param port port number for application to run on
     */
    public ChatServer(int port) throws IOException{
        this.socket = new ServerSocket(port);
        this.clients = new ArrayList<OutputStream>();
    }

    /**
     * Start ChatServer
     */
    public void start() throws IOException{
        System.out.println("Accepting connections on " + this.socket.getInetAddress());
        System.out.println("################## Chat Service Started #############");

        while(true){

            Socket client = this.socket.accept();
            new ChatClient(client, this.clients).start();

        }
    }
}