package online.zsgbp.demo.spring_boot.game_0_demo.service.cacheService;


import jakarta.annotation.Resource;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Rank0;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.rankmapper.RankMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class Rank0CacheService
{
	private final String rankName = "rank0";
	@Resource
	private RankMapper rankMapper;
	
	
	@Cacheable ( value = "rank0", key = "'map0' + #map0Id + ':usersId:' + #usersId" )
	public Rank0 getRank0 ( int usersId , int map0Id )
	{
		try
		{
			Rank0 rank0 = rankMapper.getRank0 ( rankName , usersId , map0Id );
			if ( rank0 != null ) rank0.setRank ( ( long ) rankMapper.getRank0WithRank ( rankName , usersId , map0Id ) );
			return rank0;
		}
		catch ( Exception e )
		{
			e.printStackTrace ();
			return null;
		}
	}
	
	@CachePut ( value = "rank0", key = "'map0' + #rank0.map0Id + ':usersId:' + #rank0.usersId" )
	public Rank0 addRank0 ( Rank0 rank0 )
	{
		rankMapper.addRank0 ( rankName , rank0 );
		return rank0;
	}
	
	@CachePut ( value = "rank0", key = "'map0' + #rank0.map0Id + ':usersId:' + #rank0.usersId" )
	public Rank0 updateRank0 ( Rank0 rank0 )
	{
		System.out.println ( rank0 );
		rankMapper.updateRank0 ( rankName , rank0 );
		return rank0;
	}
	
	@CacheEvict ( value = "rank0", key = "'map0' + #map0Id + ':usersId:' + #usersId" )
	public void deleteRank0 ( int usersId , int map0Id )
	{
		return;
	}
}
