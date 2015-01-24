package com.cp372.server;

import java.io.*;
import java.net.*;
import java.util.*;

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
		try {
			processRequest();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void processRequest() throws Exception {

		// Get a reference to the socket's input and output streams.
		InputStream is = socket.getInputStream();
		DataOutputStream os = new DataOutputStream(socket.getOutputStream());

		// Set up input stream filters.
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String requestLine = null;
		while ((requestLine = br.readLine()).length() != 0) {
			System.out.println("Client Request: " + requestLine);

			// Get some shape responses via the the parser
			Iterable<Shape> shapes = parseRequest(requestLine);

		}

		// Close streams and socket.
		os.close();
		br.close();
		socket.close();
	}

	private Iterable<Shape> parseRequest(String requestLine) throws Exception {

		StringTokenizer tokenizer = new StringTokenizer(requestLine);
		List<String> tokens = new ArrayList<String>();

		while (tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken());
		}

		if (tokens.size() > 1) {
			String method = tokens.get(0);
			if (method.equals("GET")) {
				// Pass in the parameters
				try {
					return _shapeReader.processQuery(tokens.get(1),
							tokens.subList(2, tokens.size() - 1));
				} catch (Exception e) {
					throw e;
				}
			} else if (method.equals("POST")) {
				// Return some dummy data
				return new ArrayList<Shape>();
			} else {
				throw new IllegalRequestException(requestLine);
			}
		} else {
			throw new Exception("Not enough arguments provided.");
		}

	}



}
