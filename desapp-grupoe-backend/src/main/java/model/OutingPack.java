package model;

import java.util.ArrayList;
import java.util.List;

public class OutingPack implements IOuting {

    private List<Outing> outings = new ArrayList<>();


    public OutingPack() {
    }

    public OutingPack(List<Outing> outings) {
        this.outings = outings;
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
