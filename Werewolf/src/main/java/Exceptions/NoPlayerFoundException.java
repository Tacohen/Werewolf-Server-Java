package Exceptions;

public class NoPlayerFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	private String userID;
	
	public NoPlayerFoundException(String userId){
		super();
		this.userID = userId;
	}
	
	public String getUserID(){
		return userID;
	}
	
}
