package br.com.exception.chapie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final String ERRO_INESPERADO = "Um erro inesperado aconteceu, contate o suporte em contato@exception.com.br";
    private static final String RECURSO_NAO_ENCONTRADO = "Recurso não encontrado";
    private static final String JSON_INVALIDO = "Json inválido";
    private static final String METODO_NAO_SUPORTADO = "Método não suportado";
    private static final String ARGUMENTOS_INVALIDOS = "Argumentos inválidos";
    private static final String TIPO_PARAMETRO_INVALIDO = "Tipo de parâmetro inválido, verifique a documentação";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RespostaException handleException(HttpServletRequest request, Exception exception){
        return new RespostaException(request, ERRO_INESPERADO, exception.getMessage());
    }

    @ExceptionHandler({NoHandlerFoundException.class,ObjetoNaoEncontradoException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RespostaException handleNotFoundException(HttpServletRequest request, Exception exception){
        return new RespostaException(request, RECURSO_NAO_ENCONTRADO, exception.getMessage());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RespostaException badRequestException(HttpServletRequest request, HttpMessageNotReadableException exception) {
        return new RespostaException(request, JSON_INVALIDO, exception.getMessage());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RespostaException badRequestException(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        return new RespostaException(request, METODO_NAO_SUPORTADO, exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RespostaException badRequestException(HttpServletRequest request, MethodArgumentTypeMismatchException exception) {
        return new RespostaException(request, TIPO_PARAMETRO_INVALIDO, exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RespostaException badRequestException(HttpServletRequest request, MethodArgumentNotValidException exception) {

        List<FieldError> erros = exception.getBindingResult().getFieldErrors();

        HashMap<String, String> detail = new HashMap<String, String>();

        for (FieldError erroCampo : erros) {
            detail.put(erroCampo.getField(), erroCampo.getDefaultMessage());
        }

        return new RespostaException(request, ARGUMENTOS_INVALIDOS, detail);
    }
}
