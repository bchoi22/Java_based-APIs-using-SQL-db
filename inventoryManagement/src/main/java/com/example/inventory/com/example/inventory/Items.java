package com.example.inventory;

public class Items {
	private String partNo;
	private String serialNo;
	private int weight;
	
	public Items(String partNo, String serialNo, int weight) {
		super();
		this.partNo = partNo;
		this.serialNo = serialNo;
		this.weight = weight;
	}
	
	public Items() {
		
	}
	
	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
