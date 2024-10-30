package online.zsgbp.demo.spring_boot.game_0_demo.mapper.rankmapper;

import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.RankGuess;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OtherRankMapper//rankGuessMapper actually
{
	@Insert ( "INSERT INTO rank_guess (users_id,score,`rank`) VALUES (#{usersId},#{score},#{rank})" )
	void addRank ( RankGuess rankGuess );
	
	@Update ( "UPDATE rank_guess SET score=#{score},`rank`=#{rank} WHERE users_id=#{usersId}" )
	void updateRank ( RankGuess rankGuess );
	
	@Select ( "SELECT * FROM rank_guess WHERE users_id=#{usersId}" )
	RankGuess getRankGuessByUsersId ( int usersId );
	
	@Select ( "SELECT COUNT(1)+1 FROM rank_guess WHERE score>#{score}" )
	int getRankByScore ( long score );
}
