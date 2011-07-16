package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import ui.IO;

public class ClientRequestProcessor implements Runnable {

	private Socket socket;
	private BufferedReader in;
	private PrintStream out;
	
	public ClientRequestProcessor(Socket socket) {
		this.socket = socket;

		// Initialize I/O streams
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
			
			// Log the successful connection
			IO.write("Connected to " + socket.getInetAddress() + ":" + socket.getPort());
		} catch (IOException e) {
			IO.writeError("Error while retrieving the streams: " + e);
			try {
				socket.close();
			} catch (IOException e1) {
				IO.writeError("Error while closing the connection: " + e1);
			}
		}
	}
	
	public void run() {
		
	}
	
}
