package exceptions;


import org.eclipse.jetty.http.HttpStatus;

public class EntityValidationException extends RequestException {

    public EntityValidationException(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY_422);
    }

    public EntityValidationException(String message, int status) {
        super(message, status);
    }
}
