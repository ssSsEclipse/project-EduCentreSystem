package com.hkta.educentresystem.dto;

import java.io.Serializable;

public class ResponseMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6151354730790108877L;

	private Long entityId;
	private String entityType;
	private String message;
	
	public ResponseMessage(String message) {
		super();
		this.message = message;
	}
	public ResponseMessage(Long entityId, String entityType, String message) {
		super();
		this.entityId = entityId;
		this.entityType = entityType;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
}
