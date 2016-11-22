package model.outings;

import model.builders.outings.OutingBuilder;
import model.tags.Tag;
import model.users.User;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
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

}