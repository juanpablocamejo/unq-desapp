package persistence.strategies;

import model.outings.OutingEvent;
import model.outings.OutingPlace;
import model.users.User;
import persistence.HibernateGenericDAO;

import java.util.ArrayList;
import java.util.List;

public class CouplesStrategy extends BasicSearchStrategy {
    public CouplesStrategy(HibernateGenericDAO dao) {
        this.setDao(dao);
    }

    @Override
    public List<OutingEvent> orderEvents(User user, List<OutingEvent> queryResults) {
        List<OutingEvent> results = new ArrayList<>();

        if (user.getProfile().getTags().isEmpty()) {
            return results;
        }

        for (OutingEvent oe : queryResults) {
            if (oe.matchWith(user.getProfile().getTags())) {
                results.add(oe);
            }
        }
        return results;
    }

    @Override
    public List<OutingPlace> orderPlaces(User user, List<OutingPlace> queryResults) {
        List<OutingPlace> results = new ArrayList<>();

        if (user.getProfile().getTags().isEmpty()) {
            return results;
        }

        for (OutingPlace op : queryResults) {
            if (op.matchWith(user.getProfile().getTags())) {
                results.add(op);
            }
        }
        return results;
    }
}
