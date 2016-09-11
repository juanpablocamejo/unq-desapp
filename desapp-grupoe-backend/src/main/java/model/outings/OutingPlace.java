package model.outings;

import model.planning.IPlanningResult;
import model.time.AttentionSchedule;

import java.util.List;

public class OutingPlace extends Outing implements IPlanningResult {
    private AttentionSchedule attentionSchedule;

    protected OutingPlace(String name, String description, List<OutingTag> tags, double price, AttentionSchedule attentionSchedule) {
        super(name, description, tags, price);
        this.attentionSchedule = attentionSchedule;
    }

    @Override
    public boolean isEvent() {
        return false;
    }

    @Override
    public boolean isPack() {
        return false;
    }

    @Override
    public boolean isPlace() {
        return true;
    }
}
