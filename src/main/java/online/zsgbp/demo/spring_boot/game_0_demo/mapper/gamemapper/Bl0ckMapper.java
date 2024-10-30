package online.zsgbp.demo.spring_boot.game_0_demo.mapper.gamemapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Bl0ck;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//@Deprecated//I ll try redis//but maybe it could remain
@Mapper
public interface Bl0ckMapper extends BaseMapper <Bl0ck>
{
	@Insert ( "INSERT INTO `bl0cks` (`map0_id`,`x`,`y`,`num`,`gr0up_id`,`belong_to_users_id`,`choosable`) VALUES(#{map0Id},#{x},#{y},#{num},#{gr0upId},#{belongToUsersId},#{choosable})" )
	void addBl0ck ( Bl0ck bl0ck );
	
	@Select ( "SELECT * FROM `bl0cks` WHERE `x` = ${x} AND `y` = ${y} AND `map0_id` = ${map0Id}" )
	Bl0ck getBl0ckByCoordinate ( long x , long y , int map0Id );
	
	@Select ( "SELECT * FROM `bl0cks` WHERE `gr0up_id` = ${gr0upId} AND `map0_id` = ${map0Id}" )
	List < Bl0ck > getBl0ckByGroupId ( int groupId , int map0Id );
	
	@Select ( "SELECT * FROM `bl0cks` WHERE `belong_to_users_id` = ${usersId} AND `map0_id` = ${map0Id}" )
	List < Bl0ck > getBl0ckByUserId ( int usersId , int map0Id );
	
	@Select ( "SELECT EXISTS(SELECT 1 FROM `bl0cks` WHERE `x` = ${x} AND `y` = ${y} AND `map0_id` = ${map0Id})" )
	boolean isBlockExist ( long x , long y , int map0Id );
	
	@Select ( "SELECT MAX(`group_id`) FROM `bl0cks` WHERE `map0_id` = ${map0Id}" )
	int getMaxGroupId ( int map0Id );
	
	@Update ( "UPDATE `bl0cks` SET `num` = #{num} , `gr0up_id` = #{gr0upId} , `belong_to_users_id`=#{belongToUsersId},`choosable`=${choosable} WHERE `x` = #{x} AND `y` = #{y} AND `map0_id` = #{map0Id}" )
	void updateBl0ck ( Bl0ck bl0ck );
}
