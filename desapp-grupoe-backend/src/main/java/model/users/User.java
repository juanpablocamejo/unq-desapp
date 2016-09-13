package model.users;

import model.locations.Address;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String surname;
    private Address location;
    private Profile profile;
    private List<User> friends = new ArrayList<>();

    public User(String name, String surname, Address location, Profile profile, List<User> friends) {
        this.name = name;
        this.surname = surname;
        this.location = location;
        this.profile = profile;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User friend) {
        if (!getFriends().contains(friend)) {
            getFriends().add(friend);
        }
    }

    public void removeFriend(User friend) {
        if (getFriends().contains(friend)) {
            getFriends().remove(friend);
        }
    }

    public Profile getFriendsProfile() {
        List<Profile> friendsProfiles = new ArrayList<>();
        for (User friend : this.friends) {
            friendsProfiles.add(friend.getProfile());
        }
        return Profile.mergeProfiles(friendsProfiles);
    }
}

