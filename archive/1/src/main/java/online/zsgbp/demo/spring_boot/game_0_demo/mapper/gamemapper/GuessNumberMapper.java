package online.zsgbp.demo.spring_boot.game_0_demo.mapper.gamemapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.GuessNumber;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GuessNumberMapper extends BaseMapper < GuessNumber >
{
	@Insert ( "INSERT INTO guess_number (users_id,answer,guess,times,try_time) VALUES(#{usersId},#{answerNumber},#{guessNumber},#{times},#{tryTime})" )
	void addGuess ( GuessNumber guessNumberPo );
	
	@Select ( "SELECT * FROM guess_number WHERE users_id = #{userId} ORDER BY times ASC" )
	List < GuessNumber > getAllGuessNumberByUserId ( int userId );
	
	@Select ( "SELECT * FROM guess_number WHERE users_id = #{userId} ORDER BY times DESC LIMIT 1" )
	GuessNumber  getGuessNumberByUserId ( int userId );
	
	@Delete ( "DELETE FROM guess_number WHERE users_id = #{userId}" )
	void deleteGuessNumberByUserId ( int userId );
}
