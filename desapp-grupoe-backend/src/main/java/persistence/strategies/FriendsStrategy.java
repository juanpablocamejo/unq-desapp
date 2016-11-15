package persistence.strategies;

import model.outings.OutingEvent;
import model.outings.OutingPlace;
import model.tags.Tag;
import model.users.User;
import persistence.HibernateGenericDAO;

import java.util.ArrayList;
import java.util.List;

public class FriendsStrategy extends BasicSearchStrategy {
    public FriendsStrategy(HibernateGenericDAO dao) {
        this.setDao(dao);
    }

    @Override
    public List<OutingEvent> orderEvents(User user, List<OutingEvent> queryResults) {
        List<OutingEvent> results = new ArrayList<>();

        List<Tag> allTags = new ArrayList<>();
        if (!user.getProfile().getTags().isEmpty()) allTags.addAll(user.getProfile().getTags());
        if (!user.getFriends().isEmpty()) {
            for (User f : user.getFriends()) {
                allTags.addAll(f.getProfile().getTags());
            }
        }

        for (OutingEvent oe : queryResults) {
            if (oe.matchWith(allTags)) {
                results.add(oe);
            }
        }
        return results;
    }

    @Override
    public List<OutingPlace> orderPlaces(User user, List<OutingPlace> queryResults) {
        List<OutingPlace> results = new ArrayList<>();

        List<Tag> allTags = new ArrayList<>();
        if (!user.getProfile().getTags().isEmpty()) allTags.addAll(user.getProfile().getTags());
        if (!user.getFriends().isEmpty()) {
            for (User f : user.getFriends()) {
                allTags.addAll(f.getProfile().getTags());
            }
        }

        for (OutingPlace op : queryResults) {
            if (op.matchWith(allTags)) {
                results.add(op);
            }
        }
        return results;
    }
}
