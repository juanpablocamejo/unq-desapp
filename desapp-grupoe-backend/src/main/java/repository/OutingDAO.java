package repository;

import model.builders.OutingEventBuilder;
import model.outings.Outing;
import model.planning.IPlanningResult;

import java.util.ArrayList;
import java.util.List;

public class OutingDAO implements IOutingRepository {

    private static OutingDAO instance;
    private List<IPlanningResult> outings;

    private OutingDAO() {

        outings = initializeOutings();

    }

    public static OutingDAO getInstance() {
        if (instance == null) {
            instance = new OutingDAO();
        }
        return instance;
    }

    private List<IPlanningResult> initializeOutings() {

        List<IPlanningResult> outings = new ArrayList<>();
        outings.add(OutingEventBuilder.anOutingEvent().withId(10).withName("salida").build());
        outings.add(OutingEventBuilder.anOutingEvent().withId(11).withName("boliche").build());
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
            if (ipr.isEvent() || ipr.isPlace()) {
                if (((Outing) ipr).getName().equals(name)) {
                    return ipr;
                }
            }
        }
        return null;
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
