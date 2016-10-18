package persistence;

import model.outings.OutingEvent;

public class OutingEventDAO extends HibernateGenericDAO<OutingEvent> implements GenericRepository<OutingEvent> {

    @Override
    protected Class<OutingEvent> getDomainClass() {
        return OutingEvent.class;
    }

}
