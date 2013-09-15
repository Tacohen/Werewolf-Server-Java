package edu.wm.something.domain;

import java.util.Date;

public class Game {
	
	private Date createdDate;
	private int dayNightFreq;
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getDayNightFreq() {
		return dayNightFreq;
	}
	public void setDayNightFreq(int dayNightFreq) {
		this.dayNightFreq = dayNightFreq;
	}
	public Game(Date createdDate, int dayNightFreq) {
		super();
		this.createdDate = createdDate;
		this.dayNightFreq = dayNightFreq;
	}
	

}
