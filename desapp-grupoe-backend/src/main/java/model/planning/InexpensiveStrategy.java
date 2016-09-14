package model.planning;

import model.Application;
import model.users.Profile;
import org.joda.time.LocalDate;

import java.util.List;

public class InexpensiveStrategy extends BasicSearchStrategy implements IPlanningStrategy {

    @Override
    public List<IPlanningResult> search(Profile profile) {
        return Application.findOutings(
                searchFilter(profile, profile.getInexpensiveOutingLimit())
                        .build()
        );
    }

    @Override
    public List<IPlanningResult> search(LocalDate date, Profile profile) {
        return Application.findOutings(
                searchFilter(profile, profile.getInexpensiveOutingLimit())
                        .withDate(date)
                        .build()
        );
    }
}
