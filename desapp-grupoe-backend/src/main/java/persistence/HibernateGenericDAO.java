package persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.FlushMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generic hibernate DAO
 *
 * @param <T>
 */

public abstract class HibernateGenericDAO<T> extends HibernateDaoSupport implements GenericRepository<T>, Serializable {

    private static final long serialVersionUID = 5058950102420892922L;

    protected Class<T> persistentClass = this.getDomainClass();

    @Transactional
    public int count() {
        List<Long> list = (List<Long>) this.getHibernateTemplate()
                .find("select count(*) from " + this.persistentClass.getName() + " o");

        // this.getHibernateTemplate().execute(new HibernateCallback<Car>() {
        //
        // @Override
        // public Car doInHibernate(final Session session) throws
        // HibernateException, SQLException {
        // throw new UnsupportedOperationException();
        // }
        // });
        Long count = list.get(0);
        return count.intValue();

    }

    @Transactional
    public void delete(final T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Transactional
    public void deleteById(final Serializable id) {
        T obj = this.findById(id);
        this.getHibernateTemplate().delete(obj);
    }

    @Transactional
    public List<T> findAll() {
        List<T> find = (List<T>) this.getHibernateTemplate().find("from " + this.persistentClass.getName() + " o");
        return find;

    }

    @Transactional
    public List<T> findByExample(final T exampleObject) {
        return this.getHibernateTemplate().findByExample(exampleObject);

    }

    @Transactional
    public T findById(final Serializable id) {
        return this.getHibernateTemplate().get(this.persistentClass, id);
    }

    protected abstract Class<T> getDomainClass();

    @Transactional
    public void save(final T entity) {
        this.getHibernateTemplate().save(entity);
        this.getHibernateTemplate().flush();
    }

    @Transactional
    public void update(final T entity) {
        this.getHibernateTemplate().update(entity);
    }


}