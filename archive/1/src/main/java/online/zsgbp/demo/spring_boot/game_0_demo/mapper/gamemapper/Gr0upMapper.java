package online.zsgbp.demo.spring_boot.game_0_demo.mapper.gamemapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Gr0up;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface Gr0upMapper extends BaseMapper <Gr0up>
{
	@Insert ( "INSERT INTO `gr0ups` (`id`, `father_id`,`map0_id`, `users_id`,`bl0ck_count`) VALUES (#{id}, #{fatherId},#{map0Id}, #{usersId},#{bl0ckCount})" )
	void addGr0up ( Gr0up gr0up );
	
	@Select ( "SELECT * FROM `gr0ups` WHERE `users_id` = #{usersId} AND `map0_id` = #{map0Id}" )
	List < Gr0up > getGr0upByUsersId ( int usersId , int map0Id );
	
	@Select ( "SELECT * FROM `gr0ups` WHERE `id` = #{id} AND `map0_id` = #{map0Id}" )
	Gr0up getGr0upById ( int id , int map0Id );
	
	@Select ( "SELECT * FROM `gr0ups` WHERE `map0_id` = #{map0Id}" )
	List < Gr0up > getAllGr0upByMap0Id ( int map0Id );
	
	@Select ( "SELECT `bl0ck_count` FROM `gr0ups` WHERE `id` = #{id} AND `map0_id` = #{map0Id}" )
	int getCountByGr0upId ( int id , int map0Id );
	
	@Update ( "UPDATE `gr0ups` SET  `father_id` = #{fatherId} , `users_id` = #{usersId} ,`bl0ck_count` = #{bl0ckCount} WHERE `id` = #{id} AND `map0_id` = #{map0Id}" )
	void updateGr0up ( int id ,int fatherId, int usersId , int bl0ckCount , int map0Id );
}
