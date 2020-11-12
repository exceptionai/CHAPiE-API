package br.com.exception.chapie.exception;

public class ExecucaoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExecucaoException(String msg) {
        super(msg);
    }

    public ExecucaoException(String msg, Throwable cause) {
        super(msg,cause);
    }
}