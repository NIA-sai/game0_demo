package online.zsgbp.demo.spring_boot.game_0_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.time.LocalDate;
import java.time.LocalDateTime;

@MapperScan( "online.zsgbp.demo.spring_boot.game_0_demo.mapper" )
@EnableCaching
@SpringBootApplication
public class Game0DemoApplication
{
	public static void main ( String[] args )
	{
		SpringApplication.run ( Game0DemoApplication.class , args );
		System.out.println ( "success awa"  );
	}
}
