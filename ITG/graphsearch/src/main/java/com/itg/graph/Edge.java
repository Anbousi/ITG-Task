package com.itg.graph;
public class Edge extends Node{
	private int cost;
	private int from;
	private int fCost;
	public Edge(int value, int cost , int from , int weight) {
		super(value,weight);
		this.setCost(cost);
		this.setFrom(from);
		
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}

	
	public int getfCost() {
		return fCost;
	}
	public void setfCost(int fCost) {
		this.fCost = fCost;
	}
	@Override
	public String toString() { 
	    return "( from "+this.getFrom()+" TO " + this.getValue() + "," + this.cost  +", estCost "+this.getWeight()+ ")" ;
	} 
	
}
