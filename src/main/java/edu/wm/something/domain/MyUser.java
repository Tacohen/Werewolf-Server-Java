package edu.wm.something.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User{
	
	private static final long serialVersionUID = -3974317891273346210L;
	private static GrantedAuthority[] authorities;
	private static String password;
	private static String username;
	private String firstName;
	private String lastName;
	private String imageURL;
	private String hashedPassword;
	private Boolean isWerewolf;
	
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
	public MyUser(String firstName, String lastName, String imageURL,
			String hashedPassword, Boolean isWerewolf) {
		super(username,password,true,true,true,true,authorities);
		this.firstName = firstName;
		this.lastName = lastName;
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
