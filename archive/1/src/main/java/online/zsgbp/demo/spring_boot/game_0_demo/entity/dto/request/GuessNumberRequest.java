package online.zsgbp.demo.spring_boot.game_0_demo.entity.dto.request;

public class GuessNumberRequest
{
	private Integer guessNumber=null;
	
	public Integer getGuessNumber ()
	{
		return guessNumber;
	}
	
	public void setGuessNumber ( Integer guessNumber )
	{
		this.guessNumber = guessNumber;
	}
}
