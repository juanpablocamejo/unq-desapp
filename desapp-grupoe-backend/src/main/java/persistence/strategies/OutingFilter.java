package persistence.strategies;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class OutingFilter {
    private int idUser;
    private int assistants;
    private LocalDate date;
    private String strategy;

    public OutingFilter(int idUser, int assistants, LocalDate date, String strategy) {
        this.idUser = idUser;
        this.assistants = assistants;
        this.date = date;
        this.strategy = strategy;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getAssistants() {
        return assistants;
    }

    public void setAssistants(int assistants) {
        this.assistants = assistants;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

}