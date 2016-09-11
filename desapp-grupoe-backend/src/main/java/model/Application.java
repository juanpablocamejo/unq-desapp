package model;

import model.outings.OutingEvent;
import model.outings.OutingPlace;
import model.users.User;

import java.util.ArrayList;
import java.util.List;

public class Application {
    List<User> users;
    List<OutingPlace> places = new ArrayList<>();
    List<OutingEvent> events = new ArrayList<>();
}
