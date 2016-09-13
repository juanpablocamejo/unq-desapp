package model.users;

import model.outings.OutingTag;
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

    @Test
    public void testMergeProfilesResultingProfileTagsIsCalculatedCorrectly() {

        OutingTag tag1 = anyTag().withId(1).withName("Musica").build();
        OutingTag tag2 = anyTag().withId(2).withName("Pelicula").build();
        OutingTag tag3 = anyTag().withId(3).withName("Comida").build();

        ArrayList<OutingTag> tagsP1 = new ArrayList<>();
        tagsP1.add(tag1);
        tagsP1.add(tag2);

        ArrayList<OutingTag> tagsP2 = new ArrayList<>();
        tagsP2.add(tag1);
        tagsP2.add(tag3);

        ArrayList<OutingTag> tagsP3 = new ArrayList<>();
        tagsP3.add(tag1);


        Profile profile1 = anyProfile().withTags(tagsP1).build();
        Profile profile2 = anyProfile().withTags(tagsP2).build();
        Profile profile3 = anyProfile().withTags(tagsP3).build();

        ArrayList<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);
        profiles.add(profile3);

        Profile profileToTest = Profile.mergeProfiles(profiles);

        assertTrue(profileToTest.getTags().contains(tag1));
        assertFalse(profileToTest.getTags().contains(tag2));
        assertFalse(profileToTest.getTags().contains(tag3));
    }
}