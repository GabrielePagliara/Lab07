package it.polito.tdp.poweroutages.model;
import java.sql.Timestamp;
public class PowerOutages {
	
	private int year;
	private Timestamp date_event_began;
	private Timestamp time_event_finished;
	private int hour;
	private int customers;
	
	public PowerOutages(int year, Timestamp date_event_began, Timestamp time_event_finished, int hour, int customers) {
		super();
		this.year = year;
		this.date_event_began = date_event_began;
		this.time_event_finished = time_event_finished;
		this.hour = hour;
		this.customers = customers;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Timestamp getDate_event_began() {
		return date_event_began;
	}

	public void setDate_event_began(Timestamp date_event_began) {
		this.date_event_began = date_event_began;
	}

	public Timestamp getTime_event_finished() {
		return time_event_finished;
	}

	public void setTime_event_finished(Timestamp time_event_finished) {
		this.time_event_finished = time_event_finished;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getCustomers() {
		return customers;
	}

	public void setCustomers(int customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "" + year + " " + date_event_began + " "
				+ time_event_finished + " " + hour + " " + customers + " \n ";
	}
	
	
	
	
	

}
