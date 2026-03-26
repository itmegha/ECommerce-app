package com.example.demo.helpers;

public class HelperC {
	
	private String message;
	private Object data;
	public HelperC() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HelperC(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "HelperC [message=" + message + ", data=" + data + "]";
	}
	
	
	

}
