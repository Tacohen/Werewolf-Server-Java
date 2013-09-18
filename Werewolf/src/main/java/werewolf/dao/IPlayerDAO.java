package werewolf.dao;

import java.util.List;

import Exceptions.NoPlayerFoundException;

import edu.wm.something.domain.Player;

public interface IPlayerDAO {
	
	public List<Player> getAllAlive();
	
	public void setDead(Player p);
	
	void insertPlayer(Player p);//insert player into database
	
	public Player getPlayerById(String id) throws NoPlayerFoundException;
}
