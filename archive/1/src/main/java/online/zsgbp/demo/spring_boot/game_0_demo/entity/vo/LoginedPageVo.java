package online.zsgbp.demo.spring_boot.game_0_demo.entity.vo;

import lombok.Data;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Logined;

import java.time.LocalDateTime;

@Data
public class LoginedPageVo
{
	private String id;
	private LocalDateTime lastInteractiveTime;
	private boolean logInOrOut;
	public LoginedPageVo( Logined logined )
	{
		this.id = "user_"+logined.getUsersId();
		this.lastInteractiveTime = logined.getTime();
		this.logInOrOut = logined.getLogInOrOut ();//true : login , false : logout
	}
	
	public LoginedPageVo ()
	{
	}
	
	public String getId ()
	{
		return id;
	}
	
	public void setId ( String id )
	{
		this.id = id;
	}
	
	public LocalDateTime getLastInteractiveTime ()
	{
		return lastInteractiveTime;
	}
	
	public void setLastInteractiveTime ( LocalDateTime lastInteractiveTime )
	{
		this.lastInteractiveTime = lastInteractiveTime;
	}
	
	public boolean isLogInOrOut ()
	{
		return logInOrOut;
	}
	
	public void setLogInOrOut ( boolean logInOrOut )
	{
		this.logInOrOut = logInOrOut;
	}
}
