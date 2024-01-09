package vijay.dev.productservice.exceptions;

import org.apache.coyote.http2.HpackDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vijay.dev.productservice.dtos.ExceptionDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(Exception ex)
    {
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND,ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
