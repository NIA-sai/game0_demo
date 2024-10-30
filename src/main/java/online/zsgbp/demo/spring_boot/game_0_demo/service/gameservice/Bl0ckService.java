
// this part is truly shit

package online.zsgbp.demo.spring_boot.game_0_demo.service.gameservice;

import lombok.Setter;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Bl0ck;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Gr0up;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Map0;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Rank0;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.Game0PageVo;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.Result;
import online.zsgbp.demo.spring_boot.game_0_demo.service.cacheService.Bl0ckCacheService;
import online.zsgbp.demo.spring_boot.game_0_demo.service.cacheService.Rank0CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
//%10==0 use seed to random generate
// DEFAULT num = -1 !!! NOT 0 !!!!
import java.time.LocalDateTime;
import java.util.*;

import static online.zsgbp.demo.spring_boot.game_0_demo.util.CreateStuff.*;

//holy *filtered* *filtered* *filtered* code that the past-me made
@Setter
@Service
public class Bl0ckService
{
	private static final byte[] xShift = new byte[] { 1 , -1 , 1 , -1 , 0 , 0 , 1 , -1 };
	private static final byte[] yShift = new byte[] { 1 , -1 , -1 , 1 , 1 , -1 , 0 , 0 };
	
	@Autowired
	private Bl0ckCacheService bl0ckCacheService;
	@Autowired
	private Rank0CacheService rank0CacheService;
	@Autowired
	private RedisTemplate < String, Object > redisRank;
	
	
	private int map0Id;//do not let mapId be one more method parameter ever !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private Map0 map0;//todo<<remember set !
	private String seed;
	
	@Value ( "${game0.bl0ck.map0.default-view-size}" )
	private int viewSize;
	@Value ( "${game0.bl0ck.every-independent0-get-score}" )
	private int everyIndependent0getScore;
	
	public Bl0ckService setSize ( int size )
	{
		this.viewSize = size;
		return this;
	}
	
	public Result setMap0Id ( int map0Id , int usersId , Integer size , Boolean autoCreateMap0 )
	{
		this.map0 = bl0ckCacheService.getMap0ById ( map0Id );
		if ( this.map0 == null )
		{
			if ( autoCreateMap0 == null ) autoCreateMap0 = true;
			if ( autoCreateMap0 ) createMap0 ( map0Id , usersId , size , null );
				//throw new RuntimeException ( "Map0 not found" );
			else return Result.failure ( 404 , "Map0:"+map0Id+" not found, try to use/create or add a autoCreateMap0 flag with value true " );
		}
		//if ( size != null && size != 0 ) this.viewSize = size;
		this.seed = this.map0.getSeed ();
		this.map0Id = map0Id;
		return Result.success ( this );
	}
	
	public Result createMap0 ( int map0Id , int usersId , Integer size , String seed )
	{
		if ( map0Id <= 0 ) return Result.failure ( 401 , "map0Id must be positive" );
		if ( bl0ckCacheService.getMap0ById ( map0Id ) != null )
			return Result.failure ( 401 , "map0 "+map0Id+"already exists, try to use/bl0ck/"+map0Id+"/  to play!!" );
		if ( seed == null ) this.seed = generateSeed ( usersId , LocalDateTime.now ().toString () );
		else this.seed = seed;
		this.map0 = new Map0 ( map0Id , usersId , this.seed , LocalDateTime.now () , 0 );
		bl0ckCacheService.addMap0 ( this.map0 );
		if ( size == null ) size = this.viewSize;
		return Result.success ( "success awa , exactly you can use/bl0ck/"+map0Id+"/  to play if you didn't set the autoCreateMap0 with false!!" , view ( 0 , 0 , size ) );
	}
	
