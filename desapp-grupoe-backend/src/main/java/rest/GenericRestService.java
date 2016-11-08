package rest;

import org.eclipse.jetty.http.HttpStatus;
import services.appservice.GenericService;

import javax.ws.rs.core.Response;
import java.util.List;

public class GenericRestService<T> {
    GenericService<T> service;

    public GenericService<T> getService() {
        return service;
    }

    public void setService(GenericService<T> service) {
        this.service = service;
    }

    public List<T> getAll() {
        return service.retriveAll();
    }

    public T findById(int id) {
        return service.findById(id);
    }

    public Response deleteById(int id) {
        T object = service.findById(id);
        if (object == null) {
            return Response.ok("No se encontro la entidad con el id: " + id).status(HttpStatus.NOT_FOUND_404).build();
        }
        service.delete(object);
        return Response.ok("Se elimino correctamente").status(HttpStatus.OK_200).build();
    }

    public Response create(T object) {
        service.save(object);
        return Response.ok(object).status(HttpStatus.OK_200).build();
    }
}
