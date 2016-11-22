package model.users;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static model.builders.UserBuilder.anyUser;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserTest {

    @Test
    public void testAddFriendToTheListAddsCorrectly() {

        User friend1 = anyUser().build();
        User friend2 = anyUser().build();

        friend1.setId(1);
        friend2.setId(2);

        User user = anyUser().build();

        user.addFriend(friend1);

        assertTrue(user.getFriends().contains(friend1));
        assertFalse(user.getFriends().contains(friend2));
    }

    @Test
    public void testAddTheSameFriendTwiceDoNothing() {

        User friend1 = anyUser().build();
        User user = anyUser().build();

        friend1.setId(1);

        user.addFriend(friend1);
        user.addFriend(friend1);

        assertEquals(user.getFriends().size(), 1);
    }

    @Test
    public void testAddTheUserItselfToFriendsDoNothing() {

        User user = Mockito.mock(User.class);
        when(user.getId()).thenReturn(1);

        user.addFriend(user);

        assertEquals(user.getFriends().size(), 0);
    }

    @Test
    public void testRemoveFriendFromTheListIsOk() {

        User friend1 = Mockito.mock(User.class);
        User friend2 = Mockito.mock(User.class);
        when(friend1.getId()).thenReturn(1);
        when(friend2.getId()).thenReturn(2);

        List<User> friendList = new ArrayList<>();
        friendList.add(friend1);
        friendList.add(friend2);

        User user = anyUser().withFriends(friendList).build();
        user.removeFriend(friend1);

        assertEquals(user.getFriends().size(), 1);
        assertFalse(user.getFriends().contains(friend1));
        assertTrue(user.getFriends().contains(friend2));
    }
}