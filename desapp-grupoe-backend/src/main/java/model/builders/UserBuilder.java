package model.builders;

import model.locations.Address;
import model.locations.Coord;
import model.users.Profile;
import model.users.User;

import java.util.ArrayList;
import java.util.List;

import static model.builders.ProfileBuilder.anyProfile;

public class UserBuilder {

    private String name = "username";
    private String surname = "surname";
    private Address location = new Address(new Coord(1000, 2000));
    private Profile profile = anyProfile().build();
    private List<User> friends = new ArrayList<>();

    public static UserBuilder anyUser() {
        return new UserBuilder();
    }

    public User build() {
        return new User(name, surname, location, profile, friends);
    }

    public UserBuilder withName(String n) {
        name = n;
        return this;
    }

    public UserBuilder withSurname(String s) {
        surname = s;
        return this;
    }

    public UserBuilder withLocation(Address a) {
        location = a;
        return this;
    }

    public UserBuilder withProfile(Profile p) {
        profile = p;
        return this;
    }

    public UserBuilder withFriends(List<User> f) {
        friends = f;
        return this;
    }

}
