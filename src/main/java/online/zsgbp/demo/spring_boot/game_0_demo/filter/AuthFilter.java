package online.zsgbp.demo.spring_boot.game_0_demo.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.usermapper.LoginedMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.usermapper.UserMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static online.zsgbp.demo.spring_boot.game_0_demo.util.Jwt.filterToken;

public class AuthFilter implements Filter
{
	@Autowired
	private LoginedMapper loginedMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private Jwt jwt;
	
	public AuthFilter ( LoginedMapper loginedMapper , UserMapper userMapper , Jwt jwt )
	{
		this.loginedMapper = loginedMapper;
		this.userMapper = userMapper;
		this.jwt = jwt;
	}
	
	public static final ThreadLocal < String > nameHolder = new ThreadLocal <> ();
	public static final ThreadLocal < String > tokenHolder = new ThreadLocal <> ();
	public static final ThreadLocal < Integer > idHolder = new ThreadLocal <> ();
	
	
	@Override
	public void init ( FilterConfig filterConfig )
	{
		System.out.println ( "AuthFilter initialized success awa(actually111)" );
	}
	
	@Override
	public void doFilter ( ServletRequest servletRequest , ServletResponse servletResponse , FilterChain filterChain ) throws IOException, ServletException
	{
		HttpServletRequest httpServletRequest = ( HttpServletRequest ) servletRequest;//?
		HttpServletResponse httpServletResponse = ( HttpServletResponse ) servletResponse;//??
		String token = filterToken ( httpServletRequest );
		tokenHolder.set ( token );
		nameHolder.set ( jwt.validateToken ( token ) );
		idHolder.set ( userMapper.getIdByName ( nameHolder.get () ) );
		Boolean Flag = loginedMapper.isLogined ( userMapper.getIdByName ( jwt.validateToken ( token ) ) );
		if ( Flag == null ) Flag = false;
		if ( token == null || jwt.validateToken ( token ) == null || !Flag )
		{
			servletResponse.setContentType ( "application/json" );
			servletResponse.getWriter ().write ( "{code: 444 },{message: \"Unauthorized\"  try to re-log in , you could be auto log out}" );
			
			//httpServletResponse.sendRedirect("/user/login");
			//确实奇怪力
			
			return;
		}
		filterChain.doFilter ( servletRequest , servletResponse );
	}
	
	@Override
	public void destroy ()
	{
		System.out.println ( "AuthFilter destroyed success awa(actually 555)" );
	}
}
