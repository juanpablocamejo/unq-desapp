package model;

import model.outings.OutingTag;
import model.users.Profile;
import org.junit.Test;

import java.util.ArrayList;

import static model.builders.ProfileBuilder.anyProfile;
import static model.builders.TagBuilder.anyTag;
import static org.junit.Assert.*;

public class ProfileTest {

    @Test
    public void addANewTagToTheListWorksOK() {

        OutingTag tagMusic = anyTag().withId(1).withName("Music").build();
        OutingTag tagMovie = anyTag().withId(2).withName("Movie").build();

        Profile profile = new Profile();
        profile.addTag(tagMusic);

        assertTrue(profile.getTags().contains(tagMusic));
        assertFalse((profile.getTags().contains(tagMovie)));
    }


    @Test
    public void removeAnExistingTagRemovesFromTheListOK() {

        OutingTag tagMusic = anyTag().withId(1).withName("Music").build();
        OutingTag tagMovie = anyTag().withId(2).withName("Movie").build();

        Profile profile = new Profile();
        profile.addTag(tagMusic);
        profile.addTag(tagMovie);

        profile.removeTag(tagMovie);

        assertFalse(profile.getTags().contains(tagMovie));
        assertTrue(profile.getTags().contains(tagMusic));

    }

    @Test
    public void testMergeProfilesInexpensiveLimitAverageIsOK() {

        Profile p1 = anyProfile().withInexpensiveOutingLimit(20).build();
        Profile p2 = anyProfile().withInexpensiveOutingLimit(60).build();
        ArrayList<Profile> listProfiles = new ArrayList<>();
        listProfiles.add(p1);
        listProfiles.add(p2);

        Profile toTest = Profile.mergeProfiles(listProfiles);

        assertEquals(toTest.getInexpensiveOutingLimit(), 40.0, 0);

    }


}