package video.meedding.Meedding.exception;

public class ExistedIdException extends RuntimeException {
    public ExistedIdException() {
        super();
    }

    public ExistedIdException(String message) {
        super(message);
    }

    public ExistedIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistedIdException(Throwable cause) {
        super(cause);
    }

    protected ExistedIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
