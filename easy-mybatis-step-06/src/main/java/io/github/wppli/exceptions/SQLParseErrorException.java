package io.github.wppli.exceptions;

/**
 * @author li--jiaqiang 2024−11−20
 */
public class SQLParseErrorException extends RuntimeException {

    private final String errorMessage;

    public SQLParseErrorException(String errorMessage) {
        super("MapperRegisterRepeatException");
        this.errorMessage = errorMessage;
    }

    public SQLParseErrorException(String errorMessage , Throwable cause) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}