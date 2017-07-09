package com.hkta.educentresystem.dto;

import java.io.Serializable;
import java.util.List;

public class SimpleListResponse<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5808586477721379042L;

	private boolean success;
	private List<T> results;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
	
	
}
