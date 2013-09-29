

public class User {
	
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
	public User(String firstName, String lastName, String imageURL,
			String hashedPassword, Boolean isWerewolf) {
		super();
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