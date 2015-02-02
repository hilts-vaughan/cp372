package com.cp372.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



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
				} catch (SocketException socketException) {
					System.out
							.println("A client has forcefully terminated their connection. Closing thread.");
					return;
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

					if (shapes != null) {

						// Generate a query string for each shape
						for (ShapeEntry entry : shapes) {
							String packedString = entry.getShape()
									.getPackedString();
							int count = entry.getCount();
							packedString += ":" + count;
							response += packedString;
							response += "&";
						}

						// Trim if required
						if (response.length() > 0)
							response = response.substring(0, response.length() - 1);
					} else {
						// It was a POST
						response += "\tType: " + this._t;
					}

					System.out.println("Sending response to client: " + response);

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

		private String _t = "";

		private Iterable<ShapeEntry> parseRequest(String requestLine)
				throws BadRequestException {

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
							"The shape qualifier or post data is missing from the request");
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
					
					if (tokenizer.hasMoreTokens()) {
						throw new BadRequestException(
								"Data followed by the shape qualifier that is not a TAB character is illegal.");
					}
					
					try {
						// Return the stuff we need
						return _shapeReader.processQuery(aux, parameters);

					} catch (BadRequestException e) {
						throw e;
					} catch (Exception e) {
						throw new BadRequestException(
								"The request failed for an unknown reason.");
					}

				} else if (verb.equals("POST")) {

					List<Point> points;

					try {
						int startIndex = requestLine.indexOf(aux);
						String buffer = requestLine.substring(startIndex).trim();

						// Generate our points by using some string logic... ouch
						points = new ArrayList<Point>();

						String[] pointPieces = buffer.split(" ");

						// Convert pieces here
						for (int i = 0; i < pointPieces.length; i += 2) {

							String pointPiece = pointPieces[i].trim();
							String pointPiece2 = pointPieces[i + 1].trim();

							int x = Integer.parseInt(pointPiece.trim());
							int y = Integer.parseInt(pointPiece2.trim());

							points.add(new Point(x, y));
						}

					} catch (Exception exception) {
						throw new BadRequestException(
								"The vertices given could not be understood. Make sure there's an even amount and only single spaces seperating them.");
					}

					// With our points, generate our shape and insert it
					try {
						Shape s = ShapeFactory.createShape(points);
						ShapeStorage.getInstance().insertOrUpdateShape(s);
						System.out
								.println("A shape has been succesfully posted to the server.");
					}

					catch (BadRequestException exception) {
						throw exception;
					}

					catch (Exception exception) {
						throw new BadRequestException(
								"Too few vertices were provided for the POST. 3 or 4 are required. Duplicate points are removed.");
					}

					this._t = points.size() == 3 ? "T" : "Q";
					return null;
				} else {
					throw new BadRequestException(
							"The shape request is malformed. Please check your syntax.");
				}

			} // end if

			return null;

		}
	}
	public class ShapeEntry {

		private final Shape _shape;
		private int _count;

		public ShapeEntry(Shape _shape) {
			super();
			this._shape = _shape;
			this._count = 1;
		}

		public int getCount() {
			return _count;
		}

		public void incrementCount() {
			this._count++;
		}

		public Shape getShape() {
			return _shape;
		}

	}
	public static class ShapeFactory {

		/**
		 * Given a list of points, generates the according shape required.
		 * 
		 * @param points
		 * @throws InvalidVerticesException
		 * @throws BadRequestException
		 */
		public static Shape createShape(List<Point> points)
				throws InvalidVerticesException, BadRequestException {

			// Remove duplicates silently before putting into the factory
			points = getPointsWithDuplicatesRemoved(points);

			int pointCountBeforeJarvis = points.size();
			List<Point> jarvisPoints;

			try {
				jarvisPoints = getSortedClockwiseVertices(points);
			} catch (Exception exception) {
				throw new BadRequestException(
						"The point winding order could not be determined. Are your points co-linear?");
			}

			int pointCountAfterJarvis = jarvisPoints.size();
			boolean concave = false;

			if (pointCountBeforeJarvis != pointCountAfterJarvis) {

				// If we ended up being concave,
				jarvisPoints = points;
				concave = true;
			}

			if (points.size() == 3) {
				return new Triangle(jarvisPoints);
			} else if (points.size() == 4) {

				// If we ended up here, Jarvis failed us and we're probavly concave.
				Quadrilateral q = new Quadrilateral(jarvisPoints, concave);
				return q;
			} else {
				throw new InvalidVerticesException(points);
			}
		}

		/**
		 * Using a set, converts all
		 * 
		 * @param points
		 * @return
		 */
		private List<Point> getPointsWithDuplicatesRemoved(List<Point> points) {
			return new ArrayList<Point>(new HashSet<Point>(points));
		}

		/**
		 * Given a set of points, sorts them in clockwise order using the Jarvis
		 * algorithim.
		 * 
		 * @param vertices
		 *            The set of points to be sorted
		 * @return
		 */
		private static List<Point> getSortedClockwiseVertices(List<Point> vertices) {

			ArrayList<Point> hull = new ArrayList<Point>();

			Point vPointOnHull = vertices.get(0);

			// Get leftmost
			for (int i = 0; i < vertices.size(); i++) {
				Point x = vertices.get(i);

				if (x.getX() < vPointOnHull.getX()) {
					vPointOnHull = x;
				}

				else if (x.getX() == vPointOnHull.getX()) {
					if (x.getY() < vPointOnHull.getY()) {
						vPointOnHull = x;
					}
				}

			}

			Point vEndpoint;

			do {

				hull.add(vPointOnHull);
				vEndpoint = vertices.get(0);

				for (int i = 1; i < vertices.size(); i++) {

					if ((vPointOnHull == vEndpoint)
							|| (getOrientation(vPointOnHull, vEndpoint,
									vertices.get(i)) == -1)) {
						vEndpoint = vertices.get(i);
					}
				}

				vPointOnHull = vEndpoint;

			} while (vEndpoint != hull.get(0));

			return hull;
		}

		private static int getOrientation(Point p1, Point p2, Point p) {
			// Determinant
			int orientation = ((p2.getX() - p1.getX()) * (p.getY() - p1.getY()) - (p
					.getX() - p1.getX()) * (p2.getY() - p1.getY()));

			if (orientation > 0)
				return -1;
			if (orientation < 0)
				return 1;

			return 0;
		}

	}
	public class ShapeReader {

		private PolygonFilter _polygonFilter = new PolygonFilter();
		private OccurenceFilter _occurenceFilter = new OccurenceFilter();
		private PropertyFilter _propertyFilter = new PropertyFilter();
		private PointFilter _pointFilter = new PointFilter();

		private final static String HEADER_OCCURENCES = "OCCURRENCES";
		private final static String HEADER_POINT = "SHARES";
		private final static String HEADER_TYPE = "TYPE";

		public ShapeReader() {

		}

		public Iterable<ShapeEntry> processQuery(String type,
				Map<String, String> params) throws Exception {

			// Do an initial filter by type. Next, we simply filter based on the
			// decided attributes from the headers
			Iterable<ShapeEntry> results = _polygonFilter.filter(ShapeStorage
					.getInstance().getShapes(), type);

			for (Map.Entry<String, String> entry : params.entrySet()) {
				switch (entry.getKey().toUpperCase()) {

				case HEADER_OCCURENCES:

					try {
						int occurences = Integer.parseInt(entry.getValue());

						if (occurences < 0) {
							throw new BadRequestException(
									"A negative integer was passed to the OCCURRENCES header.");
						}

						results = _occurenceFilter.filter(results, occurences);
					} catch (BadRequestException exception) {
						throw exception;
					} catch (Exception exception) {
						throw new BadRequestException(
								"An integer was not passed to the OCCURRENCES header.");
					}

					break;
				case HEADER_POINT:

					try {

						// Generate our points by using some string logic... ouch
						List<Point> points = new ArrayList<Point>();

						String[] pointPieces = entry.getValue().split(" ");

						// Convert pieces here
						for (int i = 0; i < pointPieces.length; i += 2) {

							String pointPiece = pointPieces[i].trim();
							String pointPiece2 = pointPieces[i + 1].trim();

							int x = Integer.parseInt(pointPiece.trim());
							int y = Integer.parseInt(pointPiece2.trim());

							points.add(new Point(x, y));
						}

						results = _pointFilter.filter(results, points);
					} catch (Exception exception) {
						throw new BadRequestException(
								"The points provided to the SHARES header is malformed or incorrect.");
					}

					break;
				case HEADER_TYPE:
					try {

						List<String> properties = new ArrayList<String>();

						String[] pieces = entry.getValue().split(" ");
						for (String piece : pieces) {
							properties.add(piece);
						}

						results = _propertyFilter.filter(results, properties);
					} catch (Exception exception) {
						throw new BadRequestException(
								"The properties provided to the TYPE header are not valid.");
					}
					break;
				default: // ignore attributes that aren't implemented; they could be
							// supported as extensions
					break;
				}
			}

			return results;
		}
	}public class ShapeStorage {
		private static ShapeStorage instance = null;

		protected ShapeStorage() {
		}

		public static ShapeStorage getInstance() {
			if (instance == null) {
				instance = new ShapeStorage();
			}
			return instance;
		}

		// Non-singelton specific implementation stuff
		private List<ShapeEntry> _shapes = new ArrayList<ShapeEntry>();

		public Iterable<ShapeEntry> getShapes() {
			return _shapes;
		}

		/**
		 * Given a shape and a number of occurences, finds a shape that has occured
		 * that many times exactly.
		 * 
		 * @param shape
		 *            The shape to search for
		 * @param occurences
		 *            The number of occurences to look for
		 * @return An iterable list of shapes that meet the criteria
		 */
		public Iterable<Shape> getShapeByOccurence(Shape shape, int occurences) {
			List<Shape> occuredShapes = new ArrayList<Shape>();

			for (ShapeEntry entry : _shapes) {
				if (entry.getCount() == occurences)
					occuredShapes.add(entry.getShape());
			}

			return occuredShapes;
		}

		/**
		 * Performs an insertion of a shape if cannot be found -- otherwise,
		 * increment the counter
		 * 
		 * @param shape
		 */
		public synchronized void insertOrUpdateShape(Shape shape) {

			for (ShapeEntry entry : _shapes) {
				if (entry.getShape().equals(shape)) {
					entry.incrementCount();
					System.out.println("A shape already existed; incrementing counter.");
					return;
				}
			}

			System.out.println("A new shape was detected. Inserting into storage.");
			
			// Otherwise, we couldn't find it so we'll insert
			_shapes.add(new ShapeEntry(shape));
		}

	}public class BadRequestException extends Exception {

		/**
		 * Generated serial ID by the IDE
		 */
		private static final long serialVersionUID = -332750519693488002L;

		public BadRequestException(String message) {
			super(message);
		}

	}
	/**
	 * A general exception that is thrown when an amount of vertices that is sent
	 * isn't a valid one. This is generally a misbehaving client or unknown
	 * behaviour.
	 * 
	 * @author Vaughan Hilts
	 *
	 */
	public class InvalidVerticesException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public InvalidVerticesException(List<Point> vertices) {

		}

	}
	/**
	 * Filters by a specific amount of occurences that have occured
	 * 
	 * @author Vaughan Hilts
	 *
	 */
	public class OccurenceFilter implements ShapeFilter {

		@Override
		public Iterable<ShapeEntry> filter(Iterable<ShapeEntry> shapes,
				Object context) {

			int occurencesRequired = (Integer) context;

			List<ShapeEntry> results = new ArrayList<ShapeEntry>();

			for (ShapeEntry entry : shapes) {
				if (entry.getCount() >= occurencesRequired
						|| occurencesRequired == 0)
					results.add(entry);
			}

			return results;
		}

	}/**
	 * Filters to shapes that only have shared points
	 * @author Vaughan Hilts
	 *
	 */
	public class PointFilter implements ShapeFilter {

		@Override
		public Iterable<ShapeEntry> filter(Iterable<ShapeEntry> shapes,
				Object context) {

			List<ShapeEntry> results = new ArrayList<ShapeEntry>();

			@SuppressWarnings("unchecked")
			List<Point> shares = (ArrayList<Point>) context;

			// Filter out those that don't share points
			for (ShapeEntry entry : shapes) {
				if (entry.getShape().hasPoints(shares)) {
					results.add(entry);
				}
			}

			return results;
		}

	}
	/**
	 * Filters by a given instance type given a string for the type. Allows
	 * filtering by specific groups of shapes.
	 * 
	 * @author Vaughan Hilts
	 *
	 */
	public class PolygonFilter implements ShapeFilter {

		private final static String SHAPE_TYPE_TRIANGLE = "T";
		private final static String SHAPE_TYPE_QUAD = "Q";
		private final static String SHAPE_TYPE_BOTH = "B";

		@Override
		public Iterable<ShapeEntry> filter(Iterable<ShapeEntry> shapes,
				Object context) throws BadRequestException {

			String type = (String) context;
			List<ShapeEntry> results = new ArrayList<ShapeEntry>();

			for (ShapeEntry entry : shapes) {
				switch (type) {
				case SHAPE_TYPE_TRIANGLE:
					if (entry.getShape() instanceof Triangle)
						results.add(entry);
					break;
				case SHAPE_TYPE_QUAD:
					if (entry.getShape() instanceof Quadrilateral)
						results.add(entry);
					break;
				case SHAPE_TYPE_BOTH: // otherwise, it's both so allow that
					results.add(entry);
					break;
				default:
					throw new BadRequestException(
							"The provided shape qualifier is illegal");
				}
			}

			return results;
		}

	}
	/**
	 * Filters on enumeration types against the different shapes
	 * 
	 * @author Vaughan Hilts
	 *
	 */
	public class PropertyFilter implements ShapeFilter {

		@Override
		public Iterable<ShapeEntry> filter(Iterable<ShapeEntry> shapes,
				Object context) {
			// TODO: Apply some filtering logic

			List<ShapeEntry> results = new ArrayList<ShapeEntry>();

			@SuppressWarnings("unchecked")
			List<String> shares = (ArrayList<String>) context;

			// Filter out those that don't chare points
			for (ShapeEntry entry : shapes) {
				String p = entry.getShape().getPackedProperties();
				String[] properties = p.split(",");

				// Grab a list so we can use containsAll
				List<String> listProperties = new ArrayList<String>();
				for (String s : properties) {
					listProperties.add(s);
				}

				if (listProperties.containsAll(shares)) {
					results.add(entry);
				}
			}

			return results;

		}

	}
	/**
	 * Provides a generic interface for filters that must be implemented against
	 * different shapes.
	 * 
	 * @author Vaughan Hilts
	 *
	 */
	public interface ShapeFilter {

		/**
		 * Filters a set of shape entries using the given context. It is up to the
		 * implementor to decide on the context of which it will be used.
		 * 
		 * @param shapes
		 *            A simple iterable set of shapes that can be filtered.
		 * @param context
		 *            A data object decided by the implemented class that is used as
		 *            context for the filtering.
		 * @return
		 * @throws BadRequestException
		 */
		public Iterable<ShapeEntry> filter(Iterable<ShapeEntry> shapes,
				Object context) throws BadRequestException;

	}
	/**
	 * A basic 2D point that allows the representation of a single vertex in 2D
	 * space. Contains simple methods to get the cordinates. This object is
	 * immutable.
	 * 
	 * @author Vaughan Hilts, Brandon Smith
	 * 
	 */
	public class Point {

		private final int x;
		private final int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public String toString() {
			return ("(" + x + "," + y + ")");
		}

		@Override
		public boolean equals(Object other) {

			Point otherPoint = (Point) other;

			if (otherPoint == null)
				return false;

			if (otherPoint.x == this.x && otherPoint.y == this.y)
				return true;
			else
				return false;
		}

		/**
		 * Computes the distance between this point and another point.
		 * 
		 * @param point
		 *            The other point to compare to
		 * @return The distance between the two points
		 */
		public double distanceFrom(Point point) {

			double a = (this.getX() - point.getX());
			a = Math.pow(a, 2);

			double b = (this.getY() - point.getY());
			b = Math.pow(b, 2);

			return Math.sqrt(a + b);

		}

	}
	/**
	 * A Quad shape with 4 points
	 * @author Vaughan Hilts
	 *
	 */
	public class Quadrilateral extends Shape {

		private EnumSet<QuadProperty> _properties = EnumSet
				.noneOf(QuadProperty.class);

		public enum QuadProperty {
			SQUARE, RECTANGLE, RHOMBUS, PARALLELOGRAM, TRAPEZOID, CONVEX, CONCAVE
		};

		public boolean hasProperty(QuadProperty property) {
			return _properties.contains(property);
		}

		private void addProperty(QuadProperty property) {
			_properties.add(property);
		}

		/**
		 * Sets the concave or convex property on the Quad
		 */
		public void setConcave(boolean concave) {
			if (concave) {
				_properties.add(QuadProperty.CONCAVE);
			} else {
				_properties.add(QuadProperty.CONVEX);
			}

		}

		private void setFlags() {
			if (!this.hasProperty(QuadProperty.CONCAVE)) {
				Point p1 = this._vertices.get(0);
				Point p2 = this._vertices.get(1);
				Point p3 = this._vertices.get(2);
				Point p4 = this._vertices.get(3);

				double a = p1.distanceFrom(p2);
				double b = p2.distanceFrom(p3);
				double c = p3.distanceFrom(p4);
				double d = p4.distanceFrom(p1);

				double center1 = p1.distanceFrom(p3);
				double center2 = p2.distanceFrom(p4);

				double angleA = 0;
				double angleB = 0;
				double angleC = 0;
				double angleD = 0;

				angleA = Math.acos((Math.pow(d, 2) + Math.pow(a, 2) - Math.pow(
						center2, 2)) / (2 * d * a));
				angleB = Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(
						center1, 2)) / (2 * a * b));
				angleC = Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(
						center2, 2)) / (2 * b * c));
				angleD = Math.acos((Math.pow(c, 2) + Math.pow(d, 2) - Math.pow(
						center1, 2)) / (2 * c * d));

				angleA = Math.toDegrees(angleA);
				angleB = Math.toDegrees(angleB);
				angleC = Math.toDegrees(angleC);
				angleD = Math.toDegrees(angleD);

				// Normalize due to floating math fail...

				angleA = (int) Math.round(angleA);
				angleB = (int) Math.round(angleB);
				angleC = (int) Math.round(angleC);
				angleD = (int) Math.round(angleD);

				// TODO: Implement the actual flags...

				if (a == b && a == c && a == d && angleA == 90 && 90 == angleB
						&& angleC == 90 && angleD == 90) {
					this.addProperty(QuadProperty.SQUARE);
				}
				if (a == c && b == d && angleA == 90 & angleB == 90 && angleA == 90
						&& 90 == angleB && angleC == 90 && angleD == 90) {
					this.addProperty(QuadProperty.RECTANGLE);
				}
				if (a == c && b == d && angleA == angleC & angleB == angleD) {
					this.addProperty(QuadProperty.PARALLELOGRAM);
				}
				if (a == c && b == d && a == d && angleA == angleC
						& angleB == angleD) {
					this.addProperty(QuadProperty.RHOMBUS);
				}

				if ((angleA + angleB == 180 && angleC + angleD == 180)
						|| (angleB + angleC == 180 && angleD + angleA == 180)) {
					this.addProperty(QuadProperty.TRAPEZOID);
				}
			}


		}

		public Quadrilateral(List<Point> vertices) {
			super(vertices);
			setFlags();
		}

		public Quadrilateral(List<Point> vertices, boolean concave) {
			super(vertices);
			this.setConcave(concave);
			setFlags();
		}

		@Override
		public String getPackedProperties() {

			String packed = "";

			for (QuadProperty property : this._properties) {
				packed += property.toString() + ",";
			}

			if (packed.length() > 0) {
				packed = packed.substring(0, packed.length() - 1);
			}

			// TODO: Return packed
			return packed;

		}

	}
	public abstract class Shape {

		// A list of vertices mapped in memory
		protected List<Point> _vertices;

		public Shape(List<Point> vertices) {

			// We should sort out list of points before actually doing anything with
			// them
			// List<Point> sorted = getSortedClockwiseVertices(vertices);
			_vertices = vertices;
		}

		public void printPoints() {
			for (Point vertex : _vertices) {
				System.out.println(vertex.toString());
			}
		}

		/**
		 * Performs an equality test on the internal points stored to check if this
		 * shape shares the same points as those stored in the list.
		 * 
		 * @param points
		 *            The points to check against
		 * @return Returns true if the points are shared, false otherwise
		 */
		public boolean hasPoints(List<Point> points) {
			return this._vertices.containsAll(points);
		}


		public String toString() {
			String stuff = "";
			for (Point vertex : _vertices) {
				stuff += vertex;
			}

			return stuff;
		}

		@Override
		public boolean equals(Object other) {

			Shape otherShape = (Shape) other;

			if (otherShape == null)
				return false;

			if (this._vertices.size() != otherShape._vertices.size())
				return false;

			for (Point p : this._vertices) {
				boolean found = false;
				for (Point q : otherShape._vertices) {
					if (p.equals(q))
						found = true;
				}

				if (!found)
					return false;
			}

			return true;
		}

		public abstract String getPackedProperties();

		public String getPackedString() {

			String packedPoints = "";

			// Extract our packed points
			for (Point p : this._vertices) {
				packedPoints += p.getX() + "," + p.getY() + ",";
			}

			// Get off the ending of the string
			packedPoints = packedPoints.substring(0, packedPoints.length() - 1);

			String packedProperties = getPackedProperties();

			return packedPoints + ":" + packedProperties;

		}

	}
	/**
	 * A triangle shape with 3 points
	 * @author Vaughan Hilts
	 *
	 */
	public class Triangle extends Shape {

		private EnumSet<TriangleProperty> _properties = EnumSet
				.noneOf(TriangleProperty.class);

		public enum TriangleProperty {
			SCALENE, EQUILATERAL, ISOSCELES, ACUTE, RIGHT, OBTUSE
		};

		/**
		 * Verifies whether or not a property has a given flag.
		 * 
		 * @param property
		 * @return
		 */
		public boolean hasProperty(TriangleProperty property) {
			return _properties.contains(property);
		}

		/**
		 * Adds a property to the list of properties on this triangle.
		 * 
		 * @param property
		 */
		private void addProperty(TriangleProperty property) {
			_properties.add(property);
		}

		private void createFlags() {

			Point p1 = this._vertices.get(0);
			Point p2 = this._vertices.get(1);
			Point p3 = this._vertices.get(2);

			double a = p1.distanceFrom(p2);
			double b = p2.distanceFrom(p3);
			double c = p3.distanceFrom(p1);

			double angleA = 0;
			double angleB = 0;
			double angleC = 0;

			angleC = Math.acos((Math.pow(c, 2) - Math.pow(a, 2) - Math.pow(b, 2))
					/ (-2 * a * b));
			double cRatio = c / Math.sin(angleC);

			angleB = Math.asin(b / cRatio);

			// To degrees
			angleC = Math.toDegrees(angleC);
			angleB = Math.toDegrees(angleB);

			// Normalize due to floating math fail...
			angleC = Math.round(angleC);
			angleB = Math.round(angleB);

			// Triangles add up to 100 degrees
			angleA = 180 - angleC - angleB;
			angleA = Math.round(angleA);

			// Get our integer angles now to do our math as approximations
			int intAngleA = (int) angleA;
			int intAngleB = (int) angleB;
			int intAngleC = (int) angleC;

			int intA = (int) Math.round(a);
			int intB = (int) Math.round(b);
			int intC = (int) Math.round(c);

			// Check if it's a right triangle
			if (intAngleA == 90 || intAngleB == 90 || intAngleC == 90) {
				this.addProperty(TriangleProperty.RIGHT);
			}

			// Test for acute
			if (intAngleA < 90 && intAngleB < 90 && intAngleC < 90) {
				this.addProperty(TriangleProperty.ACUTE);
			}

			// Test for obtuse
			if (intAngleA > 90 || intAngleB > 90 || intAngleC > 90) {
				this.addProperty(TriangleProperty.OBTUSE);
			}

			// Check for scalene
			if ((intA != intB && intA != intC && intC != intB)
					&& (intAngleA != intAngleB && intAngleA != intAngleC && intAngleC != intAngleB)) {
				this.addProperty(TriangleProperty.SCALENE);
			}

			// Check if we're equilateral
			else if ((intAngleA == 60 || intAngleB == 60 || intAngleC == 60)
					&& intA == intB && intB == intC && intC == intA) {
				this.addProperty(TriangleProperty.EQUILATERAL);
			} else {
				this.addProperty(TriangleProperty.ISOSCELES); // otherwise, isoceles
			}

		}

		public Triangle(List<Point> vertices) {
			super(vertices);

			createFlags();
		}

		@Override
		public String getPackedProperties() {

			String packed = "";

			for (TriangleProperty property : this._properties) {
				packed += property.toString() + ",";
			}

			packed = packed.substring(0, packed.length() - 1);

			return packed;
		}

	}

}
