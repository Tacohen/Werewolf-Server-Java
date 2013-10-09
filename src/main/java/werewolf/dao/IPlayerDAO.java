package werewolf.dao;

import java.util.List;

import Exceptions.NoPlayerFoundException;
import Exceptions.NoPlayersException;

import edu.wm.something.domain.GPSLocation;
import edu.wm.something.domain.Player;

public interface IPlayerDAO {
	
	public List<Player> getAllAlive();
	
	public void setDead(Player p);
	
	void insertPlayer(Player p);//insert player into database
	
	public Player getPlayerById(String id) throws NoPlayerFoundException;

	void setPlayerLocation(String id, GPSLocation loc);
	
	List<Player> getAllPlayers() throws NoPlayersException;
	
	public void deletePlayer(Player p) throws NoPlayerFoundException;
	
	public void updatePlayer(Player p) throws NoPlayerFoundException;
	
	public Player getPlayerById(int id) throws NoPlayerFoundException;
	
	public void voteOnPlayer(Player p) throws NoPlayerFoundException;
	
	public Player getPlayerPic(int id) throws NoPlayerFoundException;
	
	public Player getPlayerInfo(int id) throws NoPlayerFoundException;
	
	public void createPlayer(Player p);
	
	public void killPlayer(Player p) throws NoPlayerFoundException;
	
	public List<Player> getAllPlayersNear(Player p);
	
	public void movePlayer(Player p, double lat, double lng);
}
