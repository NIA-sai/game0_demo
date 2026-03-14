package online.zsgbp.demo.spring_boot.game_0_demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
@JsonComponent
@Configuration
public class TimeFormatConfig
{
	
	@Value ( "${spring.jackson.time-format}" )
	private String pattern;
	
	// Date 类型全局时间格式化
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilder ()
	{
		
		return builder ->
		{
			DateFormat df = new SimpleDateFormat ( pattern );
			df.setTimeZone ( TimeZone.getTimeZone ( "GMT+8" ) );
			builder.failOnEmptyBeans ( false )
					.failOnUnknownProperties ( false )
					.featuresToDisable ( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS )
					.dateFormat ( df );
		};
	}
	
	@Bean
	public LocalDateTimeSerializer localDateTimeDeserializer ()
	{
		return new LocalDateTimeSerializer ( DateTimeFormatter.ofPattern ( pattern ) );
	}
	
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer ()
	{
		return builder -> builder.serializerByType ( LocalDateTime.class , localDateTimeDeserializer () );
	}
	
	@Bean
	public ObjectMapper objectMapper ()
	{
		ObjectMapper mapper = new ObjectMapper ();
		mapper.registerModule ( new JavaTimeModule () );
		return mapper;
	}
}