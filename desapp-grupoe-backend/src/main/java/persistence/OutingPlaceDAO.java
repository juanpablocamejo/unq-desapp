package persistence;

import model.outings.OutingPlace;

public class OutingPlaceDAO extends HibernateGenericDAO<OutingPlace> implements GenericRepository<OutingPlace> {
    @Override
    protected Class<OutingPlace> getDomainClass() {
        return OutingPlace.class;
    }
}
