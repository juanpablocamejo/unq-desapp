package model.planning;

import model.users.Profile;

import java.util.List;

public interface IPlanningStrategy {
    List<IPlanningResult> search(Profile profile);
}
