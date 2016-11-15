package persistence;

import model.outings.OutingPlace;
import persistence.strategies.IPlanningStrategy;
import persistence.strategies.OutingFilter;
import persistence.strategies.StrategyBuilder;

import java.util.ArrayList;
import java.util.List;

public class OutingPlaceDAO extends HibernateGenericDAO<OutingPlace> implements GenericRepository<OutingPlace> {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    protected Class<OutingPlace> getDomainClass() {
        return OutingPlace.class;
    }

    public List<OutingPlace> findPlaces(OutingFilter filter) {
        IPlanningStrategy strategy = StrategyBuilder.anStrategy().withName(filter.getStrategy()).withDao(this).build();
        List<OutingPlace> results = new ArrayList<>();
        if (strategy.hasPlaces()) {
            results = (List<OutingPlace>) strategy.getPlacesQuery(filter).list();
            results = strategy.orderPlaces(userDAO.findById(filter.getIdUser()), results);
        }
        return results;
    }
}
