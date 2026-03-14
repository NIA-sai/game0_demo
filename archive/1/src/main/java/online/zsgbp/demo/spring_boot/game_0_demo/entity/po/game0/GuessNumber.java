package online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0;

import java.time.LocalDateTime;

public class GuessNumber
{
	private int usersId;
	private Integer answerNumber;
	private Integer guessNumber = null;
	private int times;
	private LocalDateTime tryTime = LocalDateTime.now ();
	
	public int getUsersId ()
	{
		return usersId;
	}
	
	public int getAnswerNumber ()
	{
		return answerNumber;
	}
	
	public Integer getGuessNumber ()
	{
		return guessNumber;
	}
	
	public int getTimes ()
	{
		return times;
	}
	
	public LocalDateTime getTryTime ()
	{
		return tryTime;
	}
	
	public GuessNumber ( int usersId , Integer answerNumber , Integer guessNumber , int times , LocalDateTime tryTime )
	{
		this.usersId = usersId;
		this.answerNumber = answerNumber;
		this.guessNumber = guessNumber;
		this.times = times;
		this.tryTime = tryTime;
	}
	
	public GuessNumberWithNoAnswer hideAnswerNumber ()
	{
		return new GuessNumberWithNoAnswer ( this.usersId , this.guessNumber , this.times );
	}
}
