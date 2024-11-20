package io.github.wppli.exceptions;

/**
 * @author li--jiaqiang 2024−11−19
 */
public class MapperInstanceNotFountException extends RuntimeException {
    private final String errorMessage;

    public MapperInstanceNotFountException(String errorMessage) {
        super("MapperInstanceNotFountException");
        this.errorMessage = errorMessage;
    }

    public MapperInstanceNotFountException(String errorMessage , Throwable cause) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}