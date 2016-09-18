package model;

import model.outings.OutingEvent;
import model.outings.OutingPlace;
import model.planning.IPlanningResult;
import model.planning.OutingFilter;
import model.users.User;

import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private static List<OutingPlace> places;
    private static List<OutingEvent> events;
    private List<User> users;

    public static List<IPlanningResult> findOutings(OutingFilter filter) {
        List<IPlanningResult> filteredOutings = places.stream().filter(x -> filter.match(x)).collect(Collectors.toList());
        filteredOutings.addAll(events.stream().filter(x -> filter.match(x)).collect(Collectors.toList()));
        return filteredOutings;
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
        Application.places = places;
    }

    public List<OutingEvent> getEvents() {
        return events;
    }

    public void setEvents(List<OutingEvent> events) {
        Application.events = events;
    }

}
