package online.zsgbp.demo.spring_boot.game_0_demo.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.zsgbp.demo.spring_boot.game_0_demo.util.Encrypt;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayBl0ckRequest
{
	private Long x = 0L;
	private Long y = 0L;
	private Boolean autoCreateMap0 = true;
	
	public Integer getSize ()
	{
		return size;
	}
	
	public void setSize ( Integer size )
	{
		this.size = size;
	}
	
	public String getSeed ()
	{
		return seed;
	}
	
	public void setSeed ( String seed )
	{
		this.seed = seed;
	}
	
	public Boolean getAutoCreateMap0 ()
	{
		return autoCreateMap0;
	}
	
	public void setAutoCreateMap0 ( Boolean autoCreateMap0 )
	{
		this.autoCreateMap0 = autoCreateMap0;
	}
	
	public Long getX ()
	{
		return x;
	}
	
	public void setX ( Long x )
	{
		this.x = x;
	}
	
	public Long getY ()
	{
		return y;
	}
	
	public void setY ( Long y )
	{
		this.y = y;
	}
	
	private Integer size = null;
	private String seed = null;
}
