package online.zsgbp.demo.spring_boot.game_0_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Deprecated//  I don't know-~~ (petter voice） why i can't use it
@Configuration
public class WebClientConfig
{
	
	@Bean
	public WebClient.Builder webClientBuilder ()
	{
		return WebClient.builder ();
	}
}