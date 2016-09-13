package model.planning;

import model.Application;
import model.users.Profile;
import org.joda.time.LocalDate;

import java.util.List;

public class FriendsStrategy extends BasicSearchStrategy implements IPlanningStrategy {

    @Override
    public List<IPlanningResult> search(Profile profile) {
        return Application.findOutings(
                searchFilter(profile, "friends")
                        .build()
        );
    }

    @Override
    public List<IPlanningResult> search(LocalDate date, Profile profile) {
        return Application.findOutings(
                searchFilter(profile, "friends")
                        .withDate(date)
                        .build()
        );
    }
}
