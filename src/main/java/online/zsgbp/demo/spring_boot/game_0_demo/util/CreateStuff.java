package online.zsgbp.demo.spring_boot.game_0_demo.util;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.regex.Pattern;

public class CreateStuff//my stupid utils
{
	public static Long string2LongByAscii ( String str )
	{
		long s2l = 0L;
		for ( int i = 0 ; i < str.length () ; i++ )
			s2l += ( long ) str.charAt ( i )*( i+1 );
		return s2l;
	}
	
	public static Integer string2IntegerByAscii ( String str )
	{
		int s2l = 0;
		for ( int i = 0 ; i < str.length () ; i++ )
			s2l += ( int ) str.charAt ( i )*( i+1 );
		return s2l;
	}
	
	public static String key2Code ( Integer keyId )
	{
		Random ranACode = new Random ( string2LongByAscii ( LocalDateTime.now ()+keyId.toString () ) );
		StringBuilder sb = new StringBuilder ();
		sb.append ( "0000" );
		String numAndLetter = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int len = ranACode.nextInt ( 17 )+10;
		for ( int i = 0 ; i < len ; i++ )
			sb.append ( numAndLetter.charAt ( ranACode.nextInt ( 62 ) ) );
		return sb.toString ();
	}
	
	public static String HSR2Ip ( HttpServletRequest request )
	{
		String ipAddress = request.getHeader ( "X-Forwarded-For" );
		if ( ipAddress == null || ipAddress.isEmpty () || "unknown".equalsIgnoreCase ( ipAddress ) )
			ipAddress = request.getHeader ( "Proxy-Client-IP" );
		if ( ipAddress == null || ipAddress.isEmpty () || "unknown".equalsIgnoreCase ( ipAddress ) )
			ipAddress = request.getHeader ( "WL-Proxy-Client-IP" );
		if ( ipAddress == null || ipAddress.isEmpty () || "unknown".equalsIgnoreCase ( ipAddress ) )
			ipAddress = request.getRemoteAddr ();
		return ipAddress;
	}
	
	public static boolean checkName ( String str )
	{
		String regex = "[^a-zA-Z0-9_]";
		Pattern pattern = Pattern.compile ( regex );
		return pattern.matcher ( str ).find ();
	}
	
	public static boolean checkPassword ( String str )
	{
		String regex = "[^a-zA-Z0-9/!#$*]";
		Pattern pattern = Pattern.compile ( regex );
		return pattern.matcher ( str ).find ();
	}
	
	public static Coord bCoord ( String coordinate )//short for "bl0ck coordinate"
	{
		return new Coord ( Long.parseLong ( coordinate.split ( ":" )[1].split ( "," )[0] ) , Long.parseLong ( coordinate.split ( ":" )[1].split ( "," )[1] ) );
	}
	
	public static int binDigitSum ( int decNum )
	{
		int sum = 0;
		while ( decNum != 0 )
		{
			sum += decNum&1;
			decNum >>= 1;
		}
		return sum;
	}
	
	private final static char[] seedChar = { '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' , 'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' , 'i' , 'j' , 'k' , 'l' , 'm' , 'n' , 'o' , 'p' , 'q' , 'r' , 's' , 't' , 'u' , 'v' , 'w' , 'x' , 'y' , 'z' , 'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J' , 'K' , 'L' , 'M' , 'N' , 'O' , 'P' , 'Q' , 'R' , 'S' , 'T' , 'U' , 'V' , 'W' , 'X' , 'Y' , 'Z' };
	
	public static String generateSeed ( int num , String str )
	{
		StringBuilder sb = new StringBuilder ();
		Random random = new Random ( string2LongByAscii ( "0"+num+"0"+str+"0" ) );
		for ( int i = 0 ; i < str.length () ; i++ )
			sb.append ( seedChar[Math.abs ( ( ( int ) str.charAt ( i ) )+random.nextInt () )%62] );
		return sb.toString ();
	}
	
	
	public static Integer generateRandomNumber ( int x )
	{
		Random randomTemp = new Random ( string2LongByAscii ( "0"+x+"0" ) );
		Random random = new Random ( randomTemp.nextInt ()+string2IntegerByAscii ( "0"+LocalDateTime.now ()+"0" ) );
		long temp = ( long ) Math.pow ( Math.abs ( random.nextInt ( 0xfffff ) )+1 , Math.abs ( random.nextInt ( 3 ) )+1 );
		random = new Random ( temp );
		return ( int ) ( long ) ( string2LongByAscii ( random.nextInt ()+"0"+temp+"0"+random.nextInt () ) );
	}
	@Deprecated
	public static char[] generateARandNumber ( )
	{
		Random randDigit = new Random (  );
		int digit = randDigit.nextInt ( 8888)+1;// e e
		char[] randNumber = new char[digit];
		randNumber[0] = seedChar[randDigit.nextInt ( 10 )+1];
		for ( int i = 1 ; i < digit ; i++ ) randNumber[i] = seedChar[randDigit.nextInt ( 11)];
		return randNumber;
	}
	@Deprecated
	public static boolean compare(char[] a,char[] b)//true if a>b
	{
		if ( a.length > b.length ) return true;
		if ( a.length < b.length ) return false;
		for ( int i = 0 ; i < a.length ; i++ )
		{
			if ( a[i] > b[i] ) return true;
		}
		return false;
	}
}
