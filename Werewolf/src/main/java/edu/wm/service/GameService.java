package edu.wm.service;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import werewolf.dao.IPlayerDAO;

import edu.wm.something.domain.Player;

public class GameService {

		@Autowired private IPlayerDAO playerDao;
		@Autowired private IPlayerDAO userDao;
		
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
		
}