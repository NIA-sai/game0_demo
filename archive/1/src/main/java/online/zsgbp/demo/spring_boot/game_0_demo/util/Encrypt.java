package online.zsgbp.demo.spring_boot.game_0_demo.util;

import online.zsgbp.demo.spring_boot.game_0_demo.entity.po.user.User;
import org.springframework.util.DigestUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt
{
	public static String MD5 ( String str )
	{
		return DigestUtils.md5DigestAsHex ( str.getBytes () );
	}
	
	public static class B
	{
		public static String Crypt ( String str )
		{
			return BCrypt.hashpw ( str , BCrypt.gensalt () );
		}//e e
		
		public static boolean Check ( User user , String str , String code , String encode )
		{
			StringBuilder temp_str = new StringBuilder ( str );
			temp_str.insert ( user.getKeysId () > str.length () ? str.length () : user.getKeysId () , code );
			String raw = Encrypt.MD5 ( temp_str.toString () );
			String ripe = Encrypt.Sha256 ( raw );
			return BCrypt.checkpw ( ripe , encode );
		}
	}
	
	public static String Sha256 ( String str )
	{
		MessageDigest messageDigest;
		String encodeStr = "";
		try
		{
			messageDigest = MessageDigest.getInstance ( "SHA-256" );
			messageDigest.update ( str.getBytes ( StandardCharsets.UTF_8 ) );
			encodeStr = byte2Hex ( messageDigest.digest () );
		}
		catch ( NoSuchAlgorithmException e )
		{
			e.printStackTrace ();
		}
		return encodeStr;
	}
	
	private static String byte2Hex ( byte[] bytes )
	{
		StringBuilder stringBuilder = new StringBuilder ();
		String temp;
		for ( byte aByte : bytes )
		{
			temp = Integer.toHexString ( aByte&0xFF );
			if ( temp.length () == 1 )
				stringBuilder.append ( "0" );
			stringBuilder.append ( temp );
		}
		return stringBuilder.toString ();
	}
	
}
