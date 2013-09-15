package werewolf.dao;

import java.util.List;

import Exceptions.NoPlayerFoundException;

import edu.wm.something.domain.Player;

public interface PlayerDAO {
	
	List<Player> getAllAlive();
	
	void setDead(Player p);
	
	void insertPlayer(Player p);//insert player into database
	
	Player getPLayerById(String id) throws NoPlayerFoundException;
}
