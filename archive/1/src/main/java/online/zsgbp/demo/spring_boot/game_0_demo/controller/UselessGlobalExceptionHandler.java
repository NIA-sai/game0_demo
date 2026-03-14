package online.zsgbp.demo.spring_boot.game_0_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//That's because i'm useless :(
@ControllerAdvice
public class UselessGlobalExceptionHandler
{
	private static final Logger logger = LoggerFactory.getLogger ( UselessGlobalExceptionHandler.class );
	
	@ExceptionHandler ( Exception.class )
	public ResponseEntity < String > handleAllExceptions ( Exception ex )
	{
		logger.error ( "Exception : "+ex.getMessage () );
		return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR )
				.body ( "failure ovo "+ex.getMessage () );
	}
	
	@ExceptionHandler ( NullPointerException.class )
	public ResponseEntity < String > handleNullPointerException ( NullPointerException npe )
	{
		logger.error ( "NullPointerException : "+npe.getMessage () );
		return ResponseEntity.status ( HttpStatus.BAD_REQUEST )
				.body ( "something get as null ! : "+npe.getMessage () );
	}
	
	@ExceptionHandler ( RuntimeException.class )
	public ResponseEntity < String > handleRuntimeException ( RuntimeException re )
	{
		logger.error ( "RuntimeException : "+re.getMessage () );
		return ResponseEntity.status ( HttpStatus.BAD_REQUEST )
				.body ( "runtime exception : "+re.getMessage () );
	}
}