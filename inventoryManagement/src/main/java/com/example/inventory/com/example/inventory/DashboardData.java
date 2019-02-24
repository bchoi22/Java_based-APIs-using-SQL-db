package com.example.inventory;

public class DashboardData {

	private String name;
	private String location;
	private double capacity;
	private int numberOfItems;
	private double currentWeight;
	
	public DashboardData(String name, String location, double capacity, int numberOfItems, double currentWeight) {
		super();
		this.name = name;
		this.location = location;
		this.capacity = capacity;
		this.numberOfItems = numberOfItems;
		this.currentWeight = currentWeight;
	}
	
	public DashboardData() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	public int getNumberOfItems() {
		return numberOfItems;
	}
	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	public double getCurrentWeight() {
		return currentWeight;
	}
	public void setCurrentWeight(double currentWeight) {
		this.currentWeight = currentWeight;
	}
	
	
}