	public Result chooseBl0ck ( long x , long y , int usersId )
	{
		load ( x , y , viewSize );
		Bl0ck bl0ck = get ( x , y );
		if ( !bl0ck.getChoosable () )
			return Result.suspect ( 450 , "can't be chosen yet" , bl0ck );
		else
		{//every time refresh the round bl0cks ' choosable in the following method
			//todo "check
			return checkAround ( x , y , usersId );
		}
	}
	
	
	public Result checkAround ( long x , long y , int usersId )
	{
		Set < Bl0ck > ar0unds = new HashSet <> ();
		//Map < String, Bl0ck > differ0s = new TreeMap < String, Bl0ck > ();//now i'd rather call it differ-from-normal-ar0und-s. eh
		Set < Bl0ck > differ0s = new HashSet <> ();
		for ( byte k = 0 ; k < 8 ; k++ )//pre-operate with the around bl0cks
		{
			long sx = x+xShift[k];
			long sy = y+yShift[k];
			Bl0ck bl0ck = get ( sx , sy );
			
			ar0unds.add ( bl0ck );
			if ( k > 3 /*&& bl0ck.get ().getNum () == 0*/ ) differ0s.add ( bl0ck );
		}
		//this for generate the recently chosen bl0ck's num
		int sum = ar0unds.stream ()
				.map ( Bl0ck :: getNum ).filter ( num -> num != ( byte ) -1 )
				.map ( Byte :: intValue ).reduce ( 0 , Integer :: sum )%10;
		if ( sum == 0 ) //you create a 0
		{
			//change the choosable in the followed method
			return checkAr0undDiffer0 ( x , y , differ0s , usersId );
		}
		//you create a none 0 ,so now change the choosable
		for ( Bl0ck ar0und : ar0unds )//reset the around 8 bl0cks' choosable
		{
			if ( ar0und.getNum () == ( byte ) -1 )
				update ( ar0und.setChoosable ( true ) );
		}
		update ( new Bl0ck ( x , y , ( byte ) sum , 0 , 0 , false ) );
		return Result.success ( "create a "+sum+" ,score hasn't changed" , view ( x , y , 1 ) );
	}
	
	
	/**
	 * the most *filtered* and tedious part
	 *
	 * @return the result of a war or just a simple territorial expansion
	 */
	public Result checkAr0undDiffer0 ( long x , long y , Set < Bl0ck > differ0s , int usersId )//todo with differ0 <
	{
		Set < Integer > all0Gr0ups = new HashSet <> ();
		for ( Bl0ck differActuallyNotAbsolutely0 : differ0s )//// what a  *filtered* long valuable name
		{
			if ( differActuallyNotAbsolutely0.getNum () == -1 )// handled the num==-1 bl0cks , make it choosable
				update ( differActuallyNotAbsolutely0.setChoosable ( true ) );
			if ( differActuallyNotAbsolutely0.getNum () == 0 )//get 0 block 's true group id
			{
				int ancestorGr0upId = getAncestorGr0upId ( differActuallyNotAbsolutely0.getGr0upId () );
				//i'm updating his ancestor as his father there simultaneously
				if ( ancestorGr0upId != differActuallyNotAbsolutely0.getGr0upId () )
					bl0ckCacheService.updateGr0up ( bl0ckCacheService.getGr0upById (
							differActuallyNotAbsolutely0.getGr0upId () , map0Id ).setFatherId ( ancestorGr0upId ) );
				all0Gr0ups.add ( ancestorGr0upId );
			}
		}
		if ( all0Gr0ups.isEmpty () )//no 0s around
		{//new isolated bl0ck with num == 0 , --> create a new group//  score+=100
			int newGroupId = this.map0.getBiggestGr0upId ()+1;
			update ( new Bl0ck ( x , y , ( byte ) 0 , newGroupId , usersId , false ) );
			bl0ckCacheService.addGr0up ( new Gr0up ( newGroupId , usersId , 1 , map0Id ) );//this.fatherId=newGroupId;
			bl0ckCacheService.updateMap0 ( map0 , newGroupId );
			return Result.success ( 0 , "create a new independent 0 ,score+"+everyIndependent0getScore , new Game0PageVo ().setSize ( 1 )
					.setViews ( view ( x , y , 1 ) ).setRank0 ( changeRank0 ( usersId , everyIndependent0getScore ) ) );//todo +score 100 ?there
		}
		else
		{
			int winnerGroupId = Integer.MAX_VALUE,
					winnerGBCountBinDigitSum = 0,
					all0CountSum = 1,///you have chose and create a 0
					usersCountSum = 1,
					usersMinGroupId = Integer.MAX_VALUE;
			for ( Integer gr0upId : all0Gr0ups )
			{
				Gr0up gr0up = bl0ckCacheService.getGr0upById ( gr0upId , map0Id );//get the whole true leader group's detail int the Gr0up.class
				int GBCount = gr0up.getBl0ckCount ();
				all0CountSum += GBCount;
				if ( gr0up.getUsersId () == usersId )
				{
					usersCountSum += GBCount;
					usersMinGroupId = Math.min ( usersMinGroupId , gr0upId );
					continue;
				}
				int GBCountBinDigitSum = binDigitSum ( GBCount );
				if ( GBCountBinDigitSum > winnerGBCountBinDigitSum || ( GBCountBinDigitSum == winnerGBCountBinDigitSum && gr0upId < winnerGroupId ) )
				{
					winnerGroupId = gr0upId;
					winnerGBCountBinDigitSum = GBCountBinDigitSum;
				}
			}
			//handle if the user's bl0cks meet  together by this choice
			int userCountSumBinDigitSum = binDigitSum ( usersCountSum );
			if ( userCountSumBinDigitSum > winnerGBCountBinDigitSum || ( userCountSumBinDigitSum == winnerGBCountBinDigitSum && usersMinGroupId < winnerGroupId ) )
				winnerGroupId = usersMinGroupId;
			//
			if ( winnerGroupId == Integer.MAX_VALUE ) throw new RuntimeException ( "Find winner Gr0upId failed !" );
			int winnerId = bl0ckCacheService.getGr0upById ( winnerGroupId , map0Id ).getUsersId (),
					deltaScore = -1, selfCount = 0;
			for ( Integer gr0upId : all0Gr0ups )
			{
				if ( gr0upId == winnerGroupId )
				{
					selfCount = bl0ckCacheService.getGr0upById ( gr0upId , map0Id ).getBl0ckCount ();
					all0CountSum -= selfCount;
					bl0ckCacheService.updateGr0up ( new Gr0up ( gr0upId , winnerId , all0CountSum , map0Id ) );//update the bl0ck count
				}
				else
				{//failure score-- and his group regard the enemy as kith and kin
					Gr0up failureGr0up = bl0ckCacheService.getGr0upById ( gr0upId , map0Id );//however , it's failure bl0cks' group
					changeRank0 ( failureGr0up.getUsersId () , -failureGr0up.getBl0ckCount () );//if it's own to the winner user it also need to--
					bl0ckCacheService.updateGr0up ( failureGr0up.setBl0ckCount ( 0 ).setFatherId ( winnerGroupId ) );//update the bl0ck count
				}
			}
			update ( new Bl0ck ( x , y , ( byte ) 0 , winnerGroupId , winnerId , false ) );
			if ( winnerId == usersId )
			{
				all0CountSum -= 2;//as the score//because in the beginning all0CountSum=1 and now it need to -1
				usersCountSum-=selfCount;
			}
			//for other's win more 2 score , first it don't need to -1 (this meanwhile indicate it's not more 2) secondly , it won the new 0 user create to +1
			Rank0 winnerRank0 = changeRank0 ( winnerId , all0CountSum );
			if ( winnerId == usersId && usersCountSum == all0CountSum+2 )//add back to verify ,even more stupid
			{
				return Result.suspect ( 233 , "you create a dependence 0 score--" ,
						new Game0PageVo ()
								.setSize ( viewSize )//now unnecessary but not ever
								.setViews ( view ( x , y , viewSize ) ).setRank0 ( winnerRank0 ) );
			}
			else
			{
				if ( winnerId == usersId )
				{
					return Result.success ( "you won "+deltaScore+" bl0cks" ,
							new Game0PageVo ()
									.setRank0 ( winnerRank0 )
									.setViews ( view ( x , y , viewSize ) )
					);
				}
				else
				{
					return Result.suspect ( 555 , "why would you choose here qwq \n all the bl0cks are now player0"+winnerId+"'s" ,
							new Game0PageVo ().setViews ( view ( x , y , viewSize ) )
							//				.setRank0 ( winnerRank0 ) //should i show the other winner?
					);
				}
			}
		}
	}
	
	
	public int getAncestorGr0upId ( int gr0upId )
	{
		if ( gr0upId == 0 )
		{
			throw new RuntimeException ( "Find ancestor Gr0upId failed absolutely the groupId now is 0" );
		}
		int fatherId = bl0ckCacheService.getGr0upById ( gr0upId , map0Id ).getFatherId ();
		if ( gr0upId == fatherId ) return gr0upId;
		return getAncestorGr0upId ( fatherId );
	}
	
	
	public Bl0ck get ( long x , long y )//just for shorter name
	{
		//Optional < Bl0ck > bl0ck = bl0ckRepository.findByMapIdAndXAndY ( map0Id , x , y );//<<<<<<<<<<<<<<<will findByXxAndYy works?
		Bl0ck bl0ck = bl0ckCacheService.getBl0ckByCoordinate ( x , y , map0Id );
		if ( bl0ck == null )
		{
			if ( ( Math.abs ( x )%10 == 0 ) && ( Math.abs ( y )%10 == 0 ) ) bl0ck = bl0ckInitial ( x , y );
			else bl0ck = new Bl0ck ( x , y , ( byte ) -1 , 0 , 0 , false );
			save ( bl0ck );
		}
		return bl0ck;
	}
	
