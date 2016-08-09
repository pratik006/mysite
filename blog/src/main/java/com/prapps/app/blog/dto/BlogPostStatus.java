package com.prapps.app.blog.dto;

public enum BlogPostStatus {

	COMPLETE("COMPLETE"),
	DRAFT("DRAFT");
	
	private String code;
	
	private BlogPostStatus(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static BlogPostStatus getByCode(String code) {
		for (BlogPostStatus value : BlogPostStatus.values()) {
			if (value.code.equalsIgnoreCase(code)) {
				return value;
			}
		}
		
		return null;
	}
}
