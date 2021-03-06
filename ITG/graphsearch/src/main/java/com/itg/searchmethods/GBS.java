package com.itg.searchmethods;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.itg.graph.Edge;
import com.itg.graph.Graph;
import com.itg.graph.Node;

public class GBS {
	public static HashMap<String, Object> GBSearch(List<List<Integer>> record, int start , int goal) {
		Graph graph = new Graph(record);
		Node n = graph.getNode(start);
		Edge node =new Edge(start, 0 ,start , n.getWeight() );
		ArrayList<Edge> frontier = new ArrayList<>();
		ArrayList<Edge> visited = new ArrayList<>();
		HashMap<String, Object> GBSdata = new HashMap<String, Object>();
		
		frontier.add(node);
		
		do {
			if(frontier.size() == 0) {
				break;
			}
			
			else {
				
				node=frontier.remove(0);
//				 System.out.println(node);
				if(node.getValue()==goal) {
					visited.add(node);
					break;
				}
				visited.add(node);
				ArrayList<Edge> child =addParentCost(sorting(graph.getNodes(node.getValue())),node.getCost());
				for (Edge edge : child) {
					if(!containe(frontier, edge)&&!containe(visited, edge)) {
						frontier.add(edge);
					}
				}
				frontier = sorting(frontier);
			}
		}while(true);	  
	    GBSdata.put("visited",toText( editVisited(visited),","));
	    GBSdata.put("evaluated", editVisited(visited).size());
	    GBSdata.put("path",toText( findPath(visited),"->"));
	    GBSdata.put("minCost", visited.get(visited.size()-1).getCost());
		return GBSdata;

	}
	public static ArrayList<Edge> addParentCost(ArrayList<Edge> q,Integer PCost) {
		for (Edge edge : q) {
			edge.setCost(edge.getCost()+PCost);
		}
		return q;
		
	}
	
	
	public static ArrayList<Edge> sorting(ArrayList<Edge> q) {
//		Sorting ArrayList depends on lower cost node.
		
		ArrayList<Edge> sortedArray = new ArrayList<>();

		while(q.size() != 0) {
			int index = 0;
			Edge min = q.get(0);
			int minIndex = 0;
			for (Edge e : q) {
				
				if(e.getWeight()<min.getWeight()) {
					min=e;
					minIndex = index;
					
				}
				index++;	
			}
			sortedArray.add(min);
			q.remove(minIndex);

			}
		return sortedArray;
	}
	
	public static boolean containe(ArrayList<Edge> temp,Edge edge) {
		for (Edge edTemp : temp) {
			if(edTemp.getValue()==edge.getValue()) {
				return true;
			}
		}
		return false;
		
	}
	
	public static ArrayList<Integer> findPath(ArrayList<Edge> nodes){
		ArrayList<Integer> path = new ArrayList<>();
		Integer temp=nodes.get(nodes.size()-1).getValue();
			for (int i=nodes.size()-1;i>=0;i--) {
				if(nodes.get(i).getValue()==temp) {
					path.add(temp);
					temp=nodes.get(i).getFrom();
				}
			}
			Collections.reverse(path);
		return path;
		
	}
	public static ArrayList<Integer> editVisited(ArrayList<Edge> visited){
		ArrayList<Integer> temp = new ArrayList<>();
		
		for (Edge edge : visited) {
			temp.add(edge.getValue());
			
		}
		System.out.println(temp);
	return temp;
	}	
	
	private static String toText(ArrayList<Integer> temp,String sign ) {
		String p="";
		for (int i = 0 ; i < temp.size() ; i++) {
			
			if(i==0) {
				p+=temp.get(i);
			}
			else{
				p+=" " +sign+" "+ temp.get(i);
			}
		}
		return p;
	}
}


