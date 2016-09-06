package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ConcretOuting extends Outing {

	private String name;
	private LocalDate date;
	private LocalTime time;
	private ArrayList<OutingType> type;

	public ConcretOuting(String name, LocalDate date, LocalTime time, ArrayList<OutingType> type) {
		super();
		this.name = name;
		this.date = date;
		this.time = time;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public ArrayList<OutingType> getType() {
		return type;
	}

	public void setType(ArrayList<OutingType> type) {
		this.type = type;
	}

	public Boolean isType(OutingType type) {
		return getType().contains(type);
	}

}
