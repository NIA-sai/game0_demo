package online.zsgbp.demo.spring_boot.game_0_demo.service.cacheService;

import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Bl0ck;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Gr0up;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Map0;
//why we need this??
public interface Bl0ckCacheServiceInter
{
	public Bl0ck getBl0ckByCoordinate ( long x , long y , int map0Id );
	
	public Bl0ck addBl0ck ( Bl0ck bl0ck );
	
	public Bl0ck updateBl0ck ( Bl0ck bl0ck );
	
	public void deleteBl0ck ( long x , long y , int map0Id );
	
	public Map0 getMap0ById ( Integer map0Id );
	
	public Map0 addMap0 ( Map0 map0 );
	
	public Map0 updateMap0 ( Map0 map0 , int biggestGr0upId );
	
	public void deleteMap0 ( int map0Id );
	
	public Gr0up getGr0upById ( int gr0upId , int map0Id );
	
	public Gr0up addGr0up ( Gr0up gr0up );
	
	public Gr0up updateGr0up ( Gr0up gr0up );
	
	public void deleteGr0up ( int gr0upId );
}
