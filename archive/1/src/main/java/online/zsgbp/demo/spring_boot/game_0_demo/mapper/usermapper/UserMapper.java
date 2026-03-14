package online.zsgbp.demo.spring_boot.game_0_demo.mapper.usermapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper <User>
{
	@Insert ( "INSERT INTO `users` (`name`,`email`,`password`,`keys_id`,`sign`) VALUES(#{name},#{email},#{password},#{keysId},#{sign})" )
	void addUser ( User user );
	
	@Select ( "SELECT EXISTS(SELECT 1 FROM `users` WHERE `name`=#{name})" )
	Boolean isNameExist ( String name );
	
	@Select ( "SELECT EXISTS(SELECT 1 FROM `users` WHERE `email`=#{email})" )
	Boolean isEmailExist ( String email );
	
	@Select ( "SELECT EXISTS(SELECT 1 FROM `users` WHERE `id`=#{id})" )
	Boolean isIdExist ( Integer id );
	
	@Select ( "SELECT * FROM `users` WHERE `id`=#{id}" )
	User getUserById ( Integer id );
	
	@Select ( "SELECT * FROM `users` WHERE `name`=#{name}" )
	User getUserByName ( String name );
	
	@Select ( "SELECT * FROM `users` WHERE `email`=#{email}" )
	User getUserByEmail ( String email );
	
	@Select ( "SELECT `id` FROM `users` WHERE `name`=#{name} " )
	Integer getIdByName ( String name );
	
	@Select ( "SELECT COUNT(1) FROM `users`" )
	int getUserNum ();
	
	@Select ( "SELECT * FROM `users`" )
	List < User > getAllUser ();
	
	//	@Update ( "UPDATE `users` SET `name`=#{name} WHERE `id`=#{id}" )
	//	void updateName ( User user );
	//
	//	@Update ( "UPDATE `users` SET `password`=#{password} WHERE `id`=#{id}" )
	//	void updatePassword ( String password , Integer uid );
	
	@Update ( "UPDATE `users` SET `keys_id`=#{keysId} , `name`=#{name} , `password`=#{password} ,`sign`=#{sign} WHERE `id`=#{id}" )
	void updateUser ( User user );
	
	@Delete ( "DELETE FROM `users` WHERE `id`=#{id}" )
	void deleteUser ( Integer id );
}
