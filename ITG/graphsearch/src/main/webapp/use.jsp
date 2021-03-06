<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
<h1>How To Use</h1>
<h3><a href="/">Back</a></h3>
<p style="color: red">Please make sure the file extension is ".CSV file" and contains an adjacency matrix.</p>
<p>Example:</p>
<pre>
0,5,0,3,0,0,0
5,0,1,0,4,0,0
0,0,0,0,6,0,8
0,0,0,0,2,2,0
0,4,6,2,0,0,0
0,0,0,2,0,0,3
0,0,8,0,4,3,0
8,6,3,5,4,9,0
</pre>
<p>All rows except the last one are describing the edge cost between nodes.</p>
<p>The last row is describing the weight "Heuristic value"</p>
<br>
<pre>This web program was built using Java programming language with Spring boot 4.
The main purpose of this program is to calculate the Path between two node “Start and Goal nodes” using 
three different graph searching algorithms.

1-	Uniform Cost Search Algorithm.
2-	Greedy Best Search Algorithm.
3-	A* Search Algorithm.

The program will receive the data through uploading a “.CSV file”. This file will contain an adjacency matrix, which 
define the Nodes and the connections between these Nodes. These nodes will be transformed to a weighted and 
directed Graph data structure at first, then the program will be allowed to start the search depending on the 
selected search algorithm.

The result will be shown in a table with fields of (Algorithm, # of Evaluated Nodes, Evaluated Nodes, Path and Distance).
 </pre>
</div>
</body>
</html>