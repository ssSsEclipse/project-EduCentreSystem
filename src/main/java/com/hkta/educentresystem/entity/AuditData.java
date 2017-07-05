package com.hkta.educentresystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class AuditData {

	@Column(name = "create_datetime")
	private Date createDateTime;

	@Column(name = "modified_datetime")
	private Date modifiedDateTime;

	@PrePersist
	public void onPrePersist() {
		setCreateDateTime(new Date());
	}
	
	@PreUpdate
    public void onPreUpdate() {
		setModifiedDateTime(new Date());
    }

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Date modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

}
