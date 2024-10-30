package online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

@TableName ( "rank_guess" )
public class RankGuess extends Rank
{
	int usersId;
	Long score;
	Integer rank;
	
	@JsonIgnore
	private static final String rankName = "guess_rank";
	
	public String getRankName ()
	{
		return rankName;
	}
	
	public RankGuess ( int usersId , Long score , int rank )
	{
		this.usersId = usersId;
		this.score = score;
		this.rank = rank;
	}
	
	public void setScore ( long score )
	{
		this.score = score;
	}
	
	public void setRank ( int rank )
	{
		this.rank = rank;
	}
	
	public int getUsersId ()
	{
		return usersId;
	}
	
	public long getScore ()
	{
		return score;
	}
	
	public int getRank ()
	{
		return rank;
	}
}
