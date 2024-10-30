/*问AI问的.🤓☝️  */
package online.zsgbp.demo.spring_boot.game_0_demo.util;

import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Jwt
{
	@Value ( "${jwt.token-key}" )
	private String SECRET_KEY;
	
	@Value ( "${jwt.token-expire}" )
	private long EXPIRATION_TIME;  // seconds
	
	public String generateToken ( String username )
	{
		return Jwts.builder ()
				.setSubject ( username )
				.setIssuedAt ( new Date () )
				.setExpiration ( new Date ( System.currentTimeMillis ()+EXPIRATION_TIME*1000 ) )//so *1000
				.signWith ( SignatureAlgorithm.HS512 , SECRET_KEY )
				.compact ();
	}
	
	public String validateToken ( String token )
	{
		try
		{
			Claims claims = Jwts.parser ()
					.setSigningKey ( SECRET_KEY )
					.parseClaimsJws ( token )
					.getBody ();
			Date expiration = claims.getExpiration ();
			if ( expiration.before ( new Date () ) )
				return null;
			return claims.getSubject ();  // return "name"
		}
		catch ( JwtException|IllegalArgumentException e )
		{
			System.out.println ( "Invalid JWT Token" );
			return null;
		}
	}
	
	@Deprecated
	public boolean isTokenExpired ( String token )
	{
		Claims claims = Jwts.parser ()
				.setSigningKey ( SECRET_KEY )
				.parseClaimsJws ( token )
				.getBody ();
		Date expiration = claims.getExpiration ();
		return expiration.before ( new Date () );
	}
	
	public static String filterToken ( HttpServletRequest request )
	{
		//		final String authorizationHeader = request.getHeader ( "Authorization" );
		//		if ( authorizationHeader != null && authorizationHeader.startsWith ( "Bearer " ) )
		//			return authorizationHeader.substring ( 7 );
		//		return null;
		Cookie[] cookies = request.getCookies ();
		String token = null;
		if ( cookies != null )
			for ( Cookie cookie : cookies )
				if ( cookie.getName ().equals ( "token" ) )
				{
					token = cookie.getValue ();
					break;
				}
		return token;
	}
	
}

