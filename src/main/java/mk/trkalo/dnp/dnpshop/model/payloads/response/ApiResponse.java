package mk.trkalo.dnp.dnpshop.model.payloads.response;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@ResponseBody
public class ApiResponse {
    public Boolean error=false;
    public List<String> messages = new LinkedList<>();

    public ApiResponse(String message){
        messages.add(message);
    }
    public ApiResponse(String message, Boolean error){
        messages.add(message);
        this.error=error;
    }
    public ApiResponse(BindingResult res) {
        error = true;
        res.getAllErrors().forEach(a->{
            messages.add(a.getDefaultMessage());
        });
    }
}
