package model.planning;

import model.outings.Outing;
import model.tags.Tag;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static model.builders.outings.OutingBuilder.anyOuting;
import static model.builders.outings.OutingEventBuilder.anOutingEvent;
import static model.builders.outings.OutingFilterBuilder.anOutingFilter;
import static model.builders.TagBuilder.anyTag;
import static org.junit.Assert.assertTrue;

public class OutingFilterTest {

    private LocalTime anyTime() {
        return LocalTime.now();
    }

    private LocalDate anyDate() {
        return LocalDate.now();
    }

    @Test
    public void outingFilterShouldMatchOkByTag() {
        Tag tag1 = anyTag().withName("Tag 1").build();
        Tag tag2 = anyTag().withName("Tag 2").build();
        Outing outing1 = anyOuting().withTag(tag1).build();
        Outing outing2 = anyOuting().withTag(tag2).build();

        OutingFilter filterTag1 = anOutingFilter().withSearchTag(tag1).build();
        assertTrue(filterTag1.match(outing1));
        assertFalse(filterTag1.match(outing2));
    }

    @Test
    public void outingFilterShouldMatchOkByMaxPrice() {
        Outing outing1 = anOutingEvent().withPrice(200).build();

        OutingFilter filterPrice1 = anOutingFilter().withMaxPrice(220).build();
        OutingFilter filterPrice2 = anOutingFilter().withMaxPrice(180).build();
        assertTrue(filterPrice1.match(outing1));
        assertFalse(filterPrice2.match(outing1));
    }

    @Test
    public void outingFilterShouldMatchOkByDate() {
        LocalDate date1 = anyDate();
        LocalDate date2 = date1.plusDays(1);
        Outing outing1 = anOutingEvent().withStartDateTime(date1.toLocalDateTime(anyTime())).build();

        OutingFilter filterDate1 = anOutingFilter().withDate(date1).build();
        OutingFilter filterDate2 = anOutingFilter().withDate(date2).build();
        assertTrue(filterDate1.match(outing1));
        assertFalse(filterDate2.match(outing1));
    }

}