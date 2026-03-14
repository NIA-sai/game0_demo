package online.zsgbp.demo.spring_boot.game_0_demo.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest
{
	private String nameOrEmail=null;
	private String password=null;
	
	public String getNameOrEmail ()
	{
		return nameOrEmail;
	}
	
	public String getPassword ()
	{
		return password;
	}
}
