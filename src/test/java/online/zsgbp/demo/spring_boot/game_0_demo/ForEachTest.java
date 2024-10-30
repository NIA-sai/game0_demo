package online.zsgbp.demo.spring_boot.game_0_demo;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ForEachTest
{
	@Test
	public void sumTest ( )
	{
		Map < String, Integer > map = new HashMap <> ();
		map.put ( "a" , 1 );
		map.put ( "b" , 2 );
		map.put ( "c" , 3 );
		
		// it must be an array!!!?
		final int[] sum = { 0 };
		map.forEach ( ( key , value ) ->
		{
			sum[0] += value*2;
		} );
		
		System.out.println ( "Sum of transformed values: "+sum[0] ); // 输出结果
	}
}
