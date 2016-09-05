package model;

import java.util.Date;

public class ConcretOuting {

	private Date date;
	private OutingType type;
	private double price;

	public ConcretOuting(Date date, OutingType type, double price) {
		this.date = date;
		this.type = type;
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public OutingType getType() {
		return type;
	}

	public void setType(OutingType type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
