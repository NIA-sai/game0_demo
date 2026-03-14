package online.zsgbp.demo.spring_boot.game_0_demo.mapper.usermapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Logined;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginedMapper extends BaseMapper <Logined>
{
	@Insert ( "INSERT INTO `logined`(`users_id`,`ip`,`login_or_out`) VALUES(#{usersId},#{ip},#{logInOrOut})" )
	void addLogined ( Logined logined );
	
	@Select ("SELECT `login_or_out` FROM `logined` WHERE `users_id`=#{usersId} ORDER BY `id` DESC LIMIT 1" )
	Boolean isLogined ( Integer usersId );

	@Select ( "SELECT * FROM `logined` WHERE `users_id`=#{usersId} ORDER BY `id` DESC" )
	List <Logined> getAllLoginedByUserId ( Integer usersId );
	
	@Select ( "SELECT * FROM `logined` WHERE `users_id`=#{usersId} ORDER BY `id` DESC LIMIT 1" )
	Logined getLatestLoginedByUserId ( Integer usersId );
	
	@Delete ( "DELETE FROM `logined` WHERE `users_id`=#{usersId}" )
	void deleteLogined ( Integer usersId );
}
