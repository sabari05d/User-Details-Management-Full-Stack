package org.jsp.UserApp.Exception;

@SuppressWarnings("serial")
public class InvalidCredentialException extends RuntimeException {

	public InvalidCredentialException(String message) {
		super(message);
	}

}
