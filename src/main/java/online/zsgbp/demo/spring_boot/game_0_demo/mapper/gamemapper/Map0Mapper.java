package online.zsgbp.demo.spring_boot.game_0_demo.mapper.gamemapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Map0;
import org.apache.ibatis.annotations.*;

//bbzl
@Mapper
public interface Map0Mapper extends BaseMapper <Map0>
{
	@Insert ( "INSERT INTO `map0s` (`id`, `users_id`,`seed`,`create_time`,`biggest_gr0up_id`) VALUES (#{id}, #{usersId},#{seed},#{createTime},#{biggestGr0upId})" )
	void addMap0 ( Map0 map0 );
	
	@Delete ( "DELETE FROM `map0s` WHERE `id`=#{id}" )
	void deleteMap0 ( int id );
	
	@Select ( "SELECT * FROM `map0s` WHERE `id`=#{id}" )
	Map0 getMap0 ( int id );
	
	@Update ( "UPDATE `map0s` SET `biggest_gr0up_id`=#{biggestGr0upId} WHERE `id`=#{id}" )
	void updateMap0 ( int id , int biggestGr0upId );
	
	@Select ( "SELECT MAX(`id`) FROM `map0s`" )
	Integer getMaxMap0Id ();
}
