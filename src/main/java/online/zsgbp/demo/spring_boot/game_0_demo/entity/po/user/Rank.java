package online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class Rank implements Serializable
{
	@JsonIgnore
	private String rankName;
	
	public Rank setRankName(String rankName)
	{
		this.rankName = rankName;
		return this;
	}
	
	public String getRankName ()
	{
		return rankName;
	}
	
	public Rank ()
	{
	}
	
	public Rank ( String rankName )
	{
		this.rankName = rankName;
	}
}
