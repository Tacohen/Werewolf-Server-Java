package edu.wm.something;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import Exceptions.NoPlayerFoundException;
import Exceptions.NoPlayersException;

//import werewolf.dao.IPlayerDAO;

import edu.wm.service.GameService;
import edu.wm.service.PlayerService;
import edu.wm.something.domain.GPSLocation;
import edu.wm.something.domain.Player;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	int id;
	JsonResponse jsonResponse = new JsonResponse();
	Player genericPlayer;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	// @Autowired private IPlayerDAO playerDao;
	@Autowired
	private GameService gameService;
	@Autowired
	private PlayerService playerService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/players/alive", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject getAllAlive() throws NoPlayersException {
		logger.info("In players/alive!");
		List<Player> players = gameService.getAllAlive();
		JSONObject j = new JSONObject();

		for (int i = 0; i < players.size(); i++) {
			j.put(players.get(i).getId(), players.get(i));
		}

		return j;
	}

	@RequestMapping(value = "/players/location", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse setlocation(
			@RequestParam(value = "playerId", required = true) String playerId,
			@RequestParam(value = "lat", required = true) Long lat,
			@RequestParam(value = "lng", required = true) Long lng) {
		GPSLocation location = new GPSLocation();
		location.setLat(Math.abs(lat));
		location.setLng(Math.abs(lng));
		logger.info("moving player, in home controller");
		logger.info("lat for moving player is: " + Math.abs(lat));
		logger.info("lng for moving player is: " + Math.abs(lng));
		try {
			gameService.updatePosition(gameService.getPlayerByIDStr(playerId),
					location);
		} catch (NoPlayerFoundException e) {
			e.printStackTrace();
		}
		return jsonResponse;
	}

	@RequestMapping(value = "/players/add", method = RequestMethod.POST)
	public @ResponseBody
	void addPlayer(
			@RequestParam(value = "playerId", required = true) String playerId,
			@RequestParam(value = "lat", required = true) Long lat,
			@RequestParam(value = "lng", required = true) Long lng,
			@RequestParam(value = "isWerewolf", required = true) boolean isWerewolf) {
		Random random = new Random();
		Player p = new Player();
		p.setId(playerId);
		p.setKills(0);
		p.setDead(false);
		p.setWerewolf(isWerewolf);
		p.setLat(Math.abs(lat));
		p.setLng(Math.abs(lng));
		p.setUserID(random.nextInt());
		p.setPicture("none");
		logger.info("Started to add player, in home controller now");
		playerService.addplayer(p);

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/players/kill", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject killPlayerById(
			@RequestParam(value = "killerId", required = true) String killerIdStr,
			@RequestParam(value = "victimId", required = true) String victimIdStr)
			throws NoPlayerFoundException, NoPlayersException {
		System.out.println("About to kill!");
		logger.info("killerId is:" + killerIdStr);
		logger.info("victimId is:" + victimIdStr);
		Player killer = gameService.getPlayerByIDStr(killerIdStr);
		Player victim = gameService.getPlayerByIDStr(victimIdStr);

		if (gameService.canKill(killer, victim)) {
			gameService.Kill(victim, killer);
			JSONObject json = new JSONObject();
			json.put("isDead", true);
			return json;
		} else {
			// gameService.Kill(victim, killer);
			JSONObject json = new JSONObject();
			json.put("isDead", false);
			return json;
		}
	}

	@RequestMapping(value = "/players/vote", method = RequestMethod.POST)
	public @ResponseBody
	void voteOnPlayer(
			@RequestParam(value = "voterId", required = true) String voterId,
			@RequestParam(value = "voteId", required = true) String voteId)
			throws NoPlayerFoundException {
		JsonResponse response = new JsonResponse();
		logger.info("voter is:" + voterId);
		gameService.voteOnPlayer(gameService.getPlayerByIDStr(voteId));
		// return response;
	}

	@RequestMapping(value = "/players/id", method = RequestMethod.GET)
	public @ResponseBody
	Player getPlayerById(
			@RequestParam(value = "ownerId", required = true) String ownerId)
			throws NoPlayerFoundException {
		Player players = gameService.getPlayerByIDStr(ownerId);
		return players;
	}

	@RequestMapping(value = "/players/alive/{ownerId}/pic", method = RequestMethod.GET)
	public @ResponseBody
	Player getPicById(@PathVariable int ownerId) throws NoPlayerFoundException {
		Player player = gameService.getPicByID(ownerId);
		return player;

	}

	@RequestMapping(value = "/admin/restartGame", method = RequestMethod.POST)
	public @ResponseBody
	void restartGame() {
		try {
			gameService.restartGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/players/isnight", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject isNight() {
		if (gameService.isNight()) {
			JSONObject json = new JSONObject();
			json.put("isNight", true);
			return json;
		} else {
			JSONObject json = new JSONObject();
			json.put("isNight", false);
			return json;
		}
	}

	@RequestMapping(value = "/admin/setnight", method = RequestMethod.POST)
	public @ResponseBody
	void setNight() {
		gameService.setNight(true);
		gameService.hang();//for demonstration purposes
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject logint(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "lat", required = true) double lat,
			@RequestParam(value = "lng", required = true) double lng) {
		try {
			Player p = gameService.getPlayerByIDStr(username);
			p.setLat(Math.abs(lat));
			p.setLat(Math.abs(lng));
			gameService.Move(p, Math.abs(lat), Math.abs(lng));
			JSONObject json = new JSONObject();
			json.put("userExists", true);
			return json;// User is in database
		} catch (NoPlayerFoundException | NoPlayersException e) {
			JSONObject json = new JSONObject();
			json.put("userExists", false);
			return json;// failed to find player in database
		}

	}

	@RequestMapping(value = "/admin/setday", method = RequestMethod.POST)
	public @ResponseBody
	void setDay() {
		gameService.setNight(false);
	}

	// @SuppressWarnings("unchecked")
	@RequestMapping(value = "/users/register", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject register(@RequestParam(value = "username", required = true) String username,@RequestParam(value = "password", required = true) String password,@RequestParam(value = "lat", required = true) double lat,@RequestParam(value = "lng", required = true) double lng) {
		Player p;

		Random random = new Random();
		Random randomWerewolf = new Random(4);
		boolean isWerewolf = false;
		int isWerewolfSource = randomWerewolf.nextInt();
		if (isWerewolfSource == 3) {
			isWerewolf = true;
		}
		try {
			p = gameService.getPlayerByIDStr(username);
			JSONObject json = new JSONObject();
			json.put("addedUser", false);
			return json;// Already logged in
		} catch (NoPlayerFoundException e) {
			p = new Player(username, false, Math.abs(lat), Math.abs(lng),
					random.nextInt(), isWerewolf, 0, 0);
			logger.info("Started to add player, in home controller now");
			playerService.addplayer(p);
			JSONObject json = new JSONObject();
			json.put("addedUser", true);
			return json;// added player sucessfully
		}

	}

	@RequestMapping(value = "/players/cankill", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject canKill(@RequestParam(value = "username", required = true) String username,@RequestParam(value = "targetName", required = true) String targetName) throws NoPlayerFoundException, NoPlayersException {
		Player killer = gameService.getPlayerByIDStr(username);
		Player victim = gameService.getPlayerByIDStr(targetName);

		if (gameService.canKill(killer, victim)) {
			logger.info("Can kill");
			JSONObject json = new JSONObject();
			json.put("canKill", true);
			return json;
		} else {
			logger.info("cannot kill");
			JSONObject json = new JSONObject();
			json.put("canKill", false);
			return json;
		}
	}

	@RequestMapping(value = "/players/getNear", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject getNear(@RequestParam(value = "username", required = true) String username) throws NoPlayerFoundException, NoPlayersException {
		Player killer = gameService.getPlayerByIDStr(username);

		List<Player> playersNear = gameService.getAllNear(killer);
		JSONObject json = new JSONObject();

		for (int i = 0; i < playersNear.size(); i++) {
			json.put(playersNear.get(i).getId(), "Near");
		}

		return json;
	}

	@RequestMapping(value = "/players/type", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject whatType(
			@RequestParam(value = "username", required = true) String username)
			throws NoPlayerFoundException {
		Player player = gameService.getPlayerByIDStr(username);
		if (player.isWereWolf()) {
			JSONObject json = new JSONObject();
			json.put("isWerewolf", true);
			return json;
		} else {
			JSONObject json = new JSONObject();
			json.put("isWerewolf", false);
			return json;
		}
	}

	@RequestMapping(value = "/players/kills", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject kills(
			@RequestParam(value = "username", required = true) String username)
			throws NoPlayerFoundException {
		Player player = gameService.getPlayerByIDStr(username);
		JSONObject json = new JSONObject();
		json.put("kills", player.getKills());
		return json;
	}

}
