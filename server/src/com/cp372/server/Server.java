package com.cp372.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cp372.server.models.Point;
import com.cp372.server.models.Shape;

public final class Server {
	public static void main(String argv[]) throws Exception {
		// Get the port number from the command line.
		int port = 5555;// new Integer(argv[0]).intValue();
		// System.out.println(sss.charAt(0));
		// Establish the listen socket.
//		ServerSocket socket = new ServerSocket(port);

		System.out.println("Preparing to launch the web server...");
	
		// Try 4 points
		List<Point> points = new ArrayList<Point>();
		
		while (true){
			Scanner scan = new Scanner(System.in);
			float a;
			float b;
			System.out.println("Point 1 x");
			a = scan.nextFloat();
			System.out.println("point 1 y");
			b = scan.nextFloat();
			points.add(new Point(a, b));
			System.out.println("point 2 x");
			a = scan.nextFloat();
			System.out.println("point 2 y");
			b = scan.nextFloat();
			points.add(new Point(a, b));
			System.out.println("point 3 x");
			a = scan.nextFloat();
			System.out.println("point 3 y");
			b = scan.nextFloat();
			points.add(new Point(a, b));
			System.out.println("point 4 x");
			a = scan.nextFloat();
			System.out.println("point 4 y");
			b = scan.nextFloat();
			points.add(new Point(a, b));		
			System.out.println("next");
			Shape s = ShapeFactory.createShape(points);
			s.printPoints();
			points.clear();
		}
		// Process HTTP service requests in an infinite loop.
	/*	while (true) {
			// Listen for a TCP connection request.
			Socket connection = socket.accept();

			// Construct an object to process the HTTP request message.
			HttpRequest request = new HttpRequest(connection);

			// Create a new thread to process the request.
			Thread thread = new Thread(request);

			// Start the thread.
			thread.start();
		}
		*/
		// close socket if we ever manage to leave the loop
		// socket.close();
	}
}
