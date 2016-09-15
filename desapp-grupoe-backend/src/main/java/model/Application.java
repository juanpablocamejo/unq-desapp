package model;

import model.outings.OutingEvent;
import model.outings.OutingPlace;
import model.planning.IPlanningResult;
import model.planning.OutingFilter;
import model.users.User;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private List<User> users;
    private static List<OutingPlace> places = new ArrayList<>();
    private static List<OutingEvent> events = new ArrayList<>();

    public static List<IPlanningResult> findOutings(OutingFilter filter) {

        List<IPlanningResult> outings = new ArrayList<>();
        for (OutingPlace op : places) {
            if (op.getTags().contains(filter.getSearchTag())) {
                outings.add(op);
            }
        }

        for (OutingEvent oe : events) {
            if (oe.getTags().contains(filter.getSearchTag())) {
                outings.add(oe);
            }
        }

        return outings;

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<OutingPlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<OutingPlace> places) {
        this.places = places;
    }

    public List<OutingEvent> getEvents() {
        return events;
    }

    public void setEvents(List<OutingEvent> events) {
        this.events = events;
    }
}
