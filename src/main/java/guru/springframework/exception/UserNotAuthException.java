package guru.springframework.exception;

public class UserNotAuthException extends Exception{

    private String message;

    public UserNotAuthException(String message){
        super(message);
        this.message=message;
    }

    public UserNotAuthException() {}
}
