package edu.wm.something.domain;

public class Player {
	
	private String id;
	private boolean isDead;
	private double lat;
	private double lng;
	private String userID;
	private boolean isWereWolf;
	private String votedAgainst;
	
	public Player(String id, boolean isDead, double lat, double lng, String userID, Boolean isWerewolf) {
		super();
		this.id = id;
		this.isDead = isDead;
		this.lat = lat;
		this.lng = lng;
		this.userID = userID;
		this.isWereWolf = isWerewolf;
		
	}
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setWerewolf(boolean b) {
		this.isWereWolf = b;
		
	}

}
