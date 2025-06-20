package br.com.mundodev;

public class CpfInvalidoException extends RuntimeException {

    public CpfInvalidoException() {
        super();
    }

    public CpfInvalidoException(String message) {
        super(message);
    }

    public CpfInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CpfInvalidoException(Throwable cause) {
        super(cause);
    }

}
