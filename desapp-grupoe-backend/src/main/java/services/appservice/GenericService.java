package services.appservice;

import org.springframework.transaction.annotation.Transactional;
import persistence.GenericRepository;

import java.io.Serializable;
import java.util.List;


public class GenericService<T> implements Serializable {

    private static final long serialVersionUID = -6540963495078524186L;

    private GenericRepository<T> repository;

    public GenericRepository<T> getRepository() {
        return this.repository;
    }

    public void setRepository(final GenericRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    public void delete(final T object) {
        this.getRepository().delete(object);
    }

    @Transactional(readOnly = true)
    public List<T> retriveAll() {
        return this.getRepository().findAll();
    }

    @Transactional
    public void save(final T object) {
        this.getRepository().save(object);
    }

    @Transactional
    public void update(final T object) {

        this.getRepository().update(object);
    }

    @Transactional
    public T findById(final int id) {
        return this.getRepository().findById(id);
    }


}
