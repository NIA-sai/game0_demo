package online.zsgbp.demo.spring_boot.game_0_demo.mapper.usermapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface KeyMapper
{
	@Insert ( "INSERT INTO `keys` (`id`,`code`) VALUES(#{id},#{code})")
	void addKey ( Integer id,String code );
	@Select ( "SELECT `code` FROM `keys` WHERE `id`=#{id}" )
	String getKeyCodeById ( Integer id );
	@Select ( "SELECT EXISTS(SELECT 1 FROM `keys` WHERE `id`=#{id})" )
	Boolean isKeyExist ( Integer id );
}
