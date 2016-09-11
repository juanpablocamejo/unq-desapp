package model;

import junit.framework.TestCase;
import model.outings.OutingTag;
import model.users.Profile;
import org.junit.Test;

import static model.builders.TagBuilder.anyTag;

public class ProfileTest extends TestCase {

    @Test
    public void testAddANewTagToTheListWorksOK() {

        OutingTag tagMusic = anyTag().withId(1).withName("Music").build();
        OutingTag tagMovie = anyTag().withId(2).withName("Movie").build();

        Profile profile = new Profile();
        profile.addTag(tagMusic);

        assertTrue(profile.getTags().contains(tagMusic));
        assertFalse((profile.getTags().contains(tagMovie)));
    }

    @Test
    public void testRemoveAnExistingTagRemovesFromTheListOK() {

        OutingTag tagMusic = anyTag().withId(1).withName("Music").build();
        OutingTag tagMovie = anyTag().withId(2).withName("Movie").build();

        Profile profile = new Profile();
        profile.addTag(tagMusic);
        profile.addTag(tagMovie);

        profile.removeTag(tagMovie);

        assertFalse(profile.getTags().contains(tagMovie));
        assertTrue(profile.getTags().contains(tagMusic));

    }

}