package online.zsgbp.demo.spring_boot.game_0_demo.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.UserPageVo;


//@Getter
//@AllArgsConstructor
public class UserForm
{
	
	private final UserPageVo userPageVo;
	private final UserDetail userDetail;
	
	public UserForm ( UserPageVo userPageVo , UserDetail userDetail )
	{
		this.userPageVo = userPageVo;
		this.userDetail = userDetail;
	}
	
	public UserPageVo getUserPageVo ()
	{
		return userPageVo;
	}
	
	public UserDetail getUserDetail ()
	{
		return userDetail;
	}
}
