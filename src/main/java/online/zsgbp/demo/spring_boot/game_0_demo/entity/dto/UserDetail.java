package online.zsgbp.demo.spring_boot.game_0_demo.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
public class UserDetail
{
	private String token;
	private String ip;
	
	public UserDetail ( String token , String ip )
	{
		this.token = token;
		this.ip = ip;
	}
	
	public String getToken ()
	{
		return token;
	}
	
	public String getIp ()
	{
		return ip;
	}
}
