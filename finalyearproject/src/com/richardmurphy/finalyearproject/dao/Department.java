package com.richardmurphy.finalyearproject.dao;

public class Department {
	private int departmentId;
	private String name;
	
	
	public Department() {
	}
	public Department(int departmentId, String name) {
		super();
		this.departmentId = departmentId;
		this.name = name;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", name=" + name + "]";
	}
	
	
}
