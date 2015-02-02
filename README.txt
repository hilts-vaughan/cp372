QATTP Sample Usage README
=========================


Server:

1) Compile the server using "javac Server.java" (or javac *, if you prefer)
2) Run the server with "java Server 5555" (or whatever port you prefer)
3) The server will now run in this command window. No need to do anything else here.


QATTP Client:

1) Compile the client using "javac Client.java"
2) Run the client with "java Client"
3) Enter your port and hit connect.
4) Use the application

Usage Notes:

You can perform a GET on the server by using the syntax described in the RFC. For ease of use, some sample POST's and GET's have been provided.

These can be pasted into the textbox on the QATTP client as is, and then either the GET or POST pushed to use them.

POST:

0 8 1 4 9 8 4 7
0 4 0 0 4 0
4 4 0 0 4 0
0 0 0 2 1 2 1 0
0 0 3 4 8 4 5 0
0 0 3 4 8 4 5 0


GET:

Sample queries are provided below (you can just copy and paste):

B	Type: Concave
Q	Type: Concave
Q	Type: Convex
T	Type: Concave
B	Occurrences: 2
B	Occurrences: 0
B	Shares: 4 0
B	Shares: 4 0 0 0
B	Type: Square


You can test errors with stuff like...

B	Occurrences: -1
SOME_RANDOM_QUERY