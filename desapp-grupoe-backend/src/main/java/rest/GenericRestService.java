package rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.GenericService;

import java.util.List;

public class GenericRestService<T> {
    GenericService<T> service;

    public List<T> getAll() {
        return service.retriveAll();
    }

    public ResponseEntity<T> findById(int id) {
        T obj = service.findById(id);
        if (obj == null) {
            return new ResponseEntity("No existe la entidad con id " + id, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.FOUND);
        }
    }

    public ResponseEntity deleteById(int id) {
        T object = service.findById(id);
        if (object == null) {
            return new ResponseEntity("No se encontro la entidad con id " + id, HttpStatus.NOT_FOUND);
        }
        service.delete(object);
        return new ResponseEntity(object.getClass().getSimpleName() + " eliminado correctamente", HttpStatus.OK);
    }

    public GenericService<T> getService() {
        return service;
    }

    public void setService(GenericService<T> service) {
        this.service = service;
    }
}