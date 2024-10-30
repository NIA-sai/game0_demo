//package online.zsgbp.demo.spring_boot.game_0_demo;
//
//import online.zsgbp.demo.spring_boot.game_0_demo.repository.Bl0ckRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest
////@ContextConfiguration (classes = { RedisConfig.class})
//public class RedisTest
//{
//	@Autowired
//	private RedisTemplate redisTemplate;//<> is not necessary????
//
//	@Test
//	public void testBl0ck ()
//	{
//		//Bl0ck bl0ck = new Bl0ck ( 1 , 2 , 3 , 1 , 3 , 1 );
//		//String key = bl0ck.getX ()+":"+bl0ck.getY ();//make it a util//stupid
//		//redisTemplate.opsForValue ().set ( key , bl0ck );//lazy to amend
//		//System.out.println ( redisTemplate.opsForValue ().get ( key ) );
//	}
//
//	@Test
//	public void testString3 ()
//	{
//		Map < String, Object > user = new HashMap <> ();
//		user.put ( "id" , "0000" );
//		user.put ( "name" , "0" );
//		user.put ( "birthday" , new Date ( 2005-1900 , 10 , 03 ) );
//		String key = "user:0001";
//		redisTemplate.opsForValue ().set ( key , user );
//		System.out.println ( redisTemplate.opsForValue ().get ( key ) );
//	}
//
//	@Test
//	public void testNULL ()
//	{
//		System.out.println ( redisTemplate.opsForValue ().get ( "s" ) );//it really sout null erm
//	}
//
//	/// holy shit @RedisHash save my life
//
//	@Autowired
//	private Bl0ckRepository bl0ckRepository;
//
//	@Test
//	public void testAnnotationOne ()
//	{
//		//Bl0ck bl0ck = new Bl0ck ( 546356, 0L , 0L , 3 , 0 , 0 );
//		//bl0ckRepository.save ( bl0ck );
//		//System.out.println ( bl0ckRepository.);
//		//bl0ckRepository.save(bl0ck.setNum ( 2 ));
//		//System.out.println ( bl0ckRepository.findById ( 2L ));
//	}
//
//	@Test
//	public void testList()
//	{
//		String key = "game0";
//		redisTemplate.opsForList ().rightPush ( key , "group1" );
//
//	}
//}
