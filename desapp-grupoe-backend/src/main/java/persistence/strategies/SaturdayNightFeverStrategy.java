package persistence.strategies;

import model.outings.OutingEvent;
import model.outings.OutingPlace;
import model.users.User;
import org.hibernate.Query;

import java.util.List;

public class SaturdayNightFeverStrategy implements IPlanningStrategy {
    @Override
    public Query getEventsQuery(OutingFilter filter) {
        return null;
    }

    @Override
    public Query getPlacesQuery(OutingFilter filter) {
        return null;
    }

    @Override
    public Boolean hasEvents() {
        return null;
    }

    @Override
    public Boolean hasPlaces() {
        return null;
    }

    @Override
    public List<OutingEvent> orderEvents(User user, List<OutingEvent> queryResults) {
        return null;
    }

    @Override
    public List<OutingPlace> orderPlaces(User user, List<OutingPlace> queryResults) {
        return null;
    }
}
