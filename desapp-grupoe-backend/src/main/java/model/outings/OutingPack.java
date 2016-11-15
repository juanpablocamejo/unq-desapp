package model.outings;

import persistence.strategies.IPlanningResult;

import java.util.ArrayList;
import java.util.List;

public class OutingPack implements IPlanningResult {

    private int id;
    private List<Outing> outings = new ArrayList<>();

    public OutingPack() {
    }


    public OutingPack(List<Outing> outings) {
        this.outings = outings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Outing> getOutings() {
        return outings;
    }

    public void setOutings(List<Outing> outings) {
        this.outings = outings;
    }


    @Override
    public double getPrice() {
        double finalPrice = 0;

        for (Outing o : outings) {
            finalPrice += o.getPrice();
        }
        return finalPrice;
    }

    @Override
    public boolean isEvent() {
        return false;
    }

    @Override
    public boolean isPack() {
        return true;
    }

    @Override
    public boolean isPlace() {
        return false;
    }

    public void addOuting(Outing o) {
        if (!outings.contains(o)) {
            outings.add(o);
        }
    }

    public void removeOuting(Outing o) {
        if (outings.contains(o)) {
            outings.remove(o);
        }
    }
}
