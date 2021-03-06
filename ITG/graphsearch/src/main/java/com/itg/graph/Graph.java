package com.itg.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
	
	HashMap<Integer , List<Node>> graph = new HashMap<>();
	
	public Graph(List<List<Integer>> records) {
		
		int numberOfNodes = records.size();
//		the last row in 2D array represent the weight for each node
		Integer nodesWeight = records.size() - 1;
		
		for(int i = 1 ; i<numberOfNodes ; i++) {
//			each key in HashMap has a value of array list contains all reached nodes by node(i)
			graph.put(i, new ArrayList<Node>());
			Integer weight =records.get(nodesWeight).get(i-1);
			graph.get(i).add(new Node(i,weight));
//			System.out.println(i +" " + z);
			List<Integer> internalArray = records.get(i-1);
			for (int j = 1 ; j<=internalArray.size(); j++) {
				if(internalArray.get(j-1) > 0) {
					Integer cost = internalArray.get(j-1);
					graph.get(i).add(new Edge(j,cost,i,records.get(nodesWeight).get(j-1)));
				}
			}
			
		}
	}
	
	public ArrayList getNodes(int key){
		ArrayList<Node> w =  new ArrayList<>(graph.get(key));
		w.remove(0);
		return  w  ;
		
	}
	public Node getNode(int key){
		ArrayList<Node> w =  new ArrayList<>(graph.get(key));


		return  w.get(0) ;
		
	}
	
	public void printGraph() {
		for (Integer i : graph.keySet()) {
			  System.out.println("key: " + i + " value: " + graph.get(i));
			}
	}
	
	public Integer findCost(Integer start , Integer goal) {
		ArrayList<Edge>x=new ArrayList<Edge>(getNodes(start));
		for (Edge edge : x) {
			if (edge.getValue()==goal) {
				return edge.getCost();
			}
		}
		return 0;
		
	}
	
}  
