package repository;

import model.builders.OutingBuilder;
import model.builders.OutingEventBuilder;
import model.outings.Outing;
import model.planning.IPlanningResult;

import java.util.ArrayList;
import java.util.List;

public class OutingDAO implements IOutingRepository {

    private static List<IPlanningResult> outings;

    public OutingDAO() {
        outings = createOutings();
    }

    private List<IPlanningResult> createOutings() {
        List<IPlanningResult> outings = new ArrayList<>();
        outings.add(OutingEventBuilder.anOutingEvent().withId(10).withName("Salida1").build());
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
    public List<IPlanningResult> getOutings() {
        return outings;
    }

    @Override
    public IPlanningResult getOutingsByName(String name) {
        for (IPlanningResult ipr : outings) {
            if (!ipr.isPack()) {
                if (((Outing) ipr).getName() == name) {
                    return ipr;
                }
            }
        }
        return OutingBuilder.anyOuting().build();
    }

    @Override
    public IPlanningResult getOutingsById(int id) {
        for (IPlanningResult ipr : outings) {
            if (ipr.getId() == id) {
                return ipr;
            }
        }
        return null;
    }

}
