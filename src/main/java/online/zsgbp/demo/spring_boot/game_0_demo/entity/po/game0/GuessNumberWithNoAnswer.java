package online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0;

import java.time.LocalDateTime;
import java.util.Random;

// stupid enough
public class GuessNumberWithNoAnswer
{
	private String message;
	private int usersId;
	private Integer guessNumber;
	private int times;
	private LocalDateTime tryTime = LocalDateTime.now ();
	private static final String[] messages = new String[] { "or nothing!!" , "keep trying !" , "nothing or every thing!" , "this is not mi mi world" , "take care!!" , "everything," , "you gambler!" , "nothing or everything" , "you gambler!" , "nothing," , "or every thing" };
	
	public GuessNumberWithNoAnswer ( int usersId , Integer guessNumber , int times )
	{
		this.usersId = usersId;
		this.guessNumber = guessNumber;
		this.times = times;
		if ( times < 10 ) this.message = "you can give up without reduce score in "+( 10-times )+" times";
		else if(times==10) this.message = "you can give up without reduce score now or ? nothing! or! every thing! ";
		else
		{
			Random random = new Random ();
			this.message = messages[random.nextInt ( 11 )];
		}
	}
	
	public String getMessage ()
	{
		return message;
	}
	
	public void setMessage ( String message )
	{
		this.message = message;
	}
	
	public int getUsersId ()
	{
		return usersId;
	}
	
	public void setUsersId ( int usersId )
	{
		this.usersId = usersId;
	}
	
	public Integer getGuessNumber ()
	{
		return guessNumber;
	}
	
	public void setGuessNumber ( Integer guessNumber )
	{
		this.guessNumber = guessNumber;
	}
	
	public int getTimes ()
	{
		return times;
	}
	
	public void setTimes ( int times )
	{
		this.times = times;
	}
	
	public LocalDateTime getTryTime ()
	{
		return tryTime;
	}
	
	public void setTryTime ( LocalDateTime tryTime )
	{
		this.tryTime = tryTime;
	}
}
