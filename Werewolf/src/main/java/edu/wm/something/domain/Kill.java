package edu.wm.something.domain;

import java.util.Date;

public class Kill {
	
	private String killerID;
	private String victimID;
	private Date timestamp;
	private float lat;
	private float lng;
	public String getKillerID() {
		return killerID;
	}
	public void setKillerID(String killerID) {
		this.killerID = killerID;
	}
	public String getVictimID() {
		return victimID;
	}
	public void setVictimID(String victimID) {
		this.victimID = victimID;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public Kill(String killerID, String victimID, Date timestamp, float lat,
			float lng) {
		super();
		this.killerID = killerID;
		this.victimID = victimID;
		this.timestamp = timestamp;
		this.lat = lat;
		this.lng = lng;
	}
	

}
