package Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.wm.something.HomeController;

public class NoPlayerFoundException extends Exception{
	
	private static final Logger logger = LoggerFactory.getLogger(NoPlayerFoundException.class);


	private static final long serialVersionUID = 1L;
	private String userID;
	
	public NoPlayerFoundException(String userId){
		super();
		logger.error("NO PLAYER FOUND!");
		this.userID = userId;
	}
	
	public String getUserID(){
		return userID;
	}
	
}
