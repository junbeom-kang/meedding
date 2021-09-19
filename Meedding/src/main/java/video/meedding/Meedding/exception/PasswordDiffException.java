package video.meedding.Meedding.exception;

public class PasswordDiffException extends RuntimeException {
    public PasswordDiffException() {
        super();
    }

    public PasswordDiffException(String message) {
        super(message);
    }

    public PasswordDiffException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordDiffException(Throwable cause) {
        super(cause);
    }

    protected PasswordDiffException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
