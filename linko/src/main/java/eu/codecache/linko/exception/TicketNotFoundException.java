package eu.codecache.linko.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Event not found")
public class TicketNotFoundException extends Exception {

	public TicketNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}