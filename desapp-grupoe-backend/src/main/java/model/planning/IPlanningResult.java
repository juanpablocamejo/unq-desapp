package model.planning;

public interface IPlanningResult {
    double getPrice();

    boolean isEvent();

    boolean isPlace();

    boolean isPack();
}