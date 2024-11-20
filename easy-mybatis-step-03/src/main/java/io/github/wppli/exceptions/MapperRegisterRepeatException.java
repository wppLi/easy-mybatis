package io.github.wppli.exceptions;

/**
 * Mapper 重复注册错误
 * @author li--jiaqiang 2024−11−19
 */
public class MapperRegisterRepeatException extends RuntimeException {

    private final String errorMessage;

    public MapperRegisterRepeatException(String errorMessage) {
        super("MapperRegisterRepeatException");
        this.errorMessage = errorMessage;
    }

    public MapperRegisterRepeatException(String errorMessage , Throwable cause) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}