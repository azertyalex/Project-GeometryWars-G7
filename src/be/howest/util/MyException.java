package be.howest.util;


public class MyException extends RuntimeException {
	private static final long serialVersionUID = 5596817537319968490L;

	public MyException(String message, Throwable ex){
        super(message,ex);
    }
    
    public MyException(String message){
        super(message);
    }
}
