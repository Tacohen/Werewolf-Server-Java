package edu.wm.something;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	JsonResponse jsonResponse = new JsonResponse();;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//@Autowired private IPlayerDAO playerDao;
	@Autowired private GameService gameService;
	@Autowired private PlayerService playerService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/players/alive", method = RequestMethod.GET)
	public  @ResponseBody List<Player> getAllAlive() throws NoPlayersException
	{
		List<Player> players = gameService.getAllAlive();
		return players;
		
	}
	@RequestMapping(value="/location",method=RequestMethod.POST)
	public @ResponseBody JsonResponse setlocation(@ModelAttribute GPSLocation location,Player player)
	{
		gameService.updatePosition(player,location);
		return jsonResponse;
	}
	
	@RequestMapping(value="/players/add/{playerName}",method=RequestMethod.POST)
	public @ResponseBody void addPlayer(@PathVariable("newPlayerId") String newPlayerId) {
		Player p = new Player();
		p.setId(newPlayerId);
		playerService.addplayer(p);
	}
	
	

	@RequestMapping(value = "/players/kill/{killerId}/{victimId}", method=RequestMethod.POST)
	public @ResponseBody boolean killPlayerById(@PathVariable("killerId") int killerId, @PathVariable("victimId") int victimId) throws NoPlayerFoundException, NoPlayersException
	{
		logger.info("killerId is:"+killerId);
		logger.info("victimId is:"+victimId);
		Player killer = gameService.getPlayerByID(killerId);
		Player victim = gameService.getPlayerByID(victimId);
		if (gameService.canKill(killer,victim)){
			gameService.Kill(victim);
			return true;
		}
		else{
			return false;
		}
	}

	
	@RequestMapping(value = "/players/vote/{voterId}/{voteId}", method=RequestMethod.DELETE)	
	public @ResponseBody void voteOnPlayer(@PathVariable int voterId, @PathVariable int voteId) {
		JsonResponse response = new JsonResponse();
		logger.info("voter is:"+voterId);
		logger.info("voted on:"+voteId);
		gameService.voteOnPlayer(voterId, voteId);
		//return response;
    }
	
	@RequestMapping(value = "/players/alive/{ownerId}", method = RequestMethod.GET)
	public  @ResponseBody Player getPlayerById(@PathVariable int ownerId)
	{
		Player players = gameService.getPlayerByID(ownerId);
		return players;
	}
	
	@RequestMapping(value = "/players/alive/{ownerId}/pic", method = RequestMethod.GET)
	public  @ResponseBody List<Player> getPicById(@PathVariable int ownerId)
	{
		List<Player> players = gameService.getPicByID(ownerId);
		return players;
		
	}
	
}
