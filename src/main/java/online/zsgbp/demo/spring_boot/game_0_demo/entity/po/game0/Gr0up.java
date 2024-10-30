package online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
//@AllArgsConstructor//not safe
//@NoArgsConstructor
@TableName ( "gr0ups" )
public class Gr0up implements Serializable
{
	private int id = 0;
	private int fatherId = id;
	private int map0Id = 0;
	private int usersId = 0;
	
	public int getBl0ckCount ()
	{
		return bl0ckCount;
	}
	
	public int getUsersId ()
	{
		return usersId;
	}
	
	public int getMap0Id ()
	{
		return map0Id;
	}
	
	public int getFatherId ()
	{
		return fatherId;
	}
	
	public int getId ()
	{
		return id;
	}
	
	private int bl0ckCount = 0;
	
	public Gr0up ( int id , int usersId , int bl0ckCount , int map0Id )//use to create new group
	{
		this.id = id;
		this.fatherId = id;
		this.usersId = usersId;
		this.bl0ckCount = bl0ckCount;
		this.map0Id = map0Id;
	}
	
	public Gr0up setBl0ckCount ( int bl0ckCount )
	{
		this.bl0ckCount = bl0ckCount;
		return this;
	}
	
	public Gr0up setFatherId ( int fatherId )
	{
		this.fatherId = fatherId;
		return this;
	}
	
	public Gr0up ()
	{
	
	}
}
