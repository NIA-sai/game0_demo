package online.zsgbp.demo.spring_boot.game_0_demo.controller;


import jakarta.servlet.http.HttpServletRequest;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.dto.request.PlayBl0ckRequest;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.Result;
import online.zsgbp.demo.spring_boot.game_0_demo.filter.AuthFilter;
import online.zsgbp.demo.spring_boot.game_0_demo.service.gameservice.Bl0ckService;
import online.zsgbp.demo.spring_boot.game_0_demo.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ( "/bl0ck" )
public class Bl0ckController
{
	@Autowired
	private Bl0ckService bl0ckService;
	@Autowired
	private Jwt jwt;
	
	@Value ( "${game0.bl0ck.auto-create-map0}" )
	boolean autoCreateMap0;
	
	
	//create map
	@PostMapping ( "/{map0Id}" )
	public Result createMap0 ( HttpServletRequest request , @PathVariable String map0Id , @RequestBody PlayBl0ckRequest playBl0ckRequest )
	
	{
		try
		{
			return bl0ckService.createMap0 ( Integer.parseInt ( map0Id ) , AuthFilter.idHolder.get () , playBl0ckRequest.getSize () , playBl0ckRequest.getSeed () );//optional
		}
		catch ( NumberFormatException nfe )
		{
			return Result.failure ( 401 , "map0Id must be an int !  and i'm too stupid to use the global exception handler" );
		}
	}
	
	//play
	@PostMapping ( "/{map0Id}/" )
	public Result play ( HttpServletRequest request , @PathVariable String map0Id , @RequestBody PlayBl0ckRequest playBl0ckRequest )
	{
		try
		{
			Result result = bl0ckService.setMap0Id ( Integer.parseInt ( map0Id ) , AuthFilter.idHolder.get () , null , playBl0ckRequest.getAutoCreateMap0 () );
			if ( result.getCode () > 399 ) return result;
			else return ( ( Bl0ckService ) result.getData () )
					.chooseBl0ck ( playBl0ckRequest.getX () , playBl0ckRequest.getY () , AuthFilter.idHolder.get () );
		}
		catch ( NumberFormatException nfe )
		{
			return Result.failure ( 401 , "map0Id must be an int !  and i'm too stupid to use the global exception handler" );
		}
	}
	
	
	//view
	@PostMapping ( "/{map0Id}/view" )
	public Result getMap0 ( HttpServletRequest request , @PathVariable String map0Id , @RequestBody PlayBl0ckRequest playBl0ckRequest )
	{
		try
		{
			Result result = bl0ckService.setMap0Id ( Integer.parseInt ( map0Id ) , AuthFilter.idHolder.get () , null , playBl0ckRequest.getAutoCreateMap0 () );
			if ( result.getCode () > 399 ) return result;
			else return Result.success ( ( ( Bl0ckService ) result.getData () )
					.view ( playBl0ckRequest.getX () , playBl0ckRequest.getY () , playBl0ckRequest.getSize () ) );
			
		}
		catch ( NumberFormatException nfe )
		{
			return Result.failure ( 401 , "map0Id must be an int !  and i'm too stupid to use the global exception handler" );
		}
	}
	
	//	@Deprecated
	//	@GetMapping ( "/{map0Id}/set" )
	//	public Result setMap0Size ( HttpServletRequest request , @PathVariable String map0Id , @RequestParam int viewSize )
	//	{
	//		try
	//		{
	//			Result result = bl0ckService.setMap0Id ( Integer.parseInt ( map0Id ) , AuthFilter.idHolder.get () , null , false );
	//			if ( result.getCode () > 399 ) return result;
	//			else return Result.success ( ( ( Bl0ckService ) result.getData () )
	//					.setSize ( viewSize ) );
	//
	//		}
	//		catch ( NumberFormatException nfe )
	//		{
	//			return Result.failure ( 401 , "map0Id and size must be an int !  and i'm too stupid to use the global exception handler" );
	//		}
	//	}
	
	//shit
	@PostMapping ( "/{map0Id}/l00k" )
	public Result uselessView ( HttpServletRequest request , @PathVariable String map0Id , @RequestBody PlayBl0ckRequest playBl0ckRequest )
	{
		try
		{
			Result result = bl0ckService.setMap0Id ( Integer.parseInt ( map0Id ) , AuthFilter.idHolder.get () , null , playBl0ckRequest.getAutoCreateMap0 () );
			if ( result.getCode () > 399 ) return result;
			else return ( ( Bl0ckService ) result.getData () )
					.uselessView ( playBl0ckRequest.getX () , playBl0ckRequest.getY () , playBl0ckRequest.getSize () );
		}
		catch ( NumberFormatException nfe )
		{
			return Result.failure ( 401 , "map0Id must be an int !  and i'm too stupid to use the global exception handler" );
		}
	}
}


