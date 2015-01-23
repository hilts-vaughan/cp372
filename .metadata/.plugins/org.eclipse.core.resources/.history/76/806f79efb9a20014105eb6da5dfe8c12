package com.cp372.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.cp372.server.models.Point;
import com.cp372.server.models.Shape;

public final class Server {
	public static void main(String argv[]) throws Exception {
		// Get the port number from the command line.
		int port = 5555;// new Integer(argv[0]).intValue();
		// System.out.println(sss.charAt(0));
		// Establish the listen socket.
		ServerSocket socket = new ServerSocket(port);

		System.out.println("Preparing to launch the web server...");
		
		// Try 4 points
		List<Point> points = new ArrayList<Point>();
		
		points.add(new Point(1, 0));
		points.add(new Point(0, 0));
		points.add(new Point(0, 1));
		points.add(new Point(1, 1));
		
		Shape s = ShapeFactory.createShape(points);
		s.printPoints();
		
		// Process HTTP service requests in an infinite loop.
		while (true) {
			// Listen for a TCP connection request.
			Socket connection = socket.accept();

			// Construct an object to process the HTTP request message.
			HttpRequest request = new HttpRequest(connection);

			// Create a new thread to process the request.
			Thread thread = new Thread(request);

			// Start the thread.
			thread.start();
		}
		
		// close socket if we ever manage to leave the loop
		// socket.close();
	}
}
