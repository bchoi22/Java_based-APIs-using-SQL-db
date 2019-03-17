package com.example.inventory;

import java.util.List;

public class Unit {
	private boolean hasWeight;
	private List<Items> items;
	
	public Unit(boolean hasWeight, List<Items> items) {
		super();
		this.hasWeight = hasWeight;
		this.items = items;
	}

	public boolean isHasWeight() {
		return hasWeight;
	}

	public void setHasWeight(boolean hasWeight) {
		this.hasWeight = hasWeight;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
	
	
}

