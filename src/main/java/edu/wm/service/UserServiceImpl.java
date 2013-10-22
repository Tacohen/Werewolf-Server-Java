package edu.wm.service;



import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.wm.something.domain.MyUser;

public class UserServiceImpl implements UserDetailsService{
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public MyUser createUser(String username, String password){
		String hashedPass = passwordEncoder.encodePassword(password, "INREOUHG984N5V9V98N54W8");
		Random rand = new Random(); 
		int value = rand.nextInt(5);
		Boolean isWerewolf = false;
		if (value == 0){//20% chance of being a werewolf
			isWerewolf = true;
		}
		
		MyUser newUser = new MyUser(username, hashedPass, "pictureurl", isWerewolf);
		return newUser;
	}

}
