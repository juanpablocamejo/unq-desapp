package persistence;

import model.outings.OutingEvent;
import persistence.strategies.IPlanningStrategy;
import persistence.strategies.OutingFilter;
import persistence.strategies.StrategyBuilder;

import java.util.ArrayList;
import java.util.List;

public class OutingEventDAO extends HibernateGenericDAO<OutingEvent> implements GenericRepository<OutingEvent> {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    protected Class<OutingEvent> getDomainClass() {
        return OutingEvent.class;
    }

    public List<OutingEvent> findEvents(OutingFilter filter) {
        IPlanningStrategy strategy = StrategyBuilder.anStrategy().withName(filter.getStrategy()).withDao(this).build();
        List<OutingEvent> results = new ArrayList<>();
        if (strategy.hasEvents()) {
            results = (List<OutingEvent>) strategy.getEventsQuery(filter).list();
            results = strategy.orderEvents(userDAO.findById(filter.getIdUser()), results);
        }
        return results;
    }
}
