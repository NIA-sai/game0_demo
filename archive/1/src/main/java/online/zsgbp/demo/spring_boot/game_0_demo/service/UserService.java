package online.zsgbp.demo.spring_boot.game_0_demo.service;

import online.zsgbp.demo.spring_boot.game_0_demo.entity.dto.UserDetail;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.dto.UserForm;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Logined;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.Rank;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.User;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.LoginedPageVo;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.Result;
import online.zsgbp.demo.spring_boot.game_0_demo.entity.vo.UserPageVo;
import online.zsgbp.demo.spring_boot.game_0_demo.filter.AuthFilter;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.rankmapper.OtherRankMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.rankmapper.RankMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.usermapper.KeyMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.usermapper.LoginedMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.usermapper.UserMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.util.CreateStuff;
import online.zsgbp.demo.spring_boot.game_0_demo.util.Encrypt;
import online.zsgbp.demo.spring_boot.game_0_demo.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static online.zsgbp.demo.spring_boot.game_0_demo.util.CreateStuff.checkName;
import static online.zsgbp.demo.spring_boot.game_0_demo.util.CreateStuff.checkPassword;

@Service
public class UserService
{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private KeyMapper keyMapper;
	@Autowired
	private LoginedMapper loginedMapper;
	@Autowired
	private Jwt jwt;//can't be static yyy
	
	@Value ( "${user.auto-logout-time}" )
	private int autoLogoutTime;
	
	//re-write times: 2
	public boolean isLogined ( String name )
	{
		Logined logined = loginedMapper.getLatestLoginedByUserId ( userMapper.getIdByName ( name ) );
		if ( logined == null || !logined.getLogInOrOut () ) return false;
		if ( Duration.between ( logined.getTime () , LocalDateTime.now () ).toSeconds () >= autoLogoutTime )
		{
			logined.setLogInOrOut ( false );
			logined.setTime ( LocalDateTime.now () );
			loginedMapper.addLogined ( logined );
			return false;
		}
		return true;
	}//could it be here?
	
	@Deprecated
	public Result checkUser ( String token )
	{
		String trueName = jwt.validateToken ( token );
		if ( trueName == null || !userMapper.isNameExist ( trueName ) )
			return Result.failure ( 401 , "token is invalid or "+trueName+" has not registered yet or ? suspiciously o.0" );
		if ( !isLogined ( trueName ) )
			return Result.suspect ( 555 , "please login first, MoM" , trueName );
		if ( jwt.isTokenExpired ( token ) )
		{
			loginedMapper.addLogined ( new Logined ( userMapper.getUserByName ( trueName ) , "auto-logout" , false ) );
			return Result.suspect ( 501 , "token is expired please re-login to get a new one" , trueName );
		}
		return Result.success ( trueName );
	}
	
	//I inexplicitly learn the Optional<>  ?? , but i feel tired to simple these spaghetti codes
	public Result registerUser ( String name , String email , String password , String confirm )
	{
		if ( name == null )
			return Result.failure ( 400 , "please name a name. " );
		if ( name.length () > 100 )
			return Result.failure ( 400 , "name too long,max:100  ^_^ " );
		if ( checkName ( name ) )
			return Result.failure ( 400 , "name contains illegal characters" );
		if ( userMapper.isNameExist ( name ) )
			return Result.failure ( 401 , "> I guess great minds really do think alike.  HEART ~ <3 \n>the name has been used" );
		if ( email != null && email.length () > 100 )
			return Result.failure ( 400 , "email too long,max:100  ^_^ " );
		if ( userMapper.isEmailExist ( email ) )
			return Result.failure ( 401 , "email has been used" );
		if ( password == null )
			return Result.failure ( 400 , "please set a password" );
		if ( password.length () > 100 )
			return Result.failure ( 400 , "password too long,max:50 v.v" );
		if ( checkPassword ( password ) )
			return Result.failure ( 400 , "password contains illegal characters" );
		if ( confirm == null )
			return Result.failure ( 400 , "please confirm your password" );
		if ( !password.equals ( confirm ) )
			return Result.failure ( 400 , "password not match" );
		User user = new User ();
		user.setName ( name );
		user.setEmail ( email );
		user.setsKeys ( password+name );
		if ( !keyMapper.isKeyExist ( user.getKeysId () ) )
			keyMapper.addKey ( user.getKeysId () , CreateStuff.key2Code ( user.getKeysId () ) );
		String code = keyMapper.getKeyCodeById ( user.getKeysId () );
		user.setsPassword ( password , code );
		userMapper.addUser ( user );
		user = userMapper.getUserByName ( name );
		UserPageVo userPageVo = new UserPageVo ( user );
		return Result.success ( "success awa " , /*new UserForm ( */userPageVo /*, userDetail )*/ );//please take down your token coz i'm stupid 0.0//only i'm stupid
	}
	
