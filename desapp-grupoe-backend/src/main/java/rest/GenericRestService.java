package rest;

import exceptions.EntityValidationException;
import exceptions.ResourceNotFoundException;
import services.GenericService;

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
            throw new ResourceNotFoundException("Non existant entity with id " + id);
        }
        service.delete(object);
        return Response.ok("Se elimino correctamente").build();
    }

    public void create(T object) throws EntityValidationException {
        service.save(object);

    }
}
