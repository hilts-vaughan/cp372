package com.cp372.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.cp372.server.models.Point;
import com.cp372.server.models.Shape;

public final class Server {
	@SuppressWarnings("resource")
	public static void main(String argv[]) throws Exception {
		// Get the port number from the command line.

		int port = 0;
		
		try {
			port = Integer.parseInt(argv[0]);
		} 
		
		catch(Exception exception) {
			System.out.println("Invalid usage. Proper usage is: server <portNumber>");
			return;
		}
		
		// System.out.println(sss.charAt(0));
		// Establish the listen socket.
		System.out.println("Preparing to launch the web server...");

		ServerSocket socket;
		
		try {
			socket = new ServerSocket(port);
		}
		catch(Exception exception) {
			System.out.println("Server failed to bind on port " + port + ". Is it in use?");
			exception.printStackTrace();
			return;
		}
		
		System.out.println("Socket is now bound at localhost:" + port);

		// Try 4 points

		List<Point> points = new ArrayList<Point>();

		List<Point> points2 = new ArrayList<Point>();

		points.add(new Point(52, 7));
		points.add(new Point(34, 6));
		points.add(new Point(2, 1));
		points.add(new Point(3, 4));

		points2.add(new Point(52, 7));
		points2.add(new Point(2, 1));
		points2.add(new Point(34, 6));
		points2.add(new Point(3, 4));

		Shape s = null;
		s = ShapeFactory.createShape(points);
		// s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);

		points.clear();
		points.add(new Point(1, 2));
		points.add(new Point(2, 3));
		points.add(new Point(3, 2));
		points.add(new Point(1, 1));
		s = ShapeFactory.createShape(points);
		// s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);
		/*
		 * points.clear(); points.add(new Point(4, 2)); points.add(new Point(2,
		 * 3)); points.add(new Point(2, 2)); points.add(new Point(1, 1));
		 * 
		 * s = ShapeFactory.createShape(points); //s.printPoints();
		 * ShapeStorage.getInstance().insertOrUpdateShape(s);
		 */
		points.clear();
		points.add(new Point(6, 2));
		points.add(new Point(2, 3));
		points.add(new Point(3, 3));
		points.add(new Point(1, 2));
		s = ShapeFactory.createShape(points);
		// s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);

		points.clear();
		points.add(new Point(0, 4));
		points.add(new Point(0, 0));
		points.add(new Point(4, 0));
		s = ShapeFactory.createShape(points);
		s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);

		points.clear();
		points.add(new Point(4, 4));
		points.add(new Point(0, 0));
		points.add(new Point(4, 0));
		s = ShapeFactory.createShape(points);
		ShapeStorage.getInstance().insertOrUpdateShape(s);

		s.printPoints();
		points.clear();
		points.add(new Point(3, 2));
		points.add(new Point(0, 1));
		points.add(new Point(4, 3));
		s = ShapeFactory.createShape(points);
		s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);

		points.clear();
		points.add(new Point(0, 1));
		points.add(new Point(0, 0));
		points.add(new Point(1, 0));
		s = ShapeFactory.createShape(points);
		s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);

		points.clear();
		points.add(new Point(0, 0));
		points.add(new Point(6, 0));
		points.add(new Point(3, 5));
		s = ShapeFactory.createShape(points);
		s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);

		points.clear();
		points.add(new Point(0, 0));
		points.add(new Point(6, 9));
		points.add(new Point(12, 0));
		s = ShapeFactory.createShape(points);
		s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);

		/*
		 * points.clear(); points.add(new Point(2, 2)); points.add(new Point(2,
		 * 3)); points.add(new Point(9, 2)); points.add(new Point(1, 1)); s =
		 * ShapeFactory.createShape(points); //s.printPoints();
		 * ShapeStorage.getInstance().insertOrUpdateShape(s);
		 */

		// ShapeStorage.getInstance().insertOrUpdateShape(s);
		// ShapeStorage.getInstance().insertOrUpdateShape(s);

		points.clear();
		points.add(new Point(0, 0));
		points.add(new Point(0, 3));
		points.add(new Point(2, 5));
		points.add(new Point(4, 5));
		s = ShapeFactory.createShape(points);
		ShapeStorage.getInstance().insertOrUpdateShape(s);
		s.printPoints();

		points.clear();
		points.add(new Point(0, 0));
		points.add(new Point(1, 1));
		points.add(new Point(0, 1));
		points.add(new Point(1, 0));
		s = ShapeFactory.createShape(points);
		s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);
		points.clear();
		points.add(new Point(0, 0));
		points.add(new Point(2, 3));
		points.add(new Point(5, 3));
		points.add(new Point(7, 0));

		s = ShapeFactory.createShape(points);
		s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);

		points.clear();
		points.add(new Point(0, 0));
		points.add(new Point(0, 2));
		points.add(new Point(1, 2));
		points.add(new Point(1, 0));
		s = ShapeFactory.createShape(points);
		s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);

		points.clear();
		points.add(new Point(0, 0));
		points.add(new Point(4, 4));
		points.add(new Point(0, 4));
		points.add(new Point(4, 0));
		s = ShapeFactory.createShape(points);
		s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);

		points.clear();
		points.add(new Point(0, 0));
		points.add(new Point(3, 4));
		points.add(new Point(8, 4));
		points.add(new Point(5, 0));
		s = ShapeFactory.createShape(points);
		s.printPoints();
		ShapeStorage.getInstance().insertOrUpdateShape(s);
		ShapeStorage.getInstance().insertOrUpdateShape(s);

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
