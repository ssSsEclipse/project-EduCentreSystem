package com.hkta.educentresystem.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public class AuditData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4988083073979695084L;

	@Column(name = "create_datetime", updatable=false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createDateTime;

	@Column(name = "modified_datetime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date modifiedDateTime;

	@PrePersist
	public void onPrePersist() {
		this.createDateTime = new Date();
	}
	
	@PreUpdate
    public void onPreUpdate() {
		this.modifiedDateTime = new Date();
    }

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}


}
