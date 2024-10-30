package online.zsgbp.demo.spring_boot.game_0_demo;

import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Gr0up;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Map0;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.User;
import online.zsgbp.demo.spring_boot.game_0_demo.service.cacheService.Bl0ckCacheService;
import online.zsgbp.demo.spring_boot.game_0_demo.service.cacheService.Bl0ckCacheServiceInter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;

public class SerializeTest
{
	
	@Test
	public void test ()
	{
		Map0 map0 = new Map0 ( 1 , 1 , "sad" , LocalDateTime.now () , 1 );
		System.out.println ( map0 );
	}
	//	@Autowired
	//	private Bl0ckCacheServiceInter bl0ckCacheService;
	//	@Test
	//	public void test2()
	//	{
	//		//bl0ckCacheService.addMap0 ( new Map0 ( 1,1,"sad", LocalDateTime.now (),1 ) );
	//		bl0ckCacheService.addGr0up ( new Gr0up (1,1,1,1) );
	//	}
	
	
	@Test
	public void test3 ()
	{
		Map0 map0=new Map0 ( 1 , 1 , "sad" , LocalDateTime.now () , 1 );
		System.out.println ( map0 );
	}
//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate;
//	@Test
//	public void serializeTest4 ()
//	{
//		User user=new User ( 1 , "1" , "1" ,1,"1", LocalDateTime.now () , "1" );
//		redisTemplate.opsForList ().rightPush ( "111111",user );
//	}
}