	public void save ( Bl0ck bl0ck )/////////////////////////use save to set mapId!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
	{
		//bl0ckRepository.save ( bl0ck.setMapId ( map0Id ) );//this set the bl0ck's unique id simultaneously
		bl0ckCacheService.addBl0ck ( bl0ck.setMapId ( map0Id ) );
	}
	
	public void update ( Bl0ck bl0ck )
	{
		bl0ckCacheService.updateBl0ck ( bl0ck.setMapId ( map0Id ) );
	}
	
	public Set < Bl0ck > view ( long x , long y , Integer size )//there size actually is nearly 1/2 size
	{
		if ( size == null ) size = this.viewSize;
		size = size > 0 ? size : -size;///
		Set < Bl0ck > views = new HashSet <> ();
		for ( int i = 1 ; i <= size ; i++ )
			for ( int j = 1 ; j <= size ; j++ )
				for ( byte k = 0 ; k < 4 ; k++ )
				{
					long sx = x+i*xShift[k];
					long sy = y+j*yShift[k];
					views.add ( get ( sx , sy ) );
				}
		for ( int i = -size ; i <= size ; i++ )//to avoid repeated get
		{
			views.add ( get ( x , y+i ) );
			views.add ( get ( x+i , y ) );
		}
		return views;
	}
	
