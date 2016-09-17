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

        List<IPlanningResult> outingsByTag = new ArrayList<>();
        for (OutingPlace op : places) {
            if (op.getTags().contains(filter.getSearchTag())) {
                outingsByTag.add(op);
            }
        }

        List<IPlanningResult> outingsByPrice = new ArrayList<>();
        for (OutingPlace op : places) {
            if (op.getPrice() <= filter.getMaxPrice()) {
                outingsByPrice.add(op);
            }
        }

        List<IPlanningResult> outingsByDate = new ArrayList<>();
        for (OutingPlace op : places) {
            if (op.getWeekTimeSchedule().includes(filter.getDate(), filter.getTimeSlot())) {
                outingsByDate.add(op);
            }
        }

        return matchOutings(matchOutings(outingsByTag, outingsByPrice), outingsByDate);

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

    public static List<IPlanningResult> matchOutings(List<IPlanningResult> listA, List<IPlanningResult> listB) {
        List<IPlanningResult> finalOutings = new ArrayList<>();
        for (IPlanningResult outing : listA) {
            if (listB.contains(outing)) {
                finalOutings.add(outing);
            }
        }
        return finalOutings;
    }
}
