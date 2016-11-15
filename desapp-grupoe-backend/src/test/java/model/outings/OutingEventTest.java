package model.outings;

import model.builders.TagBuilder;
import model.builders.outings.OutingEventBuilder;
import model.tags.Tag;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OutingEventTest {
    @Test
    public void matchWithTagListReturnsOKWhenATagMatchWithOtherInTheOtherList() {

        List<Tag> tags = new ArrayList<>();
        Tag tag1 = TagBuilder.anyTag().withName("Tag1").build();
        Tag tag2 = TagBuilder.anyTag().withName("Tag2").build();
        Tag tag3 = TagBuilder.anyTag().withName("Tag3").build();
        Tag tag4 = TagBuilder.anyTag().withName("Tag4").build();
        Tag tag5 = TagBuilder.anyTag().withName("Tag5").build();

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
        Tag tag1 = TagBuilder.anyTag().withName("Tag1").build();
        Tag tag2 = TagBuilder.anyTag().withName("Tag2").build();
        Tag tag3 = TagBuilder.anyTag().withName("Tag3").build();
        Tag tag4 = TagBuilder.anyTag().withName("Tag4").build();
        Tag tag5 = TagBuilder.anyTag().withName("Tag5").build();

        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tags.add(tag4);

        OutingEvent oe = OutingEventBuilder.anOutingEvent().withTag(tag5).build();

        assertFalse(oe.matchWith(tags));

    }

}