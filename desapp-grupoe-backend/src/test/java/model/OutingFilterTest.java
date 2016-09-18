package model;

import model.outings.Outing;
import model.outings.OutingTag;
import model.planning.OutingFilter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static model.builders.OutingBuilder.anyOuting;
import static model.builders.OutingFilterBuilder.anOutingFilter;
import static model.builders.TagBuilder.anyTag;
import static org.junit.Assert.assertTrue;

public class OutingFilterTest {
    @Test
    public void outingFilterShouldMatchByTag() {
        OutingTag tag1 = anyTag().withId(1).build();
        OutingTag tag2 = anyTag().withId(2).build();
        List<Outing> outings = new ArrayList<>();
        Outing outing1 = anyOuting().withTag(tag1).build();
        Outing outing2 = anyOuting().withTag(tag2).build();

        OutingFilter filterTag1 = anOutingFilter().withSearchTag(tag1).build();
        assertTrue(filterTag1.match(outing1));
        assertFalse(filterTag1.match(outing2));
    }

}