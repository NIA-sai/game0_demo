package online.zsgbp.demo.spring_boot.game_0_demo.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Bl0ck;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Rank0;

import java.util.Optional;
import java.util.Set;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class Game0PageVo
{
	private int size;
	private Set <  Bl0ck >  views;
	private Rank0 rank0;
	
	public Game0PageVo setViews ( Set < Bl0ck >  views )
	{
		this.views = views;
		return this;
	}
	
	public Game0PageVo setSize ( int size )
	{
		this.size = size;
		return this;
	}
	
	public Game0PageVo setRank0 ( Rank0 rank0 )
	{
		this.rank0 = rank0;
		return this;
	}
	
	public Game0PageVo ()
	{
	}
	
	public int getSize ()
	{
		return size;
	}
	
	public Set < Bl0ck > getViews ()
	{
		return views;
	}
	
	public Rank0 getRank0 ()
	{
		return rank0;
	}
}
