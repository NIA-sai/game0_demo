package online.zsgbp.demo.spring_boot.game_0_demo.service.cacheService;

import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Bl0ck;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Gr0up;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Map0;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.gamemapper.Bl0ckMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.gamemapper.Gr0upMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.gamemapper.Map0Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class Bl0ckCacheService implements Bl0ckCacheServiceInter
{
	@Autowired
	private Bl0ckMapper bl0ckMapper;
	@Autowired
	private Map0Mapper map0Mapper;
	@Autowired
	private Gr0upMapper gr0upMapper;
	@Autowired
	private RedisCacheManager redisCacheManager;
	
	@Cacheable ( value = "bl0ck", key = "'map0' + #map0Id + ':' + #x + ',' + #y" )
	@Override
	public Bl0ck getBl0ckByCoordinate ( long x , long y , int map0Id )
	{
//		Bl0ck bl0ck= bl0ckMapper.getBl0ckByCoordinate ( x , y , map0Id );
//		System.out.println (bl0ck);
//		return bl0ck;
		return bl0ckMapper.getBl0ckByCoordinate ( x , y , map0Id );
	}
	
	@CachePut ( value = "bl0ck", key = "'map0' + #bl0ck.map0Id + ':' + #bl0ck.x + ',' + #bl0ck.y" )
	@Override
	public Bl0ck addBl0ck ( Bl0ck bl0ck )
	{
		bl0ckMapper.addBl0ck ( bl0ck );
		return bl0ck;
	}
	
	@CachePut ( value = "bl0ck", key = "'map0' + #bl0ck.map0Id + ':' + #bl0ck.x + ',' + #bl0ck.y" )
	@Override
	public Bl0ck updateBl0ck ( Bl0ck bl0ck )
	{
		bl0ckMapper.updateBl0ck ( bl0ck );
		return bl0ck;
	}
	
	@CacheEvict ( value = "bl0ck", key = "'map0' + #map0Id + ':' + #x + ',' + #y" )
	@Override
	public void deleteBl0ck ( long x , long y , int map0Id )
	{
		return;
		//no delete no need
		//but maybe delete the cache is necessary
	}
	
	@Cacheable ( value = "map0", key = "MaxId" )
	public int getMaxMap0Id ()
	{
		Integer MaxMap0Id = map0Mapper.getMaxMap0Id ();
		if ( MaxMap0Id == null ) return 0;
		return MaxMap0Id;
	}
	
	
	@Cacheable ( value = "map0", key = "#map0Id" )
	@Override
	public Map0 getMap0ById ( Integer map0Id )
	{
		return map0Mapper.getMap0 ( map0Id );
	}
	
	@CachePut ( value = "map0", key = "#map0.id" )
	@Override
	public Map0 addMap0 ( Map0 map0 )
	{
		map0Mapper.addMap0 ( map0 );
		return map0;
	}
	
	@CachePut ( value = "map0", key = "#map0.id" )
	@Override
	public Map0 updateMap0 ( Map0 map0 , int biggestGroupId )
	{
		map0.setBiggestGr0upId (biggestGroupId);// stupid way to update
		map0Mapper.updateMap0 ( map0.getId () , biggestGroupId );
		return map0;
	}
	
	@CacheEvict ( value = "map0", key = "#map0Id" )
	@Override
	public void deleteMap0 ( int map0Id )
	{
		return;
	}
	
	@Cacheable ( value = "gr0up", key = "#gr0upId" )
	@Override
	public Gr0up getGr0upById ( int gr0upId , int map0Id )
	{
		return gr0upMapper.getGr0upById ( gr0upId , map0Id );
	}
	
	@CachePut ( value = "gr0up", key = "#gr0up.id" )
	@Override
	public Gr0up addGr0up ( Gr0up gr0up )
	{
		gr0upMapper.addGr0up ( gr0up );
		return gr0up;
	}
	
	@CachePut ( value = "gr0up", key = "#gr0up.id" )
	@Override
	public Gr0up updateGr0up ( Gr0up gr0up )
	{
		gr0upMapper.updateGr0up ( gr0up.getId () , gr0up.getFatherId () , gr0up.getUsersId () , gr0up.getBl0ckCount () , gr0up.getMap0Id () );
		return gr0up;
	}
	
	@CacheEvict ( value = "gr0up", key = "#gr0upId" )
	@Override
	public void deleteGr0up ( int gr0upId )
	{
		return;
	}
}
