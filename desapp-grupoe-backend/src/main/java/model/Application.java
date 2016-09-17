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
    private static List<OutingPlace> places;
    private static List<OutingEvent> events;

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

    public static List<IPlanningResult> findOutings(OutingFilter filter) {

        List<IPlanningResult> outingsByTag = findOutingsByTag(filter);
        List<IPlanningResult> outingsByPrice = findOutingsByPrice(filter);
        List<IPlanningResult> outingsByDate = findOutingsByDate(filter);

        return matchOutings(matchOutings(outingsByTag, outingsByPrice), outingsByDate);

    }

    private static List<IPlanningResult> findOutingsByDate(OutingFilter filter) {
        List<IPlanningResult> outingsByDate = new ArrayList<>();
        for (OutingPlace op : places) {
            if (op.getWeekTimeSchedule().includes(filter.getDate(), filter.getTimeSlot())) {
                outingsByDate.add(op);
            }
        }
        return outingsByDate;
    }

    private static List<IPlanningResult> findOutingsByPrice(OutingFilter filter) {
        List<IPlanningResult> outingsByPrice = new ArrayList<>();
        for (OutingPlace op : places) {
            if (op.getPrice() <= filter.getMaxPrice()) {
                outingsByPrice.add(op);
            }
        }
        return outingsByPrice;
    }

    private static List<IPlanningResult> findOutingsByTag(OutingFilter filter) {

        List<IPlanningResult> outingsByTag = new ArrayList<>();

        if (filter.getSearchTag() == null) {
            /**
             * TODO
             */
            ;
        }

        for (OutingPlace op : places) {
            if (op.getTags().contains(filter.getSearchTag())) {
                outingsByTag.add(op);
            }
        }
        return outingsByTag;
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
