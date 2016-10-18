package rest;

import services.GenericService;

public class GenericRestService<T> {
    GenericService<T> service;

    public GenericService<T> getService() {
        return service;
    }

    public void setService(GenericService<T> service) {
        this.service = service;
    }
}
