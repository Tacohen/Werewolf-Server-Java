package edu.wm.service;

import java.util.List;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import werewolf.dao.IPlayerDAO;

import edu.wm.something.domain.GPSLocation;
import edu.wm.something.domain.Player;

public class GameService {

		@Autowired private IPlayerDAO playerDao;
		@Autowired private IPlayerDAO userDao;
		
		static Logger logger = Logger.getLogger(GameService.class.getName());

		
		public void updatePosition(String userName, GPSLocation location){
			
			
		}
		
		public List<Player> getAllAlive() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public List<Player> getAllWerewolves() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public List<Player> getPlayerByAttributes() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public List<Player> getAllNear(long lat,long lng) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public List<Player> Move(Player p, long lat,long lng) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public List<Player> Update(Player p) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public List<Player> Kill(Player p) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public List<Player> CreatePlayer(Player p) {
			// TODO Auto-generated method stub
			return null;
		}

		public Player getPlayerByID(int ownerId) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<Player> getPicByID(int ownerId) {
			// TODO Auto-generated method stub
			return null;
		}

		public Boolean killPlayerRequest(int killerId, int victimId) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<Player> playerInfoRequest(int ownerId) {
			// TODO Auto-generated method stub
			return null;
		}

		public Boolean voteOnPlayer(int voterId, int voteId) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public Boolean canKill(Player Killer, Player Victim){
			return true;
		}
		/**
		@Scheduled(fixedDelay=5000)
		public void checkGameOperation(){
			//check if all players have checked in recently
			logger.info("checking game operation...");
		}*/
		
}
