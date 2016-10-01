package repository;

import model.planning.IPlanningResult;

import java.util.List;
import java.util.Set;

public interface IOutingRepository {

    Integer getcount(String tag);

    Set<String> getTags();

    List<IPlanningResult> getOutings();

    IPlanningResult getOutingsByName(String name);

    IPlanningResult getOutingsById(int id);
}
