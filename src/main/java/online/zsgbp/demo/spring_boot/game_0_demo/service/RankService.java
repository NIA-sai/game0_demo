package online.zsgbp.demo.spring_boot.game_0_demo.service;

import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Rank;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Rank0;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.User;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.Result;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.UserPageVo;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.rankmapper.RankMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.usermapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Deprecated//NO TIME TO LEARN THE mybatis plus's @SelectProvider
@Service
public class RankService
{
	@Autowired
	private RankMapper rankMapper;
	@Autowired
	private UserMapper userMapper;
	
	//todo change the return type
	public Result getRankByNameAndUserName ( String rankName , Integer usersId )
	{
		User user = userMapper.getUserById ( usersId );
		List < Rank > ranks =new ArrayList<> ();
		return Result.success ( new UserPageVo ( user ).addRank ( null ) );
	}
	
//	public Result updateRank ( String rankName , Integer usersId , Long score )
//	{
//		if ( rankMapper.isRankExist ( rankName , usersId ) )
//			rankMapper.updateRank ( rankName , usersId , score );
//		else
//			rankMapper.addRank ( rankName , usersId , score );
//		return getRankAsResult ( rankName , usersId );
//	}
	
//	public Result getRankByUsersId ( String rankName , Integer usersId )
//	{
//		if ( !userMapper.isIdExist ( usersId ) )
//			return Result.failure ( 404 , "user_"+usersId+" has not registered yet" );
//		if ( !rankMapper.isRankExist ( rankName , usersId ) )
//			return Result.failure ( 404 , "soon come and play "+rankName.substring ( 4 )+"by API /game"+rankName.substring ( 4 ) );
//		return getRankAsResult ( rankName , usersId );
//	}
	
//	public Result deleteRank ( String rankName , Integer usersId )
//	{
//		if ( !userMapper.isIdExist ( usersId ) )
//			return Result.failure ( 404 , "user_"+usersId+" has not registered yet" );
//		if ( !rankMapper.isRankExist ( rankName , usersId ) )
//			return Result.failure ( 404 , "user_"+usersId+" has not ranked in yet" );
//		rankMapper.deleteRank ( rankName , usersId );
//		return Result.success ();
//	}
	
//	public Result getRankList ( List < String > rankNames , Integer usersId )
//	{
//		if ( !userMapper.isIdExist ( usersId ) )
//			return Result.failure ( 404 , "user_"+usersId+" has not registered yet" );
//		User user = userMapper.getUserById ( usersId );
//		return Result.success ( new UserPageVo ( user ).setRankList ( rankNames.stream ().collect ( Collectors.toMap ( rankName -> rankName , rankName -> ( Rank ) getRankAsResult ( rankName , usersId ).getData () ) ) ) );
//	}
	
	
}
