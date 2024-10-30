package online.zsgbp.demo.spring_boot.game_0_demo.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
//@NoArgsConstructor
public class Result
{
	
	
	private int code;
	private String message;
	private Object data;
	
	public static Result success ( Object data )
	{
		return new Result ( 200 , "success awa" , data );
	}
	
	public static Result success ( String message , Object data )
	{
		return new Result ( 255 , message , data );
	}
	public static Result success ( int code,String message , Object data )
	{
		return new Result ( code , message , data );
	}
	public static Result success ()
	{
		return new Result ( 200 , "success awa" , null );
	}
	
	public static Result suspect ( int code , String message , Object data )
	{
		return new Result ( code , message , data );
	}
	
	public static Result failure ( int code , String message )
	{
		return new Result ( code , message , null );
	}
	public int getCode ()
	{
		return code;
	}
	
	public void setCode ( int code )
	{
		this.code = code;
	}
	
	public String getMessage ()
	{
		return message;
	}
	
	public void setMessage ( String message )
	{
		this.message = message;
	}
	
	public Object getData ()
	{
		return data;
	}
	
	public Result ( int code , String message , Object data )
	{
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public void setData ( Object data )
	{
		this.data = data;
	}
	
	public Result ()
	{
	}
}
/*success[200,300)
suspect(whatever)
failure[400,500)*/