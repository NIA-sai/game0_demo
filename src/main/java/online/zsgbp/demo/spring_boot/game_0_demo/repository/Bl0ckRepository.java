package online.zsgbp.demo.spring_boot.game_0_demo.repository;

import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.game0.Bl0ck;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
@Deprecated
public interface Bl0ckRepository extends CrudRepository < Bl0ck, String >
{
	List < Bl0ck > findAllByMapId ( int mapId );
	
	List < Bl0ck > findAllByMapIdAndGroupId ( int mapId , int groupId );
	
	Optional <Bl0ck> findByMapIdAndXAndY ( int mapId , long x , long y );
}
