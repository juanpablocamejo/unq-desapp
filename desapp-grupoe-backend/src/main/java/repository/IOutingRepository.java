package repository;

import model.planning.IPlanningResult;

import java.util.List;

public interface IOutingRepository {

    Integer getcount(String tag);

    List<IPlanningResult> getOutings();

    IPlanningResult getOutingsByName(String name);

    IPlanningResult getOutingsById(int id);
}

