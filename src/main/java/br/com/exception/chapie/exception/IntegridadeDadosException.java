package br.com.exception.chapie.exception;

public class IntegridadeDadosException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public IntegridadeDadosException(String msg) {
        super(msg);
    }

    public IntegridadeDadosException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
