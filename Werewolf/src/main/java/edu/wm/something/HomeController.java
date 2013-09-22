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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

//import werewolf.dao.IPlayerDAO;

import edu.wm.service.GameService;
import edu.wm.something.domain.Player;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	int id;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//@Autowired private IPlayerDAO playerDao;
	@Autowired private GameService gameService;
	
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
	public  @ResponseBody List<Player> getAllAlive()
	{
		List<Player> players = gameService.getAllAlive();
		return players;
		
	}
	
	@RequestMapping(value = "/players/alive/{ownerId}", method = RequestMethod.POST)
	
	public String processSubmit(
            @ModelAttribute("pet") String pet,
            BindingResult result, SessionStatus status) {
		
			return ("okay");
	}
	/**
	}
	public  @ResponseBody List<Player> killPlayerRequest(@PathVariable String ownerId)
	{
		List<Player> players = gameService.killPlayerRequest(ownerId);
		return players;
		
	}*/
	
	@RequestMapping(value = "/players/alive/{ownerId}", method = RequestMethod.GET)
	public  @ResponseBody List<Player> getPlayerById(@PathVariable String ownerId)
	{
		List<Player> players = gameService.getPlayerByID(ownerId);
		return players;
		
	}
	
	@RequestMapping(value = "/players/alive/{ownerId}/pic", method = RequestMethod.GET)
	public  @ResponseBody List<Player> getPicById(@PathVariable String ownerId)
	{
		List<Player> players = gameService.getPicByID(ownerId);
		return players;
		
	}
	
}
