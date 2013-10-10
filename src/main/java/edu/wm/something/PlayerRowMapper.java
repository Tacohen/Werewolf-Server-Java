package edu.wm.something;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.wm.something.domain.Player;

public class PlayerRowMapper implements RowMapper
{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Player p = new Player();
		p.setUserID(rs.getInt("PLAYER_ID"));
		p.setId(rs.getString("PLAYER_NAME"));
		p.setLat(rs.getDouble("LAT"));
		p.setLng(rs.getDouble("LNG"));
		p.setWereWolf(rs.getBoolean("IS_WEREWOLF"));
		p.setDead(rs.getBoolean("IS_DEAD"));
		p.setVoteCount(rs.getInt("NUM_VOTES_AGAINST"));
		p.setPicture(rs.getString("PLAYER_PIC"));
		return p;
	}
 
}