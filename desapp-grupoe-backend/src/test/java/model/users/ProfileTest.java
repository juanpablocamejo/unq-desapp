package model.users;

import model.tags.Tag;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Hashtable;

import static model.builders.ProfileBuilder.anyProfile;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProfileTest {

    @Test
    public void addANewTagToTheListWorksOK() {

        Tag tagMusic = mock(Tag.class);
        Tag tagMovie = mock(Tag.class);

        Profile profile = new Profile();
        profile.addTag(tagMusic);

        assertTrue(profile.getTags().contains(tagMusic));
        assertFalse((profile.getTags().contains(tagMovie)));
    }


    @Test
    public void removeAnExistingTagRemovesFromTheListOK() {

        Tag tagMusic = mock(Tag.class);
        Tag tagMovie = mock(Tag.class);
        when(tagMusic.getId()).thenReturn(1);
        when(tagMovie.getId()).thenReturn(2);

        Profile profile = new Profile();
        profile.addTag(tagMusic);
        profile.addTag(tagMovie);

        profile.removeTag(tagMovie);

        assertFalse(profile.getTags().contains(tagMovie));
        assertTrue(profile.getTags().contains(tagMusic));

    }

    @Test
    public void testMergeProfilesInexpensiveLimitAverageIsOK() {

        Profile p1 = mock(Profile.class);
        Profile p2 = mock(Profile.class);
        when(p1.getInexpensiveOutingLimit()).thenReturn(20.0);
        when(p2.getInexpensiveOutingLimit()).thenReturn(60.0);

        ArrayList<Profile> listProfiles = new ArrayList<>();
        listProfiles.add(p1);
        listProfiles.add(p2);

        Profile toTest = Profile.mergeProfiles(listProfiles);

        assertEquals(toTest.getInexpensiveOutingLimit(), 40.0, 0);

    }

    @Test
    public void testMergeProfilesResultingProfileTagsIsCalculatedCorrectly() {

        Tag musica = mock(Tag.class);
        Tag pelicula = mock(Tag.class);
        Tag comida = mock(Tag.class);

        ArrayList<Tag> tagsP1 = new ArrayList<>();
        tagsP1.add(musica);
        tagsP1.add(pelicula);

        ArrayList<Tag> tagsP2 = new ArrayList<>();
        tagsP2.add(musica);
        tagsP2.add(comida);

        ArrayList<Tag> tagsP3 = new ArrayList<>();
        tagsP3.add(musica);


        Profile profile1 = anyProfile().withTags(tagsP1).build();
        Profile profile2 = anyProfile().withTags(tagsP2).build();
        Profile profile3 = anyProfile().withTags(tagsP3).build();

        ArrayList<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);
        profiles.add(profile3);

        Profile profileToTest = Profile.mergeProfiles(profiles);

        assertTrue(profileToTest.getTags().contains(musica));
        assertFalse(profileToTest.getTags().contains(pelicula));
        assertFalse(profileToTest.getTags().contains(comida));
    }

    @Test
    public void testGetTagsOccurrenceCalculatesOK() {

        Tag musica = mock(Tag.class);
        Tag pelicula = mock(Tag.class);
        Tag comida = mock(Tag.class);

        ArrayList<Tag> tagsP1 = new ArrayList<>();
        tagsP1.add(musica);
        tagsP1.add(pelicula);

        ArrayList<Tag> tagsP2 = new ArrayList<>();
        tagsP2.add(musica);
        tagsP2.add(comida);

        ArrayList<Tag> tagsP3 = new ArrayList<>();
        tagsP3.add(musica);
        tagsP3.add(pelicula);

        Profile profile1 = anyProfile().withTags(tagsP1).build();
        Profile profile2 = anyProfile().withTags(tagsP2).build();
        Profile profile3 = anyProfile().withTags(tagsP3).build();

        ArrayList<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);
        profiles.add(profile3);

        Hashtable<Tag, Integer> hash = new Hashtable<>();
        hash.put(musica, 3);
        hash.put(pelicula, 2);
        hash.put(comida, 1);

        assertEquals(Profile.getTagsOccurrence(profiles), hash);

    }
}