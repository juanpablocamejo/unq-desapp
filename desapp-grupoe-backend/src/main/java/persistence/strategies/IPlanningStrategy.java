package persistence.strategies;

import model.outings.OutingEvent;
import model.outings.OutingPlace;
import model.users.User;
import org.hibernate.Query;

import java.util.List;

public interface IPlanningStrategy {
    Query getEventsQuery(OutingFilter filter);

    Query getPlacesQuery(OutingFilter filter);

    Boolean hasEvents();

    Boolean hasPlaces();

    List<OutingEvent> orderEvents(User user, List<OutingEvent> queryResults);

    List<OutingPlace> orderPlaces(User user, List<OutingPlace> queryResults);
}




