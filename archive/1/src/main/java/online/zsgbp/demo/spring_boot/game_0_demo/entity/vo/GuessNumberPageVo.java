package online.zsgbp.demo.spring_boot.game_0_demo.entity.vo;

import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.GuessNumber;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.RankGuess;

import java.util.List;

public class GuessNumberPageVo
{
	private List < GuessNumber > guessNumbers;
	private RankGuess rankGuess;
	
	public GuessNumberPageVo ( List < GuessNumber > guessNumbers )
	{
		this.guessNumbers = guessNumbers;
	}
	
	public GuessNumberPageVo setRankGuess ( RankGuess rankGuess )
	{
		this.rankGuess = rankGuess;
		return this;
	}
	
	public List < GuessNumber > getGuessNumbers ()
	{
		return guessNumbers;
	}
	
	public void setGuessNumbers ( List < GuessNumber > guessNumbers )
	{
		this.guessNumbers = guessNumbers;
	}
	
	public RankGuess getRankGuess ()
	{
		return rankGuess;
	}
	
}
