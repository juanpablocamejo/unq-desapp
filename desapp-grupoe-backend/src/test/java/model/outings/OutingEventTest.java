package model.outings;

import model.builders.outings.OutingEventBuilder;
import model.tags.Tag;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OutingEventTest {
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

        OutingEvent oe = OutingEventBuilder.anOutingEvent().withTag(tag1).withTag(tag2).build();

        assertTrue(oe.matchWith(tags));

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

        OutingEvent oe = OutingEventBuilder.anOutingEvent().withTag(tag5).build();

        assertFalse(oe.matchWith(tags));

    }
}