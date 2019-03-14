package com.example.inventory;

public class DashboardData {

	private int bucketId;
	private String departmentId;
	private String bucketName;
	private String location;
	private String unitOfMeasurement;
	private int maxMeasurement;
	private Double capacity;
	
	
	
	public DashboardData(int bucketId, String departmentId, String bucketName, String location,
			String unitOfMeasurement, int maxMeasurement, Double capacity) {
		super();
		this.bucketId = bucketId;
		this.departmentId = departmentId;
		this.bucketName = bucketName;
		this.location = location;
		this.unitOfMeasurement = unitOfMeasurement;
		this.maxMeasurement = maxMeasurement;
		this.capacity = capacity;
	}

	public DashboardData() {}

	public int getBucketId() {
		return bucketId;
	}

	public void setBucketId(int bucketId) {
		this.bucketId = bucketId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public int getMaxMeasurement() {
		return maxMeasurement;
	}

	public void setMaxMeasurement(int maxMeasurement) {
		this.maxMeasurement = maxMeasurement;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	
	
	
	
}
