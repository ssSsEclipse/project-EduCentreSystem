package com.hkta.educentresystem.dto;

import java.io.Serializable;

public class SimpleCentre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3633799561276481410L;

	private String value;
	private String name;
	
	
	
	public SimpleCentre(String name, String value) {
		super();
		this.value = value;
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
