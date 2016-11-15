package persistence.strategies;

import model.outings.OutingEvent;
import model.outings.OutingPlace;
import model.tags.Tag;
import model.users.User;
import persistence.HibernateGenericDAO;

import java.util.ArrayList;
import java.util.List;

public class InexpensiveStrategy extends BasicSearchStrategy implements IPlanningStrategy {

    public InexpensiveStrategy(HibernateGenericDAO dao) {
        this.setDao(dao);
    }

    @Override
    public List<OutingEvent> orderEvents(User user, List<OutingEvent> queryResults) {

        List<OutingEvent> results = new ArrayList<>();
        double inexpensiveOutingLimit = user.getProfile().getInexpensiveOutingLimit();
        List<Tag> tags = user.getProfile().getTags();

        for (OutingEvent oe : queryResults) {
            if ((oe.matchWith(tags)) && oe.getPrice() <= inexpensiveOutingLimit) {
                results.add(oe);
            }
        }
        return results;
    }

    @Override
    public List<OutingPlace> orderPlaces(User user, List<OutingPlace> queryResults) {
        List<OutingPlace> results = new ArrayList<>();
        double inexpensiveOutingLimit = user.getProfile().getInexpensiveOutingLimit();

        for (OutingPlace op : queryResults) {
            if ((op.matchWith(user.getProfile().getTags())) && op.getPrice() <= inexpensiveOutingLimit) {
                results.add(op);
            }
        }
        return results;
    }
}
