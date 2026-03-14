package online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
//@AllArgsConstructor
@TableName ( "logined" )
public class Logined implements Serializable
{
	@TableId ( value = "id", type = IdType.AUTO )
	private Integer id;
	
	private Integer usersId;
	private String ip;
	private LocalDateTime time;
	private Boolean logInOrOut;
	
	public Logined ( User user , String ip , Boolean logInOrOut )
	{
		this.usersId = user.getId ();
		this.ip = ip;
		this.time = LocalDateTime.now ();
		this.logInOrOut = logInOrOut;
	}
	
	public void setId ( Integer id )
	{
		this.id = id;
	}
	
	public void setUsersId ( Integer usersId )
	{
		this.usersId = usersId;
	}
	
	public void setIp ( String ip )
	{
		this.ip = ip;
	}
	
	public void setTime ( LocalDateTime time )
	{
		this.time = time;
	}
	
	public void setLogInOrOut ( Boolean logInOrOut )
	{
		this.logInOrOut = logInOrOut;
	}
	
	public Integer getId ()
	{
		return id;
	}
	
	public Integer getUsersId ()
	{
		return usersId;
	}
	
	public String getIp ()
	{
		return ip;
	}
	
	public LocalDateTime getTime ()
	{
		return time;
	}
	
	public Boolean getLogInOrOut ()
	{
		return logInOrOut;
	}
	
	public Logined ()
	{
	}
	
	public Logined ( Integer id , Integer usersId , String ip , LocalDateTime time , Boolean logInOrOut )
	{
		this.id = id;
		this.usersId = usersId;
		this.ip = ip;
		this.time = time;
		this.logInOrOut = logInOrOut;
	}
}
