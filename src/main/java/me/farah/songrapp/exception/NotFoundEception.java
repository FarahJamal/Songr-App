package me.farah.songrapp.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
public class NotFoundEception extends Exception {

    private static final long serialVersionUID = 1L;

    public NotFoundEception(String message){
        super(message);
    }
}



