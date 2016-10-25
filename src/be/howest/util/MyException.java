package be.howest.util;

@SuppressWarnings("serial")
public class MyException extends RuntimeException {

	

	public MyException(String message, Throwable ex){
        super(message,ex);
    }
    
    public MyException(String message){
        super(message);
    }
}
