package com.prapps.app.core.persistence;

public enum RolesTypes {
	
	VERIFIER("ROLE_VERIFIER"),
	APPROVER("ROLE_APPROVER"),
	BANK_USER("ROLE_BANK_USER"),
	SUPER_ADMIN("ROLE_SUPER_USER");
	
	private final String value;
	
	RolesTypes(String type) {
		value = type;
	}
	
	@Override
	public String toString() {
        return value;
    }
}
