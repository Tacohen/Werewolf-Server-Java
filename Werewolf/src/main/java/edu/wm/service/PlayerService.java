package edu.wm.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import werewolf.dao.IPlayerDAO;
import Exceptions.NoPlayerFoundException;
import Exceptions.NoPlayersException;

import edu.wm.something.domain.Player;
 
 
@Repository
public class PlayerService {
     
    @Autowired
    private static MongoTemplate mongoTemplate;
    @Autowired private static IPlayerDAO iPlayerDAO;
     
    public static final String COLLECTION_NAME = "Players";  

     
    public void addplayer(Player player) {
        iPlayerDAO.createPlayer(player);
    }
     
    public static List<Player> getAllPlayers() throws NoPlayersException{
        return iPlayerDAO.getAllPlayers();
    }
     
    public void deletePlayer(Player player) throws NoPlayerFoundException {
        iPlayerDAO.deletePlayer(player);
    }
     
    public void updatePlayer(Player player) throws NoPlayerFoundException {
        iPlayerDAO.updatePlayer(player);
    }
    
    
    public Player getPlayerFromDbByID(Integer ownerId) throws NoPlayerFoundException{
    	return iPlayerDAO.getPlayerById(ownerId);
    }

	public List<Player> getAllNear(Player p) {
		return iPlayerDAO.getAllPlayersNear(p);
		
	}
	
	public void voteOnPlayer(Player p) throws NoPlayerFoundException{
		iPlayerDAO.voteOnPlayer(p);
	}
    
    

    
}
