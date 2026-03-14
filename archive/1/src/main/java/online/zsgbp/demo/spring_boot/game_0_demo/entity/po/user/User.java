package online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import online.zsgbp.demo.spring_boot.game_0_demo.util.Encrypt;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Getter
@TableName ( "users" )
public class User implements Serializable
{
	
	
	@TableId ( value = "id", type = IdType.AUTO )
	private Integer id;
	
	private String name;
	private String email;
	private Integer keysId;
	private String password;
	private LocalDateTime registerTime;
	private String sign="0";
	
	public void setsKeys ( String str )
	{
		keysId = 0;
		char[] a = str.toCharArray ();
		for ( char c : a )
		{
			keysId += ( int ) c;
			keysId %= 50;
		}
	}
	
	public void setsPassword ( String str , String code )
	{
		StringBuilder raw = new StringBuilder ( str );
		raw.insert ( keysId > str.length () ? str.length () : keysId , code );
		String fp = Encrypt.MD5 ( raw.toString () );
		String sp = Encrypt.Sha256 ( fp );
		password = Encrypt.B.Crypt ( sp );
	}
	public User ( Integer id , String name , String email , Integer keysId , String password , LocalDateTime registerTime , String sign )
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.keysId = keysId;
		this.password = password;
		this.registerTime = registerTime;
		this.sign = sign;
	}
	
	public Integer getId ()
	{
		return id;
	}
	
	public void setId ( Integer id )
	{
		this.id = id;
	}
	
	public String getName ()
	{
		return name;
	}
	
	public void setName ( String name )
	{
		this.name = name;
	}
	
	public String getEmail ()
	{
		return email;
	}
	
	public void setEmail ( String email )
	{
		this.email = email;
	}
	
	public Integer getKeysId ()
	{
		return keysId;
	}
	
	public void setKeysId ( Integer keysId )
	{
		this.keysId = keysId;
	}
	
	public String getPassword ()
	{
		return password;
	}
	
	public void setPassword ( String password )
	{
		this.password = password;
	}
	
	public LocalDateTime getRegisterTime ()
	{
		return registerTime;
	}
	
	public void setRegisterTime ( LocalDateTime registerTime )
	{
		this.registerTime = registerTime;
	}
	
	public String getSign ()
	{
		return sign;
	}
	
	public void setSign ( String sign )
	{
		this.sign = sign;
	}
	
	public User ()
	{
	}
	
}
