package org.jsp.UserApp.Exception;

@SuppressWarnings("serial")
public class IdNotFoundException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Invalid Id..!!!";
	}
}
