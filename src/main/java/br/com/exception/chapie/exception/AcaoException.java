package br.com.exception.chapie.exception;

public class AcaoException  extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public AcaoException(String msg) {
        super(msg);
    }

    public AcaoException(String msg, Throwable cause) {
        super(msg,cause);
    }
}