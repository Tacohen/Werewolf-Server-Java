package werewolf.dao;

import java.io.IOException;
import java.util.List;

import Exceptions.NoPlayersException;

import edu.wm.something.domain.MyUser;
import edu.wm.something.domain.Player;

public interface IUserDAO {
	
	void createUser(MyUser user) throws IOException;
	
	void deleteUser(MyUser user);	
	
	void restartGame() throws IOException;
	
	void login(String username, String password);
	
}
