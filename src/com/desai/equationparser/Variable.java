package com.desai.equationparser;

public class Variable {
	private String name;
	private double value;
	
	public Variable(String name, double value) {
		this.name=name;
		this.value=value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public void setValue(double val) {
		this.value=val;
	}
}
