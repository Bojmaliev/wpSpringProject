package mk.trkalo.dnp.dnpshop.exception;

import org.springframework.validation.BindingResult;
import java.util.LinkedList;
import java.util.List;

public class Error extends RuntimeException {
    public Error(String message){
        super(message);
    }
}
