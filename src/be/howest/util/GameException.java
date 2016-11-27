package be.howest.util;


public class GameException extends RuntimeException {
	private static final long serialVersionUID = 5596817537319968490L;

	public GameException(String message, Throwable ex){
        super(message,ex);
    }
    
    public GameException(String message){
        super(message);
    }
}
