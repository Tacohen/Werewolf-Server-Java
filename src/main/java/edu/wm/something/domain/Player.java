package edu.wm.something.domain;

public class Player {
	
	private String id;
	private boolean isDead;
	private double lat;
	private double lng;
	private int userID;
	private boolean isWereWolf;
	private boolean isAdmin;
	private int voteCount;
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public boolean isWereWolf() {
		return isWereWolf;
	}

	public void setWereWolf(boolean isWereWolf) {
		this.isWereWolf = isWereWolf;
	}

	public String getVotedAgainst() {
		return votedAgainst;
	}

	public void setVotedAgainst(String votedAgainst) {
		this.votedAgainst = votedAgainst;
	}

	private String votedAgainst;
	
	public Player(String id, boolean isDead, double lat, double lng, int userId, Boolean isWerewolf,int voteCount) {
		super();
		this.id = id;
		this.isDead = isDead;
		this.lat = lat;
		this.lng = lng;
		this.userID = userId;
		this.isWereWolf = isWerewolf;
		this.voteCount = voteCount;
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
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setWerewolf(boolean b) {
		this.isWereWolf = b;
		
	}
	
	public boolean isAdmin(Player player){
		return player.isAdmin;
	}
	
	public void makeAdmin(){
		this.isAdmin = true;
	}
	
	public void demoteAdmin(){
		this.isAdmin = false;
	}

	public boolean isNear(Player victim) {
		// TODO Auto-generated method stub
		return false;
	}

}