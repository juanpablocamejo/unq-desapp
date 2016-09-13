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
    private List<OutingPlace> places = new ArrayList<>();
    private List<OutingEvent> events = new ArrayList<>();

    public static List<IPlanningResult> findOutings(OutingFilter filter) {
        return null;
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
