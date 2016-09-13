package model.planning;

import model.users.Profile;
import org.joda.time.LocalDate;

import java.util.List;

public interface IPlanningStrategy {
    List<IPlanningResult> search(Profile profile);

    List<IPlanningResult> search(LocalDate date, Profile profile);
}
