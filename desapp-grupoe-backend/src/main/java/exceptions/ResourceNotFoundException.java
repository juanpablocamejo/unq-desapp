package exceptions;

import org.eclipse.jetty.http.HttpStatus;

public class ResourceNotFoundException extends RequestException {

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND_404);
    }
}
