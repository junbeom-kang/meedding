package video.meedding.Meedding.exception;

public class ExistedEmailException extends RuntimeException {
    public ExistedEmailException() {
        super();
    }

    public ExistedEmailException(String message) {
        super(message);
    }

    public ExistedEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistedEmailException(Throwable cause) {
        super(cause);
    }

    protected ExistedEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
