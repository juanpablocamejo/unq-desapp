package model.planning;

import model.Application;
import model.builders.OutingFilterBuilder;
import model.outings.OutingTag;
import model.users.Profile;
import org.joda.time.LocalDate;

import java.util.List;

public class CouplesStrategy implements IPlanningStrategy {

    private OutingFilterBuilder couplesSearchFilter(Profile profile) {
        OutingTag searchTag = new OutingTag();
        searchTag.setName("couples");
        return OutingFilterBuilder.anOutingFilter()
                .withSearchTag(searchTag)
                .withProfileTags(profile.getTags());
    }

    @Override
    public List<IPlanningResult> search(Profile profile) {
        return Application.findOutings(
                couplesSearchFilter(profile)
                        .build()
        );
    }

    @Override
    public List<IPlanningResult> search(LocalDate date, Profile profile) {
        return Application.findOutings(
                couplesSearchFilter(profile)
                        .withDate(date)
                        .build()
        );
    }
}
