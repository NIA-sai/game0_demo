package online.zsgbp.demo.spring_boot.game_0_demo.service.gameservice;

import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.GuessNumber;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.RankGuess;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.GuessNumberPageVo;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.Result;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.gamemapper.GuessNumberMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.rankmapper.OtherRankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static online.zsgbp.demo.spring_boot.game_0_demo.util.CreateStuff.generateRandomNumber;
import static online.zsgbp.demo.spring_boot.game_0_demo.util.CreateStuff.string2LongByAscii;

@Service
public class GuessNumberService
{
	@Autowired
	private GuessNumberMapper guessNumberMapper;
	@Autowired
	private OtherRankMapper rankGuessMapper;
	
	public Result startGuess ( int usersId )
	{
		guessNumberMapper.deleteGuessNumberByUserId ( usersId );
		guessNumberMapper.addGuess ( new GuessNumber ( usersId , generateRandomNumber ( usersId ) , null , 0 , LocalDateTime.now () ) );
		return Result.success ( "i have generate a number and you can try to use /guess/{number} to guess  now" , null );
	}
	
	public Result tryGuess ( int usersId , int guess )
	{
		GuessNumber lastGuessNumber = guessNumberMapper.getGuessNumberByUserId ( usersId );
		if ( lastGuessNumber == null ) return Result.failure ( 401 , "you have not start the game" );
		int totalTimes = lastGuessNumber.getTimes ()+1;
		if ( lastGuessNumber.getAnswerNumber () == guess )
		{
			List < GuessNumber > result = guessNumberMapper.getAllGuessNumberByUserId ( usersId );
			guessNumberMapper.deleteGuessNumberByUserId ( usersId );
			if ( totalTimes > 11 )
			{
				Random random = new Random ( string2LongByAscii ( LocalDateTime.now ().toString () ) );
				int score = random.nextInt ( 2*totalTimes+1 )-totalTimes;
				RankGuess rankGuess = rankChange ( usersId , score );
				return Result.success ( "nothing or everything! ,you tried "+totalTimes+" times ,you win or loss "+score+" scores" , new GuessNumberPageVo ( result ).setRankGuess ( rankGuess ) );
			}
			else
			{
				int score = 11-totalTimes;
				RankGuess rankGuess = rankChange ( usersId , score );
				return Result.success ( "you tried "+totalTimes+" times ,you win "+score+" scores" , new GuessNumberPageVo ( result ).setRankGuess ( rankGuess ) );
			}
		}
		GuessNumber thisGuessNumber = new GuessNumber ( usersId , lastGuessNumber.getAnswerNumber () , guess , totalTimes , LocalDateTime.now () );
		guessNumberMapper.addGuess ( thisGuessNumber );
		if ( totalTimes != 1 && lastGuessNumber.getGuessNumber () == guess )
		{
			return Result.suspect ( 233 , "whatever" , thisGuessNumber.hideAnswerNumber () );
		}
		else if ( lastGuessNumber.getAnswerNumber () > guess )
		{
			if ( totalTimes == 1 ) return Result.suspect ( 555 , "your guess is less than the correct answer" , thisGuessNumber.hideAnswerNumber () );
			else if ( lastGuessNumber.getGuessNumber () > guess && lastGuessNumber.getGuessNumber () < thisGuessNumber.getGuessNumber () )
			{
				return Result.suspect ( 555 , "i have told you, it's too small" , thisGuessNumber.hideAnswerNumber () );
			}
			else
			{
				return Result.suspect ( 555 , "now the number you gave is less than answer" , thisGuessNumber.hideAnswerNumber () );
			}
		}
		else
		{
			if ( totalTimes == 1 ) return Result.suspect ( 555 , "your guess is bigger than the  apt answer" , thisGuessNumber.hideAnswerNumber () );
			else if ( lastGuessNumber.getGuessNumber () < guess && lastGuessNumber.getGuessNumber () > thisGuessNumber.getGuessNumber ())
			{
				return Result.suspect ( 555 , "i have told you it's too big" , thisGuessNumber.hideAnswerNumber () );
			}
			else
			{
				return Result.suspect ( 555 , "now the number you gave are bigger than answer" , thisGuessNumber.hideAnswerNumber () );
			}
		}
	}
	
	public Result giveUpGuess ( int usersId )
	{
		List < GuessNumber > result = guessNumberMapper.getAllGuessNumberByUserId ( usersId );
		guessNumberMapper.deleteGuessNumberByUserId ( usersId );
		if ( result.isEmpty () ) return Result.failure ( 401 , "you have not start the game" );
		else if ( result.size () <= 11 )
		{
			return Result.suspect ( 111 , "you tried"+( result.size ()-1 )+"times ,and then you give up ,loser~~ ,no score change" , result );
		}
		else
		{
			int score = ( 11-result.size () );
			RankGuess rankGuess = rankChange ( usersId , score );
			return Result.suspect ( 111 , "you tried"+( result.size ()-1 )+"times ,and then you give up ,loser~~ , score -"+score , new GuessNumberPageVo ( result ).setRankGuess ( rankGuess ) );
		}
	}
	
	public RankGuess rankChange ( int usersId , long delta )
	{
		RankGuess rankGuess = rankGuessMapper.getRankGuessByUsersId ( usersId );
		if ( rankGuess == null )
		{
			int rank = rankGuessMapper.getRankByScore ( delta );
			rankGuess = new RankGuess ( usersId , delta , rank );
			rankGuessMapper.addRank ( rankGuess );
			return rankGuess;
		}
		else
		{
			int rank = rankGuessMapper.getRankByScore ( rankGuess.getScore ()+delta );
			rankGuess.setScore ( rankGuess.getScore ()+delta );
			rankGuess.setRank ( rank );
			rankGuessMapper.updateRank ( rankGuess );
			return rankGuess;
		}
	}
}

