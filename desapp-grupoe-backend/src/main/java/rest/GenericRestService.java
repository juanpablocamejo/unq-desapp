package rest;

import org.eclipse.jetty.http.HttpStatus;
import services.GenericService;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

public class GenericRestService<T> {
    GenericService<T> service;

    public List<T> getAll() {
        return service.retriveAll();
    }

    public Response findById(int id) {
        T obj = service.findById(id);
        if (obj == null) {
            return Response.ok("No se encontro la entidad con el id: " + id).status(HttpStatus.NOT_FOUND_404).build();
        } else {
            return Response.ok(obj).status(HttpStatus.OK_200).build();
        }
    }

    public Response deleteById(int id) {
        T object = service.findById(id);
        if (object == null) {
            return Response.ok("No se encontro la entidad con el id: " + id).status(HttpStatus.NOT_FOUND_404).build();
        }
        service.delete(object);
        return Response.ok("Se elimino correctamente").build();
    }

    public GenericService<T> getService() {
        return service;
    }

    public void setService(GenericService<T> service) {
        this.service = service;
    }
}
