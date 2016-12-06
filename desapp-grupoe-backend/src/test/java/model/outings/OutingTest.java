package model.outings;

import model.builders.outings.OutingBuilder;
import model.builders.outings.OutingEventBuilder;
import model.tags.Tag;
import model.users.User;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class OutingTest {
    @Test
    public void addANewTagToTheListOfTheOutingWorksOk() {
        Tag tag = Mockito.mock(Tag.class);
        Outing outing = OutingBuilder.anyOuting().build();

        assertEquals(0, outing.getTags().size());
        outing.addTag(tag);
        assertEquals(1, outing.getTags().size());
    }

    @Test
    public void addAnExistingTagToTheListOfTheOutingDoNothing() {
        Tag tag = Mockito.mock(Tag.class);
        Tag tag1 = Mockito.mock(Tag.class);
        when(tag.getId()).thenReturn(1);
        when(tag1.getId()).thenReturn(2);

        Outing outing = OutingBuilder.anyOuting().build();

        outing.addTag(tag);
        outing.addTag(tag1);
        outing.addTag(tag);
        assertEquals(2, outing.getTags().size());
    }

    @Test
    public void addAssistantaWorksOkIfTheUserIsNotListedYet() {
        User user = Mockito.mock(User.class);
        Outing outing = OutingBuilder.anyOuting().build();

        assertEquals(0, outing.getAssistants().size());
        outing.addAssistant(user);
        assertEquals(1, outing.getAssistants().size());

    }

    @Test
    public void removeAssistant() {
        User user = Mockito.mock(User.class);
        Outing outing = OutingBuilder.anyOuting().build();

        assertEquals(0, outing.getAssistants().size());
        outing.addAssistant(user);
        outing.removeAssistant(user);
        assertEquals(0, outing.getAssistants().size());
    }

    @Test
    public void matchWithTagListReturnsOKWhenATagMatchWithOtherInTheOtherList() {
        List<Tag> tags = new ArrayList<>();

        Tag tag1 = Mockito.mock(Tag.class);
        Tag tag2 = Mockito.mock(Tag.class);
        Tag tag3 = Mockito.mock(Tag.class);
        Tag tag4 = Mockito.mock(Tag.class);
        Tag tag5 = Mockito.mock(Tag.class);

        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tags.add(tag4);
        tags.add(tag5);

        Outing outing = OutingBuilder.anyOuting().withTag(tag1).withTag(tag2).build();

        assertTrue(outing.matchWith(tags));

    }

    @Test
    public void matchWithTagListFailsWhenCantMatchATagWithOtherInTheOtherList() {

        List<Tag> tags = new ArrayList<>();
        Tag tag1 = Mockito.mock(Tag.class);
        Tag tag2 = Mockito.mock(Tag.class);
        Tag tag3 = Mockito.mock(Tag.class);
        Tag tag4 = Mockito.mock(Tag.class);
        Tag tag5 = Mockito.mock(Tag.class);

        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tags.add(tag4);

        Outing oe = OutingBuilder.anyOuting().withTag(tag5).build();

        assertFalse(oe.matchWith(tags));

    }
}