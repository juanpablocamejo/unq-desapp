package persistence.strategies;

public interface IPlanningResult {
    int getId();

    double getPrice();

    boolean isEvent();

    boolean isPlace();

    boolean isPack();
}