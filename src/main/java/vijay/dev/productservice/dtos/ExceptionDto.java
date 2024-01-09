package vijay.dev.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ExceptionDto {
    private HttpStatus httpStatus;
    private String message;
    public ExceptionDto(HttpStatus httpStatus,String message)
    {
        this.httpStatus=httpStatus;
        this.message=message;
    }
}
