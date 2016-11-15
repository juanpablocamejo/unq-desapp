package model.builders.outings;

import org.joda.time.LocalDate;
import persistence.strategies.OutingFilter;

public class OutingFilterBuilder {
    private int idUser = 1;
    private int assistants = 2;
    private LocalDate date = new LocalDate();
    private String strategy = "";

    public static OutingFilterBuilder anOutingFilter() {
        return new OutingFilterBuilder();
    }

    public OutingFilter build() {
        return new OutingFilter(idUser, assistants, date, strategy);
    }

    public OutingFilterBuilder withUserID(int id) {
        idUser = id;
        return this;
    }

    public OutingFilterBuilder withAssistants(int cant) {
        assistants = cant;
        return this;
    }

    public OutingFilterBuilder withDate(LocalDate d) {
        date = d;
        return this;
    }

    public OutingFilterBuilder withStrategy(String strategy) {
        this.strategy = strategy;
        return this;
    }


}