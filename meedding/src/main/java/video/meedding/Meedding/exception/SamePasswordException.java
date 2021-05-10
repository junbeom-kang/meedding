package video.meedding.Meedding.exception;

public class SamePasswordException extends RuntimeException{
    public SamePasswordException() {
        super();
    }

    public SamePasswordException(String message) {
        super(message);
    }

    public SamePasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public SamePasswordException(Throwable cause) {
        super(cause);
    }

    protected SamePasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
