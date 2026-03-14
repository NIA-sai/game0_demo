package online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

//old name:Bl0ck now all move to->Bl0ck (MySQL)
@Deprecated
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash ( "bl0cks" )
public class Bl0ckRedis
{
	
	
	@Id
	private String id;
	
	private int map0Id;
	private long x;
	private long y;
	private byte num=-1;
	private int gr0upId = 0;
	private int belongToUsersId = 0;
	private boolean choosable = false;//:could be choosed ;1:chosen
	
	
//	public Bl0ck ( long x , long y , byte num , int gr0upId , int belongToUsersId , boolean choosable )
//	{
//		this.x = x;
//		this.y = y;
//		this.num = num;
//		this.gr0upId = gr0upId;
//		this.belongToUsersId = belongToUsersId;
//		this.choosable = choosable;
//	}
//
//	public Bl0ck ( int map0Id , long x , long y , byte num , int gr0upId , int belongToUsersId , boolean choosable )
//	{
//		this.id = map0Id+":"+x+","+y;
//		this.map0Id = map0Id;
//		this.x = x;
//		this.y = y;
//		this.num = num;//but exactly when num==-1,it doesn't mean that this block is un-choosable
//		this.gr0upId = gr0upId;
//		this.belongToUsersId = belongToUsersId;
//		this.choosable = choosable;
//	}
//
//	public Bl0ck setMapId ( int map0Id )
//	{
//		this.map0Id = map0Id;
//		this.id = map0Id+":"+this.x+","+this.y;
//		return this;
//	}
//
//	public Bl0ck setCoord ( long x , long y )
//	{
//		this.x = x;
//		this.y = y;
//		this.id = this.map0Id+":"+x+","+y;
//		return this;
//	}
//
//	public Bl0ck setX ( long x )
//	{
//		this.x = x;
//		this.id = this.map0Id+x+","+this.y;
//		return this;
//	}
//
//	public Bl0ck setY ( long y )
//	{
//		this.y = y;
//		this.id = this.map0Id+":"+this.x+","+y;
//		return this;
//	}
//
//	public Bl0ck setNum ( byte num )
//	{
//		this.num = num;
//		return this;
//	}
//
//	public Bl0ck setGroupId ( int gr0upId )
//	{
//		this.gr0upId = gr0upId;
//		return this;
//	}
//
//	public Bl0ck setBelongToUserId ( int belongToUsersId )
//	{
//		this.belongToUsersId = belongToUsersId;
//		return this;
//	}
//
//	public Bl0ck setChoosable ( boolean choosable )
//	{
//		this.choosable = choosable;
//		return this;
//	}
//
//	public boolean getChoosable ()
//	{
//		return choosable;
//	}
}
