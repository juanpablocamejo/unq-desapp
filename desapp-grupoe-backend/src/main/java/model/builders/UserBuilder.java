package model.builders;

import model.locations.Address;
import model.tags.Tag;
import model.users.Profile;
import model.users.User;

import java.util.ArrayList;
import java.util.List;


public class UserBuilder {

    private String name = "username";
    private String surname = "surname";
    private String email = "xxx@gmail.com";
    private String image = "path";
    private Address address = AddressBuilder.anyAddress().build();
    private Profile profile = ProfileBuilder.anyProfile().build();
    private List<User> friends = new ArrayList<>();

    public static UserBuilder anyUser() {
        return new UserBuilder();
    }

    public User build() {
        return new User(name, surname, email, image, address, profile, friends);
    }

    public UserBuilder withName(String n) {
        name = n;
        return this;
    }

    public UserBuilder withSurname(String s) {
        surname = s;
        return this;
    }

    public UserBuilder withEmail(String e) {
        email = e;
        return this;
    }

    public UserBuilder withImage(String i) {
        image = i;
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

    public UserBuilder withPriceLimit(double p) {
        profile.setInexpensiveOutingLimit(p);
        return this;
    }

    public UserBuilder withFriends(List<User> f) {
        friends = f;
        return this;
    }

    public UserBuilder withFriend(User f) {
        friends.add(f);
        return this;
    }

    public UserBuilder withTag(Tag t) {
        profile.addTag(t);
        return this;
    }

}