package exceptions;


import org.eclipse.jetty.http.HttpStatus;

public class EntityValidationException extends RequestException {

    public EntityValidationException(String message) {
        super(message, HttpStatus.BAD_REQUEST_400);
    }

    public EntityValidationException(String message, int status) {
        super(message, status);
    }
}