	public void load ( long x , long y , int size )//repeated code
	{
		size = size > 0 ? size : -size;///todo delete
		for ( int i = 1 ; i <= size ; i++ )
			for ( int j = 1 ; j <= size ; j++ )
				for ( byte k = 0 ; k < 4 ; k++ )
				{
					long sx = x+i*xShift[k];
					long sy = y+j*yShift[k];
					get ( sx , sy );
				}
		for ( int i = -size ; i <= size ; i++ )//to avoid repeated get
		{
			get ( x , y+i );
			get ( x+i , y );
		}
	}
	
	//there for supper stupid one
	public Bl0ck bl0ckInitial ( long x , long y )//only %10==0 can use this
	{
		//to generate a num maybe shouldn't be here
		byte num = ( byte ) ( this.seed.charAt ( ( ( string2IntegerByAscii ( "0"+x+"0"+y+"0" ) ) )%this.seed.length () )%9+1 );
		//to nitialize the around bl0cks
		for ( byte k = 0 ; k < 8 ; k++ )//pre-operate with the around bl0cks
		{
			long sx = x+xShift[k];
			long sy = y+yShift[k];
			Bl0ck bl0ck = get ( sx , sy );
			if ( bl0ck.getNum () == -1 && !bl0ck.getChoosable () )
			{
				update ( bl0ck.setChoosable ( true ) );
			}
		}
		return new Bl0ck ( x , y , num , 0 , 0 , false );
	}
	