	// whole re-write times :3
	public Result deleteUser ( String name , String password , String ip )//name: name or email
	{
		if ( name == null ) return Result.failure ( 400 , "you need a name or email to delete your account." );
		if ( password == null ) return Result.failure ( 400 , "you need a password to delete your account." );
		if ( !userMapper.isEmailExist ( name ) && !userMapper.isNameExist ( name ) )
			return Result.failure ( 404 , "nonexistent name or email. " );
		User user;
		if ( name.contains ( "@" ) )
			user = userMapper.getUserByEmail ( name );
		else
			user = userMapper.getUserByName ( name );
		String code = keyMapper.getKeyCodeById ( user.getKeysId () );
		if ( Encrypt.B.Check ( user , password , code , user.getPassword () ) )
		{//to do delete
			Logined logined = loginedMapper.getLatestLoginedByUserId ( userMapper.getIdByName ( name ) );
			if ( logined != null )
				if ( logined.getLogInOrOut () )
				{
					logined.setLogInOrOut ( false );
					logined.setIp ( ip );
					logined.setTime ( LocalDateTime.now () );//logined.class may need to re-write setter
					loginedMapper.addLogined ( logined );
				}
			userMapper.deleteUser ( user.getId () );
			loginedMapper.deleteLogined ( user.getId () );
			return Result.success ( "TAT don't leave me alone" , new UserPageVo ( user ) );
		}
		return Result.failure ( 400 , "wrong password" );
	}
	
	public Result loginUser ( String name , String password , String ip )
	{
		if ( !userMapper.isEmailExist ( name ) && !userMapper.isNameExist ( name ) )
			return Result.failure ( 404 , name+" hasn't register yet" );
		User user;
		if ( name.contains ( "@" ) )
			user = userMapper.getUserByEmail ( name );
		else
			user = userMapper.getUserByName ( name );
		String code = keyMapper.getKeyCodeById ( user.getKeysId () );
		if ( Encrypt.B.Check ( user , password , code , user.getPassword () ) )
		{
			String token = jwt.generateToken ( name );
			UserPageVo userPageVo = new UserPageVo ( user );
			UserDetail userDetail = new UserDetail ( token , ip );
			if ( isLogined ( name ) )
				return Result.suspect ( 399 , "> the "+name+" is  logged in again, and i don't know what wired happened \n but i refresh your token >uwu " , new UserForm ( userPageVo , userDetail ) );
			loginedMapper.addLogined ( new Logined ( user , ip , true ) );
			return Result.success ( "success awa " , new UserForm ( userPageVo , userDetail ) );
		}
		return Result.failure ( 400 , "wrong password" );
	}
	
	public Result logoutUser ( String name , String ip )
	{
		
		Logined logined = loginedMapper.getLatestLoginedByUserId ( userMapper.getIdByName ( name ) );
		if ( logined == null )
			return Result.failure ( 404 , name+" has not logged in ever" );//delete this?<<  //to avoid null pointer
		if ( !logined.getLogInOrOut () )
			return Result.failure ( 408 , "the "+name+" has already logged out.  Org   \n ///U v U///" );//<<too?  //WTF this expression // o o o
		logined.setLogInOrOut ( false );
		logined.setIp ( ip );
		logined.setTime ( LocalDateTime.now () );
		loginedMapper.addLogined ( logined );
		return Result.success ( new LoginedPageVo ( logined ) );
	}
	
