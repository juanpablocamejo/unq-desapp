package rest.errorHandler;

import org.codehaus.jackson.map.JsonMappingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class JsonMappingExceptionHandler implements ExceptionMapper<JsonMappingException> {
    @Override
    public Response toResponse(JsonMappingException exception) {

        if (exception.getPath().size() > 0) {
            Object fieldName = new Object() {
                public String fieldName = exception.getPath().get(0).getFieldName();
            };
            return Response.status(Response.Status.BAD_REQUEST).entity(fieldName).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}