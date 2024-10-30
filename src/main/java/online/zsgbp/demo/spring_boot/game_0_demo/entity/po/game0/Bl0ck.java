package online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@TableName ( "bl0cks" )
public class Bl0ck implements Serializable
{
	@Serial
	private static final long serialVersionUID = 1L;
	
	
	private long map0Id;
	private long x = 0;
	private long y = 0;
	
	public byte getNum ()
	{
		return num;
	}
	
	private byte num = -1;
	
	public int getGr0upId ()
	{
		return gr0upId;
	}
	
	public long getMap0Id ()
	{
		return map0Id;
	}
	
	public long getX ()
	{
		return x;
	}
	
	public long getY ()
	{
		return y;
	}
	
	public int getBelongToUsersId ()
	{
		return belongToUsersId;
	}
	
	public boolean isChoosable ()
	{
		return choosable;
	}
	
	private int gr0upId = 0;
	private int belongToUsersId = 0;//actually who create this block
	private boolean choosable = false;
	
	//	public Bl0ckMySQL ( Bl0ck bl0ck )
	//	{
	//		this.map0Id= bl0ck.getMap0Id ();
	//		this.x = bl0ck.getX ();
	//		this.y = bl0ck.getY ();
	//		this.num= bl0ck.getNum ();
	//		this.gr0upId= bl0ck.getGr0upId ();
	//		this.belongToUsersId= bl0ck.getBelongToUsersId ();
	//		this.choosable= bl0ck.getChoosable ();
	//	}
	
	public Bl0ck ( long x , long y , byte num , int gr0upId , int belongToUsersId , boolean choosable )
	{
		this.x = x;
		this.y = y;
		this.num = num;
		this.gr0upId = gr0upId;
		this.belongToUsersId = belongToUsersId;
		this.choosable = choosable;
	}
	
	public Bl0ck ( long x , long y , byte num , int gr0upId , int belongToUsersId  , boolean choosable , int map0Id )//stupid method
	{
		this.map0Id = map0Id;
		this.x = x;
		this.y = y;
		this.num = num;//but exactly when num==-1,it doesn't mean that this block is un-choosable
		this.gr0upId = gr0upId;
		this.belongToUsersId = belongToUsersId;
		this.choosable = choosable;
	}
	
	public Bl0ck setMapId ( int map0Id )
	{
		this.map0Id = map0Id;
		//this.id = map0Id+":"+this.x+","+this.y;
		return this;
	}
	
	public Bl0ck setCoord ( long x , long y )
	{
		this.x = x;
		this.y = y;
		//this.id = this.map0Id+":"+x+","+y;
		return this;
	}
	
	public Bl0ck setX ( long x )
	{
		this.x = x;
		//this.id = this.map0Id+x+","+this.y;
		return this;
	}
	
	public Bl0ck setY ( long y )
	{
		this.y = y;
		//this.id = this.map0Id+":"+this.x+","+y;
		return this;
	}
	
	public Bl0ck setNum ( byte num )
	{
		this.num = num;
		return this;
	}
	
	public Bl0ck setGroupId ( int gr0upId )
	{
		this.gr0upId = gr0upId;
		return this;
	}
	
	//	public Bl0ck setBelongToUserId ( int belongToUsersId )
	//	{
	//		this.belongToUsersId = belongToUsersId;
	//		return this;
	//	}
	
	public Bl0ck setChoosable ( boolean choosable )
	{
		this.choosable = choosable;
		return this;
	}
	
	public Bl0ck ( long map0Id , long x , long y , byte num , int gr0upId , int belongToUsersId , boolean choosable )
	{
		this.map0Id = map0Id;
		this.x = x;
		this.y = y;
		this.num = num;
		this.gr0upId = gr0upId;
		this.belongToUsersId = belongToUsersId;
		this.choosable = choosable;
	}
	
	public void setMap0Id ( long map0Id )
	{
		this.map0Id = map0Id;
	}
	
	public void setGr0upId ( int gr0upId )
	{
		this.gr0upId = gr0upId;
	}
	
	public void setBelongToUsersId ( int belongToUsersId )
	{
		this.belongToUsersId = belongToUsersId;
	}
	
	public boolean getChoosable ()
	{
		return choosable;
	}
}