	public Result isLoginedAsk ( String name )
	{
		if ( !userMapper.isNameExist ( name ) ) return Result.failure ( 404 , name+" has not registered yet" );
		Logined logined = loginedMapper.getLatestLoginedByUserId ( userMapper.getIdByName ( name ) );
		if ( logined == null )
		{
			UserPageVo userPageVo = new UserPageVo ( userMapper.getUserByName ( name ) );
			userPageVo = userPageVo.setLastInteractiveTime ( null );
			return Result.suspect ( 299 , name+" has not logged in ever" , userPageVo );
		}
		if ( !logined.getLogInOrOut () )
		{
			UserPageVo userPageVo = new UserPageVo ( userMapper.getUserByName ( name ) );
			return Result.suspect ( 299 , "the "+name+" is offline" , userPageVo.setLogined ( logined ) );
		}
		return Result.success ( "the "+name+" is oline \n success awa" , new LoginedPageVo ( logined ) );
	}
	
	
	public Result updateUserName ( String name , String newName )
	{
		if ( newName.length () > 100 )
			return Result.failure ( 400 , "name too long" );
		User user = userMapper.getUserByName ( name );
		if ( name.equals ( newName ) )
			return Result.success ( "whatever" , new UserPageVo ( user ) );///////
		else if ( userMapper.isNameExist ( newName ) )
			return Result.failure ( 401 , "> I guess great minds really do really think alike. <3 <3 <3" );
		user.setName ( newName );
		userMapper.updateUser ( user );
		return Result.success ( new UserPageVo ( user ) );
	}
	
	public Result updatePassword ( String token , String password , String newPassword , String confirm )//too
	{
		if ( newPassword.length () > 50 )
			return Result.failure ( 400 , "new password too long,max:50 v.v" );
		if ( !newPassword.equals ( confirm ) )
			return Result.failure ( 408 , "new password do not match" );
		String name = jwt.validateToken ( token );
		User user = userMapper.getUserByName ( name );
		String code = keyMapper.getKeyCodeById ( user.getKeysId () );
		if ( !Encrypt.B.Check ( user , password , code , user.getPassword () ) )
			return Result.failure ( 400 , "wrong original password" );
		user.setsPassword ( newPassword , code );
		userMapper.updateUser ( user );
		return Result.success ( new UserPageVo ( user ) );
	}
	
	public Result updateUserSign ( String name , String newSign )
	{
		if ( newSign.length () > 255 )
			return Result.failure ( 400 , "sign too long,max:255  (,,,)" );
		User user = userMapper.getUserByName ( name );
		if ( user.getSign ().equals ( newSign ) )
			return Result.success ( "whatever, /nerd " , new UserPageVo ( user ) );///////
		user.setSign ( newSign );
		userMapper.updateUser ( user );
		return Result.success ( new UserPageVo ( user ) );
	}
	
	@Autowired
	RankMapper rank0Mapper;
	@Autowired
	OtherRankMapper guessNumberRankMapper;
	
	//stupid method
	public Result getUserInfo ( Integer id , String rankName )
	{
		User user = userMapper.getUserById ( id );
		UserPageVo userPageVo = new UserPageVo ( user );
		//todo rankservice latter
		if ( rankName == null ) return Result.success ( userPageVo );
		else
		{
			List < Rank > ranks = new ArrayList <> ();
			if ( rankName.equals ( "all" ) )
			{
				ranks.add ( guessNumberRankMapper.getRankGuessByUsersId ( id ) );
				ranks.addAll ( rank0Mapper.getAllRanks ( "rank0" , id ) );
			}
			else if ( rankName.equals ( "rankGuess" ) )
			{
				ranks.add ( guessNumberRankMapper.getRankGuessByUsersId ( id ) );
			}
			else if ( rankName.equals ( "rank0:all" ) )
			{
				ranks = new ArrayList <> ( rank0Mapper.getAllRanks ( "rank0" , id ) );
			}
			else if ( rankName.startsWith ( "rank0:" ) )
			{
				String[] map0IdStrs = rankName.substring ( 6 ).split ( "," );
				for ( String map0IdStr : map0IdStrs )
				{
					try
					{
						ranks.add ( rank0Mapper.getRank0 ( "rank0" , id , Integer.parseInt ( map0IdStr ) ) );
					}
					catch ( NumberFormatException e )
					{
						ranks.add ( null );
					}
				}
			}
			return Result.success ( userPageVo.addRank ( ranks ) );
		}
	}
}
