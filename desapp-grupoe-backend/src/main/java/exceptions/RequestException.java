package exceptions;

import javax.ws.rs.core.Response;

public class RequestException extends RuntimeException {
    private int httpStatusCode;

    public RequestException(String message, int statusCode) {
        super(message);
        httpStatusCode = statusCode;
    }

    public Response getHttpResponse() {
        return Response.status(httpStatusCode).entity(
                new Object() {
                    public String message = getMessage();
                }
        ).build();
    }
}
