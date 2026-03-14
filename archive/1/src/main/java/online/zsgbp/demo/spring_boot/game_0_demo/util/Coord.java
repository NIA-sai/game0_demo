package online.zsgbp.demo.spring_boot.game_0_demo.util;

import lombok.AllArgsConstructor;

//coordinate is too long
//@AllArgsConstructor
public class Coord
{
	public long x, y;
	
	public Coord ( long y , long x )
	{
		this.y = y;
		this.x = x;
	}
	
	public Coord ()
	{
	}
}
