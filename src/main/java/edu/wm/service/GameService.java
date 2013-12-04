package edu.wm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

import org.springframework.scheduling.annotation.Scheduled;

import Exceptions.NoPlayerFoundException;
import Exceptions.NoPlayersException;
import werewolf.dao.PostgresPlayerDAO;
import werewolf.dao.PostgresUserDAO;

import edu.wm.something.domain.GPSLocation;
import edu.wm.something.domain.Player;
import edu.wm.something.domain.Players;

public class GameService {

		private PlayerService playerService =new PlayerService();
		//@Autowired private PlayerService playerService;
		private static PostgresPlayerDAO postgresPlayerDao = new PostgresPlayerDAO();
		//@Autowired private PostgresPlayerDAO postgresPlayerDao;
		//private static PostgresUserDAO postgresUserDao = new PostgresUserDAO();
		private boolean isNight;
		
		public boolean isNight() {
			return isNight;
		}

		public void setNight(boolean isNight) {
			this.isNight = isNight;
		}

		static Logger logger = Logger.getLogger(GameService.class.getName());

		
		public void updatePosition(Player player, GPSLocation location){
			player.setLat(location.getLat());
			player.setLng(location.getLng());
			logger.info("moving player, in game service");
			postgresPlayerDao.movePlayer(player, location.getLat(), location.getLng());
		}
		
		public List<Player> getAllAlive() throws NoPlayersException {
			logger.info("In gameSerice.getAllAlive()");
			return PlayerService.getAllAlive();

		}

		public List<Player> getAllPlayers() throws NoPlayersException {
			logger.info("In gameSerice.getAllPlayers()");
			return PlayerService.getAllPlayers();

		}

		
		public List<Player> getAllWerewolves() throws NoPlayersException {
			List<Player> playerList = getAllAlive();
			List<Player> werewolfList = (List<Player>) new ArrayList<Player>();
			int length = playerList.size();
			for (int i=0;i<length;i++){
				Player player = playerList.get(i);
				if (player.isWereWolf()){
					werewolfList.add(player);
				}
			}
			return werewolfList;
		}
		
		
		public List<Player> getAllNear(Player p) {
			return playerService.getAllNear(p);
		}
		
		public void Move(Player p, double lat,double lng) throws NoPlayersException, NoPlayerFoundException {
			p.setLat(lat);
			p.setLng(lng);
			postgresPlayerDao.movePlayer(p, lat, lng);
		}
		
		public void Update(Player p) throws NoPlayerFoundException {
			postgresPlayerDao.updatePlayer(p);
		}
		
		public List<Player> Kill(Player p, Player killer) throws NoPlayerFoundException, NoPlayersException {
			playerService.killPlayer(p, killer);
			return PlayerService.getAllAlive();
		}
		
		public List<Player> CreatePlayer(Player p) throws NoPlayersException {
			playerService.addplayer(p);
			return PlayerService.getAllAlive();
		}

		public Player getPlayerByID(double playerId) throws NoPlayerFoundException {
			//return playerService.getPlayerFromDbByID(ownerId);
			return postgresPlayerDao.getPlayerById(playerId);
		}
		
		public Player getPlayerByIDStr(String playerIdStr) throws NoPlayerFoundException {
			//return playerService.getPlayerFromDbByID(ownerId);
			//return postgresPlayerDao.getPlayerById(playerId);
			Player p = postgresPlayerDao.getPlayerById(playerIdStr);
			if (p==null){
				throw new NoPlayerFoundException(playerIdStr);
			}
			else{
				return p;
			}
		}

		public Player getPicByID(int ownerId) throws NoPlayerFoundException {
			return postgresPlayerDao.getPlayerPic(ownerId);
		}

		public Player playerInfoRequest(int ownerId) throws NoPlayerFoundException {
			return postgresPlayerDao.getPlayerInfo(ownerId);
		}

		public void voteOnPlayer(Player p) throws NoPlayerFoundException {
			postgresPlayerDao.voteOnPlayer(p);
		}
		
		public Boolean canKill(Player killer, Player victim){
			if ((isNight == true) && (killer.isWereWolf()) && (victim.getLat() <= (killer.getLat()+(1/690))) && (victim.getLat() >= (killer.getLat()-(1/690))) && (victim.getLng() <= (killer.getLng()+550)) && (victim.getLng() >= (killer.getLng()-550))){
				logger.info("Prepare to set dead");
				return true;
			}
			else{
				logger.info("Not set dead");
				logger.info("Nightime status: isNight = "+isNight);
				logger.info("is killer werewolf?"+killer.isWereWolf());
				logger.info("is victim near? "+((victim.getLat()==killer.getLat())));
				return false;
			}
		}
		
		@Scheduled(fixedDelay=900000)//Every 15 minutes
		public void checkGameOperation(){
			//check if all players have checked in recently
			logger.info("checking game operation...");
			isNight = !isNight;//flip day/night
		}
		
		public void restartGame() throws IOException{
			PostgresUserDAO postgresUserDao2 = new PostgresUserDAO();
			postgresUserDao2.restartGame();
		}
		
}
