package ru.touchin.vkchat;

public class ApiException extends Exception {
    public static final int OBSOLETE_VERSION = 1;
    public static final int RESPONDENT_NOT_FOUND = 2;
    public static final int USER_NAME_OR_PASSWORD = 3;

    private final int errorCode;

    public ApiException(final int errorCode, final String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
