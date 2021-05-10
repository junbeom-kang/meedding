package video.meedding.Meedding.exception;

public class ExistedNickNameException extends RuntimeException{
    public ExistedNickNameException() {
        super();
    }

    public ExistedNickNameException(String message) {
        super(message);
    }

    public ExistedNickNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistedNickNameException(Throwable cause) {
        super(cause);
    }

    protected ExistedNickNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
