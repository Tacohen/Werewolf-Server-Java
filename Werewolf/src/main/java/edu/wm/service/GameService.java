package edu.wm.service;

import java.util.ArrayList;
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
		@Autowired private PlayerService playerService;
		
		static Logger logger = Logger.getLogger(GameService.class.getName());

		
		public void updatePosition(Player player, GPSLocation location){
			player.setLat(location.getLat());
			player.setLng(location.getLng());
		}
		
		public List<Player> getAllAlive() {
			return PlayerService.getAllPlayers();
		}
		
		public List<Player> getAllWerewolves() {
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
		
		public List<Player> getPlayerByAttributes() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public List<Player> getAllNear(long lat,long lng) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public List<Player> Move(Player p, long lat,long lng) {
			p.setLat(lat);
			p.setLng(lng);
			return getAllAlive();
		}
		
		public List<Player> Update(Player p) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public List<Player> Kill(Player p) {
			playerService.deletePlayer(p);
			return PlayerService.getAllPlayers();
		}
		
		public List<Player> CreatePlayer(Player p) {
			playerService.addplayer(p);
			return PlayerService.getAllPlayers();
		}

		public Player getPlayerByID(int ownerId) {
			return playerService.getPlayerFromDbByID(ownerId);
		}

		public List<Player> getPicByID(int ownerId) {
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
		
		public Boolean canKill(Player killer, Player victim){
			if ((killer.isWereWolf())&&(killer.isNear(victim))){
				return true;
			}
			else{
				return false;
			}
		}
		/**
		@Scheduled(fixedDelay=5000)
		public void checkGameOperation(){
			//check if all players have checked in recently
			logger.info("checking game operation...");
		}*/
		
}
