package model.planning;

import model.Application;
import model.users.Profile;
import org.joda.time.LocalDate;

import java.util.List;

public class CouplesStrategy extends BasicSearchStrategy implements IPlanningStrategy {

    @Override
    public List<IPlanningResult> search(Profile profile) {
        return Application.findOutings(
                searchFilter(profile, "couples")
                        .build()
        );
    }

    @Override
    public List<IPlanningResult> search(LocalDate date, Profile profile) {
        return Application.findOutings(
                searchFilter(profile, "couples")
                        .withDate(date)
                        .build()
        );
    }
}
