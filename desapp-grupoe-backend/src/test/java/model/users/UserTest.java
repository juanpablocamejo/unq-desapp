package model.users;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static model.builders.UserBuilder.anyUser;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testAddFriendToTheListAddsCorrectly() {

        User friend1 = anyUser().withName("Pedro").build();
        User friend2 = anyUser().withName("Carlos").build();

        User user = anyUser().withName("Pablo").build();

        user.addFriend(friend1);

        assertTrue(user.getFriends().contains(friend1));
        assertFalse(user.getFriends().contains(friend2));
    }

    @Test
    public void testAddTheSameFriendTwiceDoNothing() {

        User friend1 = anyUser().withName("Pedro").build();
        User user = anyUser().withName("Pablo").build();

        user.addFriend(friend1);
        user.addFriend(friend1);

        assertEquals(user.getFriends().size(), 1);
    }

    @Test
    public void testRemoveFriendFromTheListIsOk() {

        User friend1 = anyUser().withName("Pedro").build();
        User friend2 = anyUser().withName("Carlos").build();
        List<User> friendList = new ArrayList<>();
        friendList.add(friend1);
        friendList.add(friend2);

        User user = anyUser().withName("Pablo").withFriends(friendList).build();

        user.removeFriend(friend1);

        assertEquals(user.getFriends().size(), 1);
        assertFalse(user.getFriends().contains(friend1));
        assertTrue(user.getFriends().contains(friend2));
    }

/*    @Test
    public void getFriendsProfile() throws Exception {

    }*/

}