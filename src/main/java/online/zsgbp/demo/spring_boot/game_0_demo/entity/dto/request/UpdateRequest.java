package online.zsgbp.demo.spring_boot.game_0_demo.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest
{
	private String originalInfo=null;
	private String updateInfo;
	private String confirm=null;
	
	public String getOriginalInfo ()
	{
		return originalInfo;
	}
	
	public String getUpdateInfo ()
	{
		return updateInfo;
	}
	
	public String getConfirm ()
	{
		return confirm;
	}
}
