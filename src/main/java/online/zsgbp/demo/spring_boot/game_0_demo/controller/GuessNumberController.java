package online.zsgbp.demo.spring_boot.game_0_demo.controller;

import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.Result;
import online.zsgbp.demo.spring_boot.game_0_demo.filter.AuthFilter;
import online.zsgbp.demo.spring_boot.game_0_demo.service.gameservice.GuessNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guess")
public class GuessNumberController
{//i'm tired to write the auto-start-guess *filtered* stuff
	
	@Autowired
	private GuessNumberService guessNumberService;
	
	
	@GetMapping("/start")
	public Result start()
	{
		return guessNumberService.startGuess ( AuthFilter.idHolder.get () );
	}
	
	@GetMapping("/{number}")
	public Result guess ( @PathVariable("number") int number )
	{
		return guessNumberService.tryGuess ( AuthFilter.idHolder.get (), number );
	}
	@GetMapping("/giveup")
	public Result giveUp()
	{
		return guessNumberService.giveUpGuess ( AuthFilter.idHolder.get () );
	}
}
