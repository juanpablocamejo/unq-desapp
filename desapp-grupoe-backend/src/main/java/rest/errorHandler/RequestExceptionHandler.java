package rest.errorHandler;


import exceptions.RequestException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestExceptionHandler implements ExceptionMapper<RequestException> {
    @Override
    public Response toResponse(RequestException exception) {
        return exception.getHttpResponse();
    }
}
