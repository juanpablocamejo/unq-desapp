package repository;

import model.builders.OutingEventBuilder;
import model.planning.IPlanningResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OutingDAO implements IOutingRepository {

    private static List<IPlanningResult> outings;

    public OutingDAO() {
        outings = createOutings();
    }

    private List<IPlanningResult> createOutings() {
        List<IPlanningResult> outings = new ArrayList<>();
        outings.add(OutingEventBuilder.anOutingEvent().withId(10).build());
        outings.add(OutingEventBuilder.anOutingEvent().withId(11).build());
        outings.add(OutingEventBuilder.anOutingEvent().withId(12).build());
        outings.add(OutingEventBuilder.anOutingEvent().withId(13).build());
        outings.add(OutingEventBuilder.anOutingEvent().withId(14).build());

        return outings;
    }

    @Override
    public Integer getcount(String tag) {
        return null;
    }

    @Override
    public Set<String> getTags() {
        return null;
    }

    @Override
    public List<IPlanningResult> getOutings() {
        return outings;
    }

    @Override
    public IPlanningResult getOutingsByName(String name) {
        return null;
    }

    @Override
    public IPlanningResult getOutingsById(int id) {
        return OutingEventBuilder.anOutingEvent().withId(id).build();
    }
}
