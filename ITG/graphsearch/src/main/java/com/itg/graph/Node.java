package com.itg.graph;
public class Node {
	private int value , weight;

	public Node (int value , int weight) {
		this.value = value;
		this.weight = weight;
	}

	public int getValue() {
		return value;
	}

	public int getWeight() {
		return weight;
	}
	
	@Override
	public String toString() { 
	    return "value: '" + this.value + "', weight: '" + this.weight ;
	} 
	
	
	
}
