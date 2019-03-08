package mk.trkalo.dnp.dnpshop.exception;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class CustomResponse {
    String errorMessage;

    public CustomResponse(String message){
        this.errorMessage = message;
    }

    public String getMessage() {
        return errorMessage;
    }
}
