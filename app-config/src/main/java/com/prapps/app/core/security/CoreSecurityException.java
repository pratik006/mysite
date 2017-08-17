package com.prapps.app.core.security;

public class CoreSecurityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private Throwable throwable;
	private String code;
	private String msg;
	
	public CoreSecurityException(Throwable throwable) {
		this.throwable = throwable;
	}
	
	public CoreSecurityException(String code) {
		this.code = code;
	}
	
	public CoreSecurityException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}
