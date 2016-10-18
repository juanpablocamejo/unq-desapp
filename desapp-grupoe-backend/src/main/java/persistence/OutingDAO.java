package persistence;

import model.outings.Outing;

import java.util.List;

public class OutingDAO extends HibernateGenericDAO<Outing> implements GenericRepository<Outing> {

    @Override
    protected Class<Outing> getDomainClass() {
        return Outing.class;
    }

    public List<Outing> findByName(String name) {
        return (List<Outing>) this.getHibernateTemplate()
                .find("from " + this.getDomainClass() + " where name = ?", name);
    }

    public Outing getOutingById(int id) {
        return super.findById(id);
    }


}
