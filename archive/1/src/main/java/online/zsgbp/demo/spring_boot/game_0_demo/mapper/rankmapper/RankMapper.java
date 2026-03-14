package online.zsgbp.demo.spring_boot.game_0_demo.mapper.rankmapper;

import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Rank0;
import org.apache.ibatis.annotations.*;

import java.util.List;

//now this almost only used for rank0//i decided no more other silly tiny game
@Mapper
public interface RankMapper
		//so it's actually Rank0Mapper but whatever
{
	
	@Insert ( "INSERT INTO `${rankName}` (`users_id`,`sc0re`,`map0_id`,`rank`) VALUES(#{rank0.usersId},#{rank0.sc0re},#{rank0.map0Id},#{rank0.rank})" )
	void addRank0 ( @Param ( "rankName" ) String rankName , Rank0 rank0 );
	
	@Update ( "UPDATE `${rankName}` SET `sc0re`=#{rank0.sc0re} ,`rank`= #{rank0.rank} WHERE `users_id`=#{rank0.usersId} AND `map0_id`=#{rank0.map0Id}" )
	void updateRank0 ( @Param ( "rankName" ) String rankName , Rank0 rank0 );
	
	@Select ( "SELECT COUNT(1)+1 FROM `${rankName}` WHERE `map0_id`=#{map0Id} AND `score`>#{score}" )
	Integer getRankByScore ( @Param ( "rankName" ) String rankName , Long score , Integer map0Id );
	
	@Select ( "SELECT `r` FROM (SELECT `users_id`, RANK() OVER (ORDER BY `sc0re` DESC) AS `r` FROM ${rankName} WHERE `map0_id` = #{map0Id}) AS `temp` WHERE `users_id`=#{usersId}" )
	Integer getRank0WithRank ( @Param ( "rankName" ) String rankName , Integer usersId , Integer map0Id );
	
	@Select ( "SELECT * FROM `${rankName}` WHERE `users_id` = #{usersId} AND `map0_id` = #{map0Id} LIMIT 1 " )
	Rank0 getRank0 ( @Param ( "rankName" ) String rankName , Integer usersId , Integer map0Id );
	
	@Select ( "SELECT id, users_id , score, RANK() OVER (ORDER BY score DESC) AS rank FROM `${rankName}` WHERE `map0_id` = #{map0Id}" )
	List < Rank0 > getAllUsersRanks ( @Param ( "rankName" ) String rankName , Integer map0Id );
	
	@Select ( "SELECT * FROM `${rankName}` WHERE `users_id`=#{usersId} ORDER BY `map0_id` ASC" )
	List < Rank0 > getAllRanks ( @Param ( "rankName" ) String rankName , Integer usersId );
	
	@Select ( "SELECT `score` FROM `${rankName}` WHERE `users_id`=#{usersId} AND `map0_id`=#{map0Id}" )
	Long getScore ( @Param ( "rankName" ) String rankName , Integer usersId , Integer map0Id );
	
	@Delete ( "DELETE FROM `${rankName}` WHERE `users_id`=#{usersId} AND `map0_id`=#{map0Id}" )
	void deleteRank ( @Param ( "rankName" ) String rankName , Integer usersId , Integer map0Id );
	
	@Select ( "SELECT EXISTS(SELECT 1 FROM `${rankName}` WHERE `users_id`=#{usersId} AND `map0_id`=#{map0Id})" )
	Boolean isRankExist ( @Param ( "rankName" ) String rankName , Integer usersId , Integer map0Id );
	
}
