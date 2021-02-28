package eu.codecache.linko.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Event not found")
public class EventNotFoundException extends Exception {

	public EventNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