	public Rank0 changeRank0 ( int usersId , int delta )
	{
		Rank0 rank0 = rank0CacheService.getRank0 ( usersId , this.map0Id );
		if ( rank0 == null )
		{
			rank0 = new Rank0 ( usersId , 0L , this.map0Id );
			redisRank.opsForZSet ().add ( "map0"+this.map0Id+":rank00" , usersId , 0 );
			rank0 = rank0.setRank ( redisRank.opsForZSet ().reverseRank ( "map0"+this.map0Id+":rank00" , usersId ) );
			rank0CacheService.addRank0 ( rank0 );
		}
		else
		{
			redisRank.opsForZSet ().add ( "map0"+this.map0Id+":rank00" , usersId , rank0.getSc0re ()+delta );
			rank0 = rank0.setSc0re ( rank0.getSc0re ()+delta )
					.setRank ( redisRank.opsForZSet ().reverseRank ( "map0"+this.map0Id+":rank00" , usersId ) );
			rank0CacheService.updateRank0 ( rank0 );
		}
		return rank0;
	}
	
	
	/**
	 * useless  method
	 **/
	//	public Result uselessView ( long x , long y , Integer size )
	//	{
	//		Map < String, String > uselessViews = new TreeMap <> ();
	//		if ( size == null || size == 0 ) return Result.failure ( 401 , "set a valid size please" );
	//		StringBuilder sb = new StringBuilder ();
	//		for ( long sx = x-size ; sx <= x+size ; sx++ ) sb.append ( String.format ( "%8s" , ""+sx ) );
	//		uselessViews.put ( "XXXXXXXX" , sb.toString () );
	//		for ( long sy = y+size ; sy >= y-size ; sy-- )
	//		{
	//			String head = String.format ( "%4s" , ""+sy );
	//			StringBuilder sb1 = new StringBuilder ();
	//			for ( long sx = x-size ; sx <= x+size ; sx++ )
	//			{
	//				Bl0ck bl0ck = get ( sx , sy );
	//				if ( bl0ck.getNum () == -1  )
	//				{
	//					if ( bl0ck.getChoosable () ) sb1.append ( String.format ( "%8s" , "@" ) );
	//					else sb1.append ( String.format ( "%8s" , "#" ) );
	//				}
	//				else sb1.append ( String.format ( "%8s" , ""+bl0ck.getNum () ) );
	//			}
	//			uselessViews.put ( "line"+head , sb1.toString () );
	//		}
	//		return Result.success ( uselessViews );
	//	}
	//even rewrite once
	public Result uselessView ( long x , long y , Integer size )
	{
		List < String > uselessViews = new ArrayList <> ();
		uselessViews.add ( "Don't recommend using this at all, but anyway @ means you can choose it and # not" );
		if ( size == null || size == 0 ) return Result.failure ( 401 , "set a valid size please" );
		StringBuilder sb = new StringBuilder ( "XXXXXXXX" );
		for ( long sx = x-size ; sx <= x+size ; sx++ ) sb.append ( String.format ( "%4s" , ""+sx ) );
		uselessViews.add ( sb.toString () );
		//to initial  bl0ck x,y%10==0 just avoid first use uselessView
		for ( long sx = ( x-size )/10*10 ; sx <= ( x+size )/10*10 ; sx += 10 )
			for ( long sy = ( y-size )/10*10 ; sy <= ( y+size )/10*10 ; sy += 10 )
				get ( sx , sy );
		//truly start add to list
		for ( long sy = y+size ; sy >= y-size ; sy-- )
		{
			String head = String.format ( "%4s" , ""+sy );
			StringBuilder sb1 = new StringBuilder ();
			for ( long sx = x-size ; sx <= x+size ; sx++ )
			{
				Bl0ck bl0ck = get ( sx , sy );
				if ( bl0ck.getNum () == -1 )
				{
					if ( bl0ck.getChoosable () ) sb1.append ( String.format ( "%4s" , "@" ) );
					else sb1.append ( String.format ( "%4s" , "#" ) );
				}
				else sb1.append ( String.format ( "%4s" , ""+bl0ck.getNum () ) );
			}
			uselessViews.add ( "YYYY"+head+sb1 );
		}
		return Result.success ( uselessViews );
	}
	
}
