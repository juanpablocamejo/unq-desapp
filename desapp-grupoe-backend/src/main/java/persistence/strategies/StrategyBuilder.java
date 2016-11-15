package persistence.strategies;

import persistence.HibernateGenericDAO;

public class StrategyBuilder {
    private HibernateGenericDAO dao;
    private String strategyName;
    private IPlanningStrategy strategy;

    public static StrategyBuilder anStrategy() {
        return new StrategyBuilder();
    }

    public StrategyBuilder withName(String name) {
        this.strategyName = name;
        return this;
    }

    public StrategyBuilder withDao(HibernateGenericDAO d) {
        this.dao = d;
        return this;
    }

    public IPlanningStrategy build() {
        switch (strategyName) {
            case "friends":
                strategy = new FriendsStrategy(dao);
                break;
            case "couples":
                strategy = new CouplesStrategy(dao);
                break;
            case "inexpensive":
                strategy = new InexpensiveStrategy(dao);
                break;
            case "surprise_me":
                strategy = new SurpriseStrategy(dao);
                break;
            case "sat_night_fever":
                strategy = new SaturdayNightFeverStrategy();
                break;
        }
        return strategy;
    }
}

