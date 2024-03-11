package org.jsp.UserApp.Exception;

@SuppressWarnings("serial")
public class NoUserFoundException extends RuntimeException {

	public NoUserFoundException(String message) {
		super(message);
	}

}
