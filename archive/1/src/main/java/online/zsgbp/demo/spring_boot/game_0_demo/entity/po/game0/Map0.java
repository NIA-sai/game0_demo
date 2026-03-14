package online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

//use redis basic template to store
@Data
@TableName ( "map0" )
public class Map0 implements Serializable
{

	@TableId
	private int id;
	
	private int usersId;//who created this map
	private String seed;
	
	@JsonDeserialize ( using = LocalDateTimeDeserializer.class )//no courage to delete this annotation
	@JsonSerialize ( using = LocalDateTimeSerializer.class )
	private LocalDateTime createTime;
	
	private int biggestGr0upId;
	public Map0 ( int id , int usersId , String seed , LocalDateTime createTime , int biggestGr0upId )
	{
		this.id = id;
		this.usersId = usersId;
		this.seed = seed;
		this.createTime = createTime;
		this.biggestGr0upId = biggestGr0upId;
	}
	
	public int getId ()
	{
		return id;
	}
	
	public void setId ( int id )
	{
		this.id = id;
	}
	
	public int getUsersId ()
	{
		return usersId;
	}
	
	public void setUsersId ( int usersId )
	{
		this.usersId = usersId;
	}
	
	public String getSeed ()
	{
		return seed;
	}
	
	public void setSeed ( String seed )
	{
		this.seed = seed;
	}
	
	public LocalDateTime getCreateTime ()
	{
		return createTime;
	}
	
	public void setCreateTime ( LocalDateTime createTime )
	{
		this.createTime = createTime;
	}
	
	public int getBiggestGr0upId ()
	{
		return biggestGr0upId;
	}
	
	public void setBiggestGr0upId ( int biggestGr0upId )
	{
		this.biggestGr0upId = biggestGr0upId;
	}
	public Map0 ()
	{
	
	}
	
}
