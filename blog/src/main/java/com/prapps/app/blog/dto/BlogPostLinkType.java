package com.prapps.app.blog.dto;

public enum BlogPostLinkType {
	PICTURE("pic");
	
	BlogPostLinkType(String type) {
		this.type = type;
	}
	
	public static BlogPostLinkType getByType(String typeStr) {
		for (BlogPostLinkType type : BlogPostLinkType.values()) {
			if (typeStr.equals(type.getType())) {
				return type;
			}
		}
		
		return null;
	}
	
	private String type;

	public String getType() {
		return type;
	}
}
