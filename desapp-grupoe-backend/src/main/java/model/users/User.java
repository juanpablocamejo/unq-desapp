package model.users;

import model.Entity;
import model.builders.ProfileBuilder;
import model.locations.Address;

import java.util.ArrayList;
import java.util.List;

public class User extends Entity {
    private String name;
    private String surname;
    private String email;
    private String image;
    private Address address;
    private Profile profile;
    private List<User> friends = new ArrayList<>();

    public User() {
        this.profile = ProfileBuilder.anyProfile().build();
    }

    public User(String name, String surname, String email, String image, Address address, Profile profile, List<User> friends) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.image = image;
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        if (friend.getId() != getId()) {
            boolean hasFriend = false;
            for (int i = 0; i < getFriends().size(); i++) {
                if (getFriends().get(i).getId() == friend.getId()) {
                    hasFriend = true;
                    break;
                }
            }
            if (!hasFriend) getFriends().add(friend);
        }
    }

    public void removeFriend(User friend) {
        for (int i = 0; i < getFriends().size(); i++) {
            if (getFriends().get(i).getId() == friend.getId()) {
                getFriends().remove(i);
            }
        }
    }

    public Profile obtainFriendsProfile() {
        List<Profile> friendsProfiles = new ArrayList<>();
        for (User friend : friends) {
            friendsProfiles.add(friend.getProfile());
        }
        return Profile.mergeProfiles(friendsProfiles);
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getSurname() + "," + getImage();
    }

    public String[] toArray() {
        return new String[]{Integer.toString(getId()), getName() + " " + getSurname(), getImage()};
    }

}

