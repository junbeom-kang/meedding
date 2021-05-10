package video.meedding.Meedding.exception;

public class CantMakeFriendException extends RuntimeException {
    public CantMakeFriendException() {
        super();
    }

    public CantMakeFriendException(String message) {
        super(message);
    }

    public CantMakeFriendException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantMakeFriendException(Throwable cause) {
        super(cause);
    }

    protected CantMakeFriendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
