package com.cp372.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.cp372.server.exceptions.IllegalRequestException;
import com.cp372.server.models.Shape;

final class HttpRequest implements Runnable {
	final static String CRLF = "\r\n";
	final static ShapeReader _shapeReader = new ShapeReader();
	Socket socket;

	// Constructor
	public HttpRequest(Socket socket) throws Exception {
		this.socket = socket;
	}

	// Implement the run() method of the Runnable interface.
	public void run() {
		while (true) {

			try {
				processRequest();
			} catch (Exception e) {
				//System.out.println(e);
			}
		}
	}

	private void processRequest() throws Exception {

		// Get a reference to the socket's input and output streams.
		InputStream is = socket.getInputStream();
		DataOutputStream os = new DataOutputStream(socket.getOutputStream());

		// Set up input stream filters.
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		boolean stop = false;
		
		while (!stop) {

			String requestLine = null;

			while ((requestLine = br.readLine()).length() != 0) {
				System.out.println("Client Request: " + requestLine);

				// Get some shape responses via the the parser
				Iterable<Shape> shapes = parseRequest(requestLine);

				System.out.println(shapes);

				if (requestLine.equals("STOP")) {
					stop = true;
					break;
				}

			}

		}

		// Close streams and socket.
		os.close();
		br.close();
		socket.close();
	}

	private Iterable<Shape> parseRequest(String requestLine) throws Exception {

		// Split into lines for reading, as the HTTP request should be
		String lines[] = requestLine.split("\\t");

		// If we have at least two lines in it
		if (lines.length > 0) {

			String methodLine = lines[0];
			StringTokenizer tokenizer = new StringTokenizer(methodLine);
			String verb = tokenizer.nextToken().toUpperCase();
			String aux = "";

			if (tokenizer.hasMoreTokens()) {
				aux = tokenizer.nextToken().toUpperCase();
			}

			// Grab the parameter block
			HashMap<String, String> parameters = new HashMap<String, String>();

			for (int i = 1; i < lines.length; i++) {

				String[] data = lines[i].split(":");

				// Split the data into the keys if possible
				if (data.length > 1) {
					parameters.put(data[0].toUpperCase().trim(), data[1]
							.toUpperCase().trim());
				}
			}

			if (verb.equals("GET")) {
				try {
					// Return the stuff we need
					return _shapeReader.processQuery(aux, parameters);
				} catch (Exception e) {
					throw e;
				}
			} else if (verb.equals("POST")) {
				return new ArrayList<Shape>();
			} else {
				throw new IllegalRequestException(requestLine);
			}

		} // end if

		return null;

		//
		// List<String> tokens = new ArrayList<String>();
		//
		// while (tokenizer.hasMoreTokens()) {
		// tokens.add(tokenizer.nextToken());
		// }
		//
		// if (tokens.size() > 1) {
		// String method = tokens.get(0);
		// if (method.equals("GET")) {
		// // Pass in the parameters
		// try {
		// return _shapeReader.processQuery(tokens.get(1),
		// tokens.subList(2, tokens.size() - 1));
		// } catch (Exception e) {
		// throw e;
		// }
		// } else if (method.equals("POST")) {
		// // Return some dummy data
		// return new ArrayList<Shape>();
		// } else {
		// throw new IllegalRequestException(requestLine);
		// }
		// } else {
		// throw new Exception("Not enough arguments provided.");
		// }

	}
}
