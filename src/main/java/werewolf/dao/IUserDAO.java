package werewolf.dao;

import java.util.List;

import Exceptions.NoPlayersException;

import edu.wm.something.domain.MyUser;
import edu.wm.something.domain.Player;

public interface IUserDAO {
	
	void createUser(MyUser user);
	
	void deleteUser(MyUser user);	
	
	
}