package online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode ( callSuper = true )
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@TableName ( "rank0" )
public class Rank0 extends Rank
{
	
	
	private Integer usersId = 0;
	private Long sc0re = 0L;
	private Integer map0Id = -1;
	
	//@TableField ( exist = false )
	private Long rank = -1L;
	
	@TableField ( exist = false )
	@JsonIgnore
	private static final String rankName = "rank0";
	
	public String getRankName ()
	{
		return rankName;
	}
	
	public Rank0 setMap0Id ( int map0Id )
	{
		this.map0Id = map0Id;
		return this;
	}
	
	public Rank0 setRank ( Long rank )
	{
		this.rank = rank;
		return this;
	}
	
	public Rank0 setSc0re ( Long sc0re )
	{
		this.sc0re = sc0re;
		return this;
	}
	
	//stereo constructor
	public Rank0 ( Integer usersId , Long sc0re , Integer map0Id )
	{
		this.usersId = usersId;
		this.sc0re = sc0re;
		this.map0Id = map0Id;
	}
	
	public Integer getUsersId ()
	{
		return usersId;
	}
	
	public Long getRank ()
	{
		return rank;
	}
	
	public Integer getMap0Id ()
	{
		return map0Id;
	}
	
	public Long getSc0re ()
	{
		return sc0re;
	}
	
	public Rank0 ( Integer usersId , Long sc0re , Integer map0Id , Long rank )
	{
		this.usersId = usersId;
		this.sc0re = sc0re;
		this.map0Id = map0Id;
		this.rank = rank;
	}
	
	public Rank0 ()
	{
	}
}
