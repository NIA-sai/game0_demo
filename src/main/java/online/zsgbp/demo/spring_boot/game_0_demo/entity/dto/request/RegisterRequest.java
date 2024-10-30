package online.zsgbp.demo.spring_boot.game_0_demo.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest
{
	private String name;
	
	public String getName ()
	{
		return name;
	}
	
	public String getEmail ()
	{
		return email;
	}
	
	public String getPassword ()
	{
		return password;
	}
	
	public String getConfirm ()
	{
		return confirm;
	}
	
	public Boolean getAutoLogin ()
	{
		return autoLogin;
	}
	
	private String email;
	private String password;
	private String confirm;
	
	private Boolean autoLogin=null;
}
