package online.zsgbp.demo.spring_boot.game_0_demo.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.dto.UserForm;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.dto.request.UserRequest;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.dto.request.RegisterRequest;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.dto.request.UpdateRequest;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.Result;
import online.zsgbp.demo.spring_boot.game_0_demo.filter.AuthFilter;
import online.zsgbp.demo.spring_boot.game_0_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

import static online.zsgbp.demo.spring_boot.game_0_demo.util.CreateStuff.HSR2Ip;
import static online.zsgbp.demo.spring_boot.game_0_demo.util.Jwt.filterToken;

@RestController
@RequestMapping ( "/user" )
public class UserController
{
	@Setter
	@Autowired
	private UserService userService;
	
	@Value ( "${user.domain}" )
	String domain;
	@Value ( "${user.auto-login}" )//can't use in method valuable
	boolean autoLogin;
	@Value ( "${jwt.token-expire}" )
	int tokenInCookieExpire;
	
	//for this spaghetti , i got unpack the Result.class and re-pack it up just for returning different message indeed kind of stupid but , it's spaghetti too in the Service.class, that made me even would prefer typing here which appear me with a more stupid look . fatigue e e e
	//what else, returning the token may also not be in the controller?
	@PostMapping ( "/regist" )
	public ResponseEntity < Result > userRegister ( HttpServletRequest requestIp , @RequestBody RegisterRequest registRequest , HttpServletResponse response )
	{
		Result registerResponse = userService.registerUser ( registRequest.getName () , registRequest.getEmail () , registRequest.getPassword () , registRequest.getConfirm () );
		if ( registRequest.getAutoLogin () != null ) autoLogin = registRequest.getAutoLogin ();
		if ( registerResponse.getCode () < 400 && autoLogin )
		{
			Result loginResponse = userService.loginUser ( registRequest.getName () , registRequest.getPassword () , HSR2Ip ( requestIp ) );
			if ( loginResponse.getCode () < 400 )
			{
				Cookie cookie = new Cookie ( "token" , ( ( UserForm ) loginResponse.getData () ).getUserDetail ().getToken () );//ha??it could be like this?? i just try coz i don't want to rewrite the service
				cookie.setHttpOnly ( true );//so this now be a joke//or may be i should deprecate the UserDetail.class?
				cookie.setPath ( "/" );
				cookie.setMaxAge ( tokenInCookieExpire );
				response.addCookie ( cookie );
				return ResponseEntity.ok ( ( Result.success ( "register and login success" , loginResponse.getData () ) ) );
			}
			else
				return ResponseEntity.status ( 555 ).body ( Result.suspect ( 555 , "register success but login failed" , registerResponse.getData () ) );//i try to use suspect result but that may be untidy?
		}
		if ( registerResponse.getCode () < 400 ) return ResponseEntity.ok ( Result.success ( "register success (success awa) but didn't login,this is your choice " , registerResponse.getData () ) );
		return ResponseEntity.ok ( registerResponse );
	}
	
	@PostMapping ( "/login" )
	public ResponseEntity < Result > userLogin ( HttpServletRequest requestIp , @RequestBody UserRequest loginRequest , HttpServletResponse response )
	{
		Result loginResponse = userService.loginUser ( loginRequest.getNameOrEmail () , loginRequest.getPassword () , HSR2Ip ( requestIp ) );
		if ( loginResponse.getCode () < 400 )
		{
			Cookie cookie = new Cookie ( "token" , ( ( UserForm ) loginResponse.getData () ).getUserDetail ().getToken () );
			cookie.setHttpOnly ( true );//repeated coed whatever ,i have given it a try
			cookie.setPath ( "/" );
			cookie.setMaxAge ( tokenInCookieExpire );
			response.addCookie ( cookie );
			return ResponseEntity.ok ( loginResponse );
		}
		return ResponseEntity.status ( 401 ).body ( loginResponse );
	}
	
	//should i get the token store in db?//i didn't
	@PostMapping ( "/logout" )
	public Result userLogout ( HttpServletRequest request  )
	{
		return userService.logoutUser ( AuthFilter.nameHolder.get () , HSR2Ip ( request ) );////finally i'd think name is needed//ultimately not
	}
	
	//is logined + 24;//got it
	@PostMapping ( "/delete" )
	public Result userDelete ( HttpServletRequest request , @RequestBody UserRequest deleteRequest )//just don't want write a new request.class
	{
		return userService.deleteUser ( deleteRequest.getNameOrEmail () , deleteRequest.getPassword () , HSR2Ip ( request ) );
	}
	
	@PostMapping ( "/update/name" )
	public Result updateUserName ( HttpServletRequest request , @RequestBody UpdateRequest updateRequest )
	{
		return userService.updateUserName ( AuthFilter.nameHolder.get () , updateRequest.getUpdateInfo () );//there token just for get original name//no i decided to give the work to front-end buddies to fit in//no, don't give to front-end
	}
	
	@PostMapping ( "/update/password" )
	public Result updatePassword ( HttpServletRequest request , @RequestBody UpdateRequest updateRequest )//maybe there is too much repeated filter
	{
		return userService.updatePassword ( filterToken ( request ) , updateRequest.getOriginalInfo () , updateRequest.getUpdateInfo () , updateRequest.getConfirm () );//there leave it as it to double make sure the security , ehh literally double
	}
	
	@PostMapping ( "/update/sign" )
	public Result updateSign ( HttpServletRequest request , @RequestBody UpdateRequest updateRequest )
	{
		return userService.updateUserSign ( AuthFilter.nameHolder.get () , updateRequest.getUpdateInfo () );
	}
	
	@PostMapping ( "/ask" )//use ask1,ask2,ask3... to get the result
	public Result isLogined ( @RequestBody Map < String, String > asks )
	{
		Map < String, Result > results = new TreeMap <> ();
		asks.forEach ( ( ask , name ) ->
		{
			if ( ask.startsWith ( "ask" ) ) results.put ( "result"+ask.substring ( 3 ) , userService.isLoginedAsk ( name ) );
		} );
		if ( results.isEmpty () ) return Result.failure ( 404 , "no ask" );
		return Result.success ( results );
	}
	
	@GetMapping ( "/info" )
	public Result getUserInfo ( HttpServletRequest request , @RequestParam ( value = "askRank", required = false ) String rankName )
	{
		return userService.getUserInfo ( AuthFilter.idHolder.get () , rankName );
	}
	
}
