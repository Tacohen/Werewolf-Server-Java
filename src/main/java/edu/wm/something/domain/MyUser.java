package edu.wm.something.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User{
	
	private static final long serialVersionUID = -3974317891273346210L;
	private static GrantedAuthority[] authorities;
	private static String password;
	private static String username;
	public String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		MyUser.username = username;
	}
	private String firstName;
	private String lastName;
	private String imageURL;
	private String hashedPassword;
	private Boolean isWerewolf;
	private int kills;
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	private int gamesPlayed;
	private int score;
	
	public Boolean getIsWerewolf() {
		return isWerewolf;
	}
	public void setIsWerewolf(Boolean isWerewolf) {
		this.isWerewolf = isWerewolf;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	@SuppressWarnings("deprecation")
	public MyUser(String username, String imageURL,
			String hashedPassword, Boolean isWerewolf) {
		super(username,password,true,true,true,true,authorities);
		this.imageURL = imageURL;
		this.hashedPassword = hashedPassword;
		this.isWerewolf = isWerewolf;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

}
