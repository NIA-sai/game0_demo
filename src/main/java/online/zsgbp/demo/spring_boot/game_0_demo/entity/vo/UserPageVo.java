package online.zsgbp.demo.spring_boot.game_0_demo.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Logined;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Rank;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Rank0;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
//@NoArgsConstructor
public class UserPageVo
{
	@JsonIgnore
	private Integer rawId;//for operate
	
	private String id;
	private String name;
	private String email;
	private LocalDateTime registerTime;
	private String sign;
	private LocalDateTime lastInteractiveTime = LocalDateTime.now ();
	private Map < String, List < Rank > > rankMap;
	
	public UserPageVo ( User user )
	{
		rawId = user.getId ();
		id = "user_"+user.getId ();
		name = "player_"+user.getName ();
		email = user.getEmail ();
		registerTime = user.getRegisterTime ();
		sign = "000 "+user.getSign ()+" 000";
		lastInteractiveTime = LocalDateTime.now ();
	}
	
	public UserPageVo setUser ( User user )
	{
		id = "user_"+String.format ( "%1$8s" , user.getId ().toString () ).replace ( ' ' , '0' );
		name = "player_"+user.getName ();
		email = user.getEmail ();
		registerTime = user.getRegisterTime ();
		sign = "000 "+user.getSign ()+" 000";
		lastInteractiveTime = LocalDateTime.now ();
		return this;
	}
	
	public UserPageVo setLastInteractiveTime ( LocalDateTime time )
	{
		lastInteractiveTime = time;
		return this;
	}
	
	//	public UserPageVo addRank ( Rank rank )
	//	{
	//		lastInteractiveTime = LocalDateTime.now ();
	//		if ( rankMap.containsKey ( rank.getRankName () ) )
	//			rankMap.replace ( rank.getRankName () , rank );
	//		else
	//			rankMap.put ( rank.getRankName () , rank );
	//		return this;
	//	}
	public UserPageVo addRank (  List<Rank> ranks )
	{
		lastInteractiveTime = LocalDateTime.now ();
		for( Rank r : ranks)
		{
			if(r==null) continue;
			if ( rankMap.containsKey ( r.getRankName () ) )
				rankMap.get ( r.getRankName () ).add ( r );
			else
				rankMap.put ( r.getRankName () , List.of ( r ) );
		}
		return this;
	}
	
	
	public UserPageVo setLogined ( Logined logined )
	{
		lastInteractiveTime = logined.getTime ();
		return this;
	}
	
	public UserPageVo setRankList ( Map < String, List<Rank> > rankMap )
	{
		lastInteractiveTime = LocalDateTime.now ();
		this.rankMap.putAll ( rankMap );
		return this;
	}
	
	public UserPageVo ( Integer rawId , String id , String name , String email , LocalDateTime registerTime , String sign , LocalDateTime lastInteractiveTime , Map < String, List < Rank > > rankMap )
	{
		this.rawId = rawId;
		this.id = id;
		this.name = name;
		this.email = email;
		this.registerTime = registerTime;
		this.sign = sign;
		this.lastInteractiveTime = lastInteractiveTime;
		this.rankMap = rankMap;
	}
	
	public UserPageVo ()
	{
	}
	
	public Integer getRawId ()
	{
		return rawId;
	}
	
	public void setRawId ( Integer rawId )
	{
		this.rawId = rawId;
	}
	
	public String getId ()
	{
		return id;
	}
	
	public void setId ( String id )
	{
		this.id = id;
	}
	
	public String getName ()
	{
		return name;
	}
	
	public void setName ( String name )
	{
		this.name = name;
	}
	
	public String getEmail ()
	{
		return email;
	}
	
	public void setEmail ( String email )
	{
		this.email = email;
	}
	
	public LocalDateTime getRegisterTime ()
	{
		return registerTime;
	}
	
	public void setRegisterTime ( LocalDateTime registerTime )
	{
		this.registerTime = registerTime;
	}
	
	public String getSign ()
	{
		return sign;
	}
	
	public void setSign ( String sign )
	{
		this.sign = sign;
	}
	
	public LocalDateTime getLastInteractiveTime ()
	{
		return lastInteractiveTime;
	}
	
	public Map < String, List < Rank > > getRankMap ()
	{
		return rankMap;
	}
	
	public void setRankMap ( Map < String, List < Rank > > rankMap )
	{
		this.rankMap = rankMap;
	}
}
