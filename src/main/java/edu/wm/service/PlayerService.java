package edu.wm.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import werewolf.dao.IPlayerDAO;
import werewolf.dao.PostgresPlayerDAO;
import Exceptions.NoPlayerFoundException;
import Exceptions.NoPlayersException;

import edu.wm.something.domain.Player;
 
 
@Repository
public class PlayerService {
     
    @Autowired private static MongoTemplate mongoTemplate;
    //private static IPlayerDAO iPlayerDAO;
    //@Autowired private static MongoPlayerDAO mongoPlayerDAO;
    private static PostgresPlayerDAO postgresPlayerDao = new PostgresPlayerDAO();
     
    public static final String COLLECTION_NAME = "Players";  
    static Logger logger = Logger.getLogger(PlayerService.class.getName());

     
    public void addplayer(Player player) {
    	postgresPlayerDao.createPlayer(player);
    }
     
    public static List<Player> getAllAlive() throws NoPlayersException{
    	logger.info("In PlayerService.getAllPlayers()");
        //return mongoPlayerDAO.getAllPlayers();
    	//postgresDao.implementDatabase();
    	//return null;
    	return postgresPlayerDao.getAllAlive();
    }
     
    public void deletePlayer(Player player) throws NoPlayerFoundException {
    	postgresPlayerDao.deletePlayer(player);
    }
     
    public void updatePlayer(Player player) throws NoPlayerFoundException {
    	postgresPlayerDao.updatePlayer(player);
    }
    
    
    public Player getPlayerFromDbByID(Integer ownerId) throws NoPlayerFoundException{
    	return postgresPlayerDao.getPlayerById(ownerId);
    }

	public List<Player> getAllNear(Player p) {
		return postgresPlayerDao.getAllPlayersNear(p);
		
	}
	
	public void voteOnPlayer(Player p) throws NoPlayerFoundException{
		postgresPlayerDao.voteOnPlayer(p);
	}
    
    

    
}