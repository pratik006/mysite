package com.prapps.app.core.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_ATTR")
public class UserAttributeEntity {

	@Id
	@Column(name="ATTR_ID")
	private long attributeId;
	
	@Column(name="ATTR_NAME")
	private String attributeName;
	
	@Column(name="ATTR_VALUE")
	private String attributeValue;

	public long getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
}
