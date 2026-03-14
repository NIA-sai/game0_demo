package online.zsgbp.demo.spring_boot.game_0_demo.config;

import lombok.Setter;
import online.zsgbp.demo.spring_boot.game_0_demo.filter.AuthFilter;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.usermapper.LoginedMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.mapper.usermapper.UserMapper;
import online.zsgbp.demo.spring_boot.game_0_demo.util.Jwt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Setter
@Configuration
public class FilterConfig
{
	@Value ( "${filter.auth-needed-api}" )
	private String authNeededApi;
	
	@Bean
	public FilterRegistrationBean < AuthFilter > authFilter ( LoginedMapper loginedMapper , UserMapper userMapper , Jwt jwt )
	{
		FilterRegistrationBean < AuthFilter > registrationBean = new FilterRegistrationBean <> ();
		
		AuthFilter authFilter = new AuthFilter (loginedMapper, userMapper, jwt);
		registrationBean.setFilter ( authFilter );
		
		registrationBean.addUrlPatterns ( authNeededApi.split ( "," ) );
		
		registrationBean.setOrder ( 1 ); ////
		
		return registrationBean;
	}
	
}