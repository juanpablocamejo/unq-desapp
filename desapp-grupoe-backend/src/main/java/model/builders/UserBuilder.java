package model.builders;

import model.locations.Address;
import model.users.Profile;
import model.users.User;

import java.util.ArrayList;
import java.util.List;


public class UserBuilder {

    private String name = "username";
    private String surname = "surname";
    private Address address = AddressBuilder.anyAddress().build();
    private Profile profile = ProfileBuilder.anyProfile().build();
    private List<User> friends = new ArrayList<>();

    public static UserBuilder anyUser() {
        return new UserBuilder();
    }

    public User build() {
        return new User(name, surname, address, profile, friends);
    }

    public UserBuilder withName(String n) {
        name = n;
        return this;
    }

    public UserBuilder withSurname(String s) {
        surname = s;
        return this;
    }

    public UserBuilder withAddress(Address a) {
        address = a;
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