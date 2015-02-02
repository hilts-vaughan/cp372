package com.cp372.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import com.cp372.server.exceptions.BadRequestException;
import com.cp372.server.exceptions.IllegalRequestException;

final class HttpRequest implements Runnable {
	final static String CRLF = "\r\n";
	final static ShapeReader _shapeReader = new ShapeReader();
	Socket socket;

	// Constructor
	public HttpRequest(Socket socket) throws Exception {
		this.socket = socket;
	}

	public void run() {
		while (true) {

			try {
				processRequest();
			} catch (Exception e) {
				System.out.println(e);
				System.out
						.println("An internal server error occured: aborting client");

				// NOTE: Maybe do something else?

				return;
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

				Iterable<ShapeEntry> shapes;

				// Try and get some shapes; report to the user is something
				// blows up
				try {
					shapes = parseRequest(requestLine);
				} catch (BadRequestException exception) {
					String response = "400 Bad Request\tReason: "
							+ exception.getMessage() + CRLF;
					os.writeUTF(response);
					continue;
				}

				String response = "200 OK ";

				// Generate a query string for each shape
				for (ShapeEntry entry : shapes) {
					String packedString = entry.getShape().getPackedString();
					int count = entry.getCount();
					packedString += ":" + count;
					response += packedString;
					response += "&";
				}

				// Trim if required
				if (response.length() > 0)
					response = response.substring(0, response.length() - 1);

				System.out.println(response);

				// Send our response code
				os.writeUTF(response + CRLF);

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

	private Iterable<ShapeEntry> parseRequest(String requestLine)
			throws Exception {

		// Split into lines for reading, as the HTTP request should be
		String lines[] = requestLine.split("\\t");

		// If we have at least two lines in it
		if (lines.length > 0) {

			String methodLine = lines[0];
			StringTokenizer tokenizer = new StringTokenizer(methodLine);

			String verb = "";
			try {
				verb = tokenizer.nextToken().toUpperCase();
			} catch (NoSuchElementException exception) {
				throw new BadRequestException(
						"A valid verb is missing from the request.");
			}

			String aux = "";

			if (tokenizer.hasMoreTokens()) {
				aux = tokenizer.nextToken().toUpperCase();
			} else {
				throw new BadRequestException(
						"The shape qualifier is missing from the request");
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
				return new ArrayList<ShapeEntry>();
			} else {
				throw new BadRequestException(
						"The shape request is malformed. Please check your syntax.");
			}

		} // end if

		return null;

	}
}
