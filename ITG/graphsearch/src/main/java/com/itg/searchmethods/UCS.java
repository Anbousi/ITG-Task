package com.itg.searchmethods;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.itg.graph.Edge;
import com.itg.graph.Graph;
import com.itg.graph.Node;



public class UCS {

		public static HashMap<String, Object> UCSearch(List<List<Integer>> record, int start , int goal) {
			Graph graph = new Graph(record);
			Edge node =new Edge(start, 0 ,start , 0);
			ArrayList<Edge> frontier = new ArrayList<>();
			ArrayList<Edge> visited = new ArrayList<>();
			
			System.out.println("....in searching....");
			HashMap<String, Object> UCSdata = new HashMap<String, Object>();
		
			frontier.add(node);
			
			do {
				if(frontier.size() == 0) {
					break;
				}
				
				else {
					
					node=frontier.remove(0);
//					 System.out.println(node);
					if(node.getValue()==goal) {
						visited.add(node);
						break;
					}
					visited.add(node);
					ArrayList<Edge> child =addParentCost(sorting(graph.getNodes(node.getValue())),node.getCost());
					for (Edge edgeCh : child) {
						if(!containe(visited,edgeCh)) {
							 if(containe(frontier,edgeCh)) {
									for (int k = 0; k<frontier.size();k++) {
										if(frontier.get(k).getValue()==edgeCh.getValue()) {
											if(frontier.get(k).getCost()>edgeCh.getCost()) {
												frontier.set(k, edgeCh);
											}
										}
									}
								}
							 else {frontier.add(edgeCh);}
						}
					}
				}
			}while(true);
			UCSdata.put("evaluated", editVisited(visited).size());
		    UCSdata.put("visited", toText( editVisited(visited),","));
		    UCSdata.put("path", toText( findPath(visited),"->"));
		    UCSdata.put("minCost", visited.get(visited.size()-1).getCost());
			return UCSdata;
		    
   
		}
		public static ArrayList<Edge> addParentCost(ArrayList<Edge> q,Integer PCost) {
			for (Edge edge : q) {
				edge.setCost(edge.getCost()+PCost);
			}
			return q;
			
		}
		
		
		public static ArrayList<Node> sorting(ArrayList<Edge> q) {
//			Sorting ArrayList depends on lower cost node.
			
			ArrayList<Node> sortedArray = new ArrayList<>();

			while(q.size() != 0) {
				int index = 0;
				Edge min = q.get(0);
				int minIndex = 0;
				for (Edge e : q) {
					
					if(e.getCost()<min.getCost()) {
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