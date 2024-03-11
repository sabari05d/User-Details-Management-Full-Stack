package org.jsp.UserApp.dto;

public class ResponseStructure<S> {

	private String Message;
	private S data;
	private int statusCode;

	public S getData() {
		return data;
	}

	public void setData(S data) {
		this.data = data;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
