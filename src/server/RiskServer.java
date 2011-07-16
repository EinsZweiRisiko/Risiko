package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import ui.IO;

public class RiskServer {

	/**
	 * Default port number for the server
	 */
	public final static int DEFAULTPORT = 62345;

	/**
	 * Stores the port number on which the server listens
	 */
	private int port;

	/**
	 * Stores the server socket
	 */
	private ServerSocket serverSocket;

	/**
	 * Creates a server with the default port number.
	 */
	public RiskServer() {
		port = DEFAULTPORT;
		start();
	}

	/**
	 * Creates a server which listens on a special port number.
	 * 
	 * @param port
	 */
	public RiskServer(int port) {
		this.port = port;
		start();
	}

	/**
	 * Starts the server and waits for clients to connect. Then dispatches the
	 * connected clients to their ClientRequestProcessor.
	 */
	private void start() {
		// Create server socket
		try {
			serverSocket = new ServerSocket(port);

			// Log server data
			InetAddress address = InetAddress.getLocalHost();
			IO.write("IP address: \t" + address.getHostAddress());
			IO.write("Port: \t\t" + port);
			IO.write("Server on \"" + address.getHostName() + "\" is now listening...");
		} catch (IOException e) {
			// Couldn't create server socket
			IO.writeError("There was an error while starting the server: " + e);
			System.exit(1);
		}

		// Accept client connect requests
		try {
			while (true) {
				// Wait for clients to connect
				Socket clientSocket = serverSocket.accept();

				// Create a new processor for the client
				ClientRequestProcessor clientRequestProcessor = new ClientRequestProcessor(
						clientSocket);
				// Start a thread, so that we can accept other client's connect
				// requests
				new Thread(clientRequestProcessor).start();
			}
		} catch (IOException e) {
			System.err
					.println("There was an error while waiting for connections: "
							+ e);
			System.exit(1);
		}
	}

	/**
	 * Main
	 * 
	 * @param args
	 *            Usage: [Port number]
	 */
	public static void main(String[] args) {
		if (args.length >= 1) {
			// Custom port number
			try {
				// Start a server on the supplied port number
				new RiskServer(Integer.parseInt(args[0]));
			} catch (NumberFormatException e) {
				IO.writeError("Couldn't parse the port number \"" + args[0]
						+ "\": " + e);
				System.exit(1);
			}
		}

		// No port number supplied, use the default port
		new RiskServer();
	}

}
